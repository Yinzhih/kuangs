package com.paasit.pai.core.blogic.java.orgImport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paasit.pai.core.blogic.dto.orgImport.OrgImportF01ReqtM01;
import com.paasit.pai.core.blogic.dto.orgImport.OrgImportF01RespM01;
import com.paasit.pai.core.dao.QueryDAO;
import com.paasit.pai.core.dao.UpdateDAO;
import com.paasit.pai.core.service.BizLogic;
import com.paasit.pai.core.sql.dto.kuangsOrgData.OrgDataF04SQLIM01;
import com.paasit.pai.core.sql.dto.nbsOrgData.NbsOrgDataF04SQLIM01;
import com.paasit.pai.core.sql.dto.nbsOrgData.NbsOrgDataF04SQLIM02;
import com.paasit.pai.core.sql.dto.nbsOrgData.NbsOrgDataF04SQLOM01;
import com.paasit.pai.core.sql.dto.nbsOrgData.NbsOrgDataF04SQLOM02;
import com.paasit.pai.core.sql.dto.orgImport.OrgImportF01SQLIM01;
import com.paasit.pai.core.sql.dto.orgImport.OrgImportF01SQLIM02;
import com.paasit.pai.core.sql.dto.orgImport.OrgImportF02SQLIM01;
import com.paasit.pai.core.sql.dto.orgImport.OrgImportF03SQLIM01;
import com.paasit.pai.core.utils.BeanCopierEx;
import com.paasit.pai.core.vo.orgImport.OrganizationRelVO;
import com.paasit.pai.core.vo.orgImport.OrganizationVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：
 *
 * @author:Yinzh
 * @date: 2022/4/15 14:49
 */
@Service("OrgImportF01BLogic")
@Transactional(rollbackFor = Exception.class)
public class OrgImportF01BLogic implements BizLogic<OrgImportF01ReqtM01, OrgImportF01RespM01> {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(OrgImportF01BLogic.class);

    /**
     * ObjectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 定义查询dao
     */
    @Autowired
    protected QueryDAO queryDAO;

    /**
     * 定义更新dao
     */
    @Autowired
    private UpdateDAO updateDAO;


    /**
     * 定义kuangs查询dao
     */
    @Autowired
    protected QueryDAO kuangsQueryDAO;

    /**
     * 部门displayName模板
     */
    private final String displayNameFormat = "\"en\":\"{0}\",\"zh_CN\":\"{1}\"";

    /**
     * 加载数据到内存
     * key   : 组织id
     * value : 组织对象
     */
    private ThreadLocal<HashMap<String, OrganizationVO>> orgMap = new ThreadLocal<>();

    /**
     * 加载数据到内存
     * key   : 关系表的bId
     * value : 关系对象
     */
    private ThreadLocal<HashMap<String, OrganizationRelVO>> orgRelBMap = new ThreadLocal<>();

    /**
     * 加载数据到内存
     * key   : 关系表的AID+BID
     * value : 关系对象
     */
    private ThreadLocal<HashMap<String, OrganizationRelVO>> orgRelABMap = new ThreadLocal<>();


    @Override
    public OrgImportF01RespM01 execute(OrgImportF01ReqtM01 orgImportF01ReqtM01) throws Exception {

        OrgImportF01RespM01 result = new OrgImportF01RespM01();

        log.info("组织导入数据开始");

        //部门集合(做新增修改)
        List<OrganizationVO> branchOrgSaveList = new ArrayList<>();

        OrgDataF04SQLIM01 dbcIU001 = new OrgDataF04SQLIM01();
        //调用queryDAO进行查询
        //如果查询条件中有主键Id，则不带分页条件
        branchOrgSaveList = kuangsQueryDAO.executeForObjectList("kuangsOrgInfoQuery01", dbcIU001);

        for (OrganizationVO org :branchOrgSaveList){
            org.setDisplayName(objectMapper.readTree("{"+ MessageFormat.format(displayNameFormat,org.displayName,org.displayName)+"}").toString());
        }

        //新增组织数据集合
        List<OrganizationVO> addOrgList = new ArrayList<>();

        //修改组织数据集合
        List<OrganizationVO> updateOrgList = new ArrayList<>();

        //新增组织关系集合
        List<OrganizationRelVO> addOrgRelList = new ArrayList<>();

        //删除关系上级
        List<OrganizationRelVO> delSupOrgRelList = new ArrayList<>();

        Map<String, String> codeMap = new HashMap<>();

        if (branchOrgSaveList != null && branchOrgSaveList.size() > 0) {
            //初始化k2组织数据
            init();

            for (OrganizationVO SVO : branchOrgSaveList) {
                //部门code
                String subCompanyCode = SVO.getOrgCode();
                //上级部门code
                String supSComCode = SVO.getOrgSuperiorCode();
                String subCompanyId = "";
                if (null != orgMap.get() && null != orgMap.get().get(subCompanyCode)) {//当前部门在k2数据库是否存在
                    subCompanyId = orgMap.get().get(subCompanyCode).getId();
                } else {
                    subCompanyId = SVO.getId();
//                    subCompanyId = UUID.randomUUID().toString();//不存在创建新的组织id
                }
                codeMap.put(subCompanyCode, subCompanyId);//拼接key；orgCode value;36为guid
            }
            for (OrganizationVO hVO : branchOrgSaveList) {
                //部门id
                String subCodeId = hVO.getOrgCode();
                //上级部门id
                String supSupId = hVO.getOrgSuperiorCode();

                if (null != orgMap && null != orgMap.get() && null != orgMap.get().get(subCodeId)) {
                    //需要修改的组织
                    updateOrgList.add(hVO);
                    //如果组织关系有改变则删除组织关系（先删除，后新增）
                    if(null != orgRelBMap.get() && null != codeMap.get(subCodeId) && null != orgRelBMap.get().get(codeMap.get(subCodeId)) && !orgRelBMap.get().get(codeMap.get(subCodeId)).getAId().equals(codeMap.get(supSupId))){
                        OrganizationRelVO OrganizationRelDelVO = new OrganizationRelVO();
                        OrganizationRelDelVO.setId(orgRelBMap.get().get(codeMap.get(subCodeId)).getId());
                        delSupOrgRelList.add(OrganizationRelDelVO);
                        if(null != codeMap.get(supSupId) && null != hVO.getId()) {
                            addOrgRelList.add(setOrgRel(codeMap.get(supSupId), hVO.getId(),0));
                        }
                    }
                    //纯粹删除需要删除的组织关系
                    if(hVO.getIs_del() == 1){
                        OrganizationRelVO delorg = new OrganizationRelVO();
                        delorg.setId(orgRelBMap.get().get(codeMap.get(subCodeId)).getId());
                        delSupOrgRelList.add(delorg);
                    }
                }else{
                    //新增组织
                    addOrgList.add(hVO);
                    //组织关系
                    addOrgRelList.add(setOrgRel(null == codeMap.get(supSupId) ? "00000000-0000-0000-0000-000000000000" : codeMap.get(supSupId),hVO.getId(),0));
                }
            }
        }
        //新增组织
        if (addOrgList.size() > 0) {
            OrgImportF01SQLIM01 sql0 = new OrgImportF01SQLIM01();
            sql0.setOrgList(addOrgList);
            //数据库影响行数--计算实际成功条数
            updateDAO.execute("OrgImportF01SQL01", sql0);
        }

        //修改组织
        if (updateOrgList.size() > 0) {
            OrgImportF03SQLIM01 sql1 = new OrgImportF03SQLIM01();
            sql1.setOrgList(updateOrgList);
            updateDAO.execute("OrgImportF03SQL01", sql1);
        }


        //删除组织关系从代码逻辑赛选出来的 只删除上级
        if (delSupOrgRelList.size() > 0) {
            OrgImportF02SQLIM01 sql02 = new OrgImportF02SQLIM01();
            sql02.setOrgList(delSupOrgRelList);
            updateDAO.execute("OrgImportF02SQL01", sql02);
        }

        //新增组织关系
        if (addOrgRelList.size() > 0) {

            List<NbsOrgDataF04SQLOM01> orgList = queryDAO.executeForObjectList("orgImportF04SQL01", null);
            HashMap<String, OrganizationVO> orgTemp = new HashMap<>();
            if (!CollectionUtils.isEmpty(orgList)) {
                for (NbsOrgDataF04SQLOM01 query : orgList) {
                    OrganizationVO OrganizationVO = new OrganizationVO();
                    BeanCopierEx.copy(query, OrganizationVO);
                    orgTemp.put(query.getOrgCode(), OrganizationVO);
                }
            }
            for (OrganizationRelVO orgrel : addOrgRelList) {
                List<NbsOrgDataF04SQLOM01> currOrg = orgList.stream().filter(m -> m.getId().equals(orgrel.getBId())).collect(Collectors.toList());
                List<String> str =  getPath(orgTemp, currOrg.get(0).getOrgCode(),new ArrayList<>());
                Collections.reverse(str);
                orgrel.setIdPath("/" + String.join("/",str));
                orgrel.setLevel(str.size());
            }


            OrgImportF01SQLIM02 sql03 = new OrgImportF01SQLIM02();
            sql03.setOrgRelList(addOrgRelList);
            updateDAO.execute("OrgImportF01SQL02", sql03);
        }

        orgMap.remove();
        orgRelBMap.remove();
        orgRelABMap.remove();

        return result;
    }

    /**
     * 初始化K2组织数据
     */
    private void init() {
        initOrg();
        initOrgRel();
    }

    /**
     * 查询组织对象
     */
    private void initOrg() {
        NbsOrgDataF04SQLIM01 sql01 = new NbsOrgDataF04SQLIM01();
        List<NbsOrgDataF04SQLOM01> queryList = queryDAO.executeForObjectList("orgImportF04SQL01", sql01);
        if (!CollectionUtils.isEmpty(queryList)) {
            HashMap<String, OrganizationVO> orgTemp = new HashMap<>();
            for (NbsOrgDataF04SQLOM01 query : queryList) {
                OrganizationVO OrganizationVO = new OrganizationVO();
                BeanCopierEx.copy(query, OrganizationVO);
                orgTemp.put(query.getOrgCode(), OrganizationVO);
            }
            orgMap.set(orgTemp);
        }
    }

    /**
     * 查询组织关系表
     */
    private void initOrgRel() {
        HashMap<String, OrganizationRelVO> orgRelABTemp = new HashMap<>();
        HashMap<String, OrganizationRelVO> orgRelBTemp = new HashMap<>();
        NbsOrgDataF04SQLIM02 sql03 = new NbsOrgDataF04SQLIM02();
        List<NbsOrgDataF04SQLOM02> queryList = queryDAO.executeForObjectList("orgImportF04SQL02", sql03);
        if (!CollectionUtils.isEmpty(queryList)) {
            for (NbsOrgDataF04SQLOM02 query : queryList) {
                OrganizationRelVO vo = new OrganizationRelVO();
                BeanCopierEx.copy(query, vo);
                if (StringUtils.isNotBlank(query.getABId())) {
                    orgRelABTemp.put(query.getABId(), vo);
                }
                if (StringUtils.isNotBlank(query.getBId())) {
                    orgRelBTemp.put(query.getBId(), vo);
                }
            }
            if (orgRelBTemp != null) {
                orgRelBMap.set(orgRelBTemp);
            }
            if (orgRelABTemp != null) {
                orgRelABMap.set(orgRelABTemp);
            }
        }
    }

    /**
     * 组装新增--------------关系数据
     */
    private OrganizationRelVO setOrgRel(String aId, String bId, Integer isdel) {
        OrganizationRelVO OrganizationRelVO = new OrganizationRelVO();
        if (null != orgRelABMap && null != orgRelABMap.get() && orgRelABMap.get().containsKey(aId + bId)) {
            //关系已经存在，无需处理
        } else {
            OrganizationRelVO.setId(UUID.randomUUID().toString());
            OrganizationRelVO.setAId(aId);
            OrganizationRelVO.setBId(bId);
            OrganizationRelVO.setIs_del(isdel == null ? 0 : isdel);
        }
        return OrganizationRelVO;
    }

    private List<String> getPath(HashMap<String, OrganizationVO> orgTemp, String orgCode,List<String> paths){

        String supOrg = orgTemp.get(orgCode).getOrgSuperiorCode();

        paths.add(orgTemp.get(orgCode).getId());

        if(null == supOrg){
            paths.add("00000000-0000-0000-0000-000000000000");
        }else{
            getPath(orgTemp,supOrg,paths);
        }

        return paths;
    }
}
