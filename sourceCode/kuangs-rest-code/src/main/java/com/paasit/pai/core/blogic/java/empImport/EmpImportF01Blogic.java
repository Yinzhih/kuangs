package com.paasit.pai.core.blogic.java.empImport;

import com.paasit.pai.core.blogic.dto.empImport.EmpImportF01ReqtM01;
import com.paasit.pai.core.blogic.dto.empImport.EmpImportF01RespM01;
import com.paasit.pai.core.dao.QueryDAO;
import com.paasit.pai.core.dao.UpdateDAO;
import com.paasit.pai.core.service.BizLogic;
import com.paasit.pai.core.sql.dto.empImport.EmpImportF01SQLIM01;
import com.paasit.pai.core.sql.dto.empImport.EmpImportF01SQLIM02;
import com.paasit.pai.core.sql.dto.empImport.EmpImportF02SQLIM01;
import com.paasit.pai.core.sql.dto.empImport.EmpImportF03SQLIM01;
import com.paasit.pai.core.sql.dto.kuangsEmpData.EmpDataF04SqlIM01;
import com.paasit.pai.core.sql.dto.nbsEmpData.NbsEmpDataF04SQLIM01;
import com.paasit.pai.core.sql.dto.nbsEmpData.NbsEmpDataF04SQLIM02;
import com.paasit.pai.core.sql.dto.nbsEmpData.NbsEmpDataF04SQLOM01;
import com.paasit.pai.core.sql.dto.nbsEmpData.NbsEmpDataF04SQLOM02;
import com.paasit.pai.core.sql.dto.nbsOrgData.NbsOrgDataF04SQLIM01;
import com.paasit.pai.core.sql.dto.nbsOrgData.NbsOrgDataF04SQLOM01;
import com.paasit.pai.core.utils.BeanCopierEx;
import com.paasit.pai.core.vo.empImport.EmpOrgVO;
import com.paasit.pai.core.vo.empImport.EmployeeVO;
import com.paasit.pai.core.vo.orgImport.OrganizationVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/22 14:23
 */
@Service("EmpImportF01Blogic")
@Transactional(rollbackFor = Exception.class)
public class EmpImportF01Blogic implements BizLogic<EmpImportF01ReqtM01, EmpImportF01RespM01> {


    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(EmpImportF01Blogic.class);

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
     * 加载数据到内存
     * key   : 组织id
     * value : 组织对象
     */
    private ThreadLocal<HashMap<String, OrganizationVO>> orgMap = new ThreadLocal<>();

    /**
     * 加载数据到内存
     * key   : 人员id
     * value : 人员对象
     */
    private ThreadLocal<HashMap<String, EmployeeVO>> empMap = new ThreadLocal<>();

    /**
     * 加载到内存 人员与组织的关系 key:bid value :aid
     */
    private ThreadLocal<HashMap<String, NbsEmpDataF04SQLOM02>> orgEmpMap = new ThreadLocal<HashMap<String, NbsEmpDataF04SQLOM02>>();


    @Override
    public EmpImportF01RespM01 execute(EmpImportF01ReqtM01 empImportF01ReqtM01) throws Exception {
        log.info("人员数据导入数据开始");
        EmpImportF01RespM01 result = new EmpImportF01RespM01();


        // 新增的list
        ArrayList<EmployeeVO> insertList = new ArrayList<EmployeeVO>();

        // 员工更新的 list
        ArrayList<EmployeeVO> updateList = new ArrayList<EmployeeVO>();

        // 员工与组织的关系 list
        ArrayList<EmpOrgVO> emporginsertList = new ArrayList<EmpOrgVO>();

        // 删除员工与组织的关系 list
        ArrayList<EmpOrgVO> emporgDelList = new ArrayList<EmpOrgVO>();

        //查询
        EmpDataF04SqlIM01 empDataF04SqlIM01 = new EmpDataF04SqlIM01();
        List<EmployeeVO> tempuserQueryList = kuangsQueryDAO.executeForObjectList("kuangsEmpInfoQuery01", empDataF04SqlIM01);
        if(null != tempuserQueryList && tempuserQueryList.size() > 0){

            //初始化数据
            init();

            for (EmployeeVO employeeVO : tempuserQueryList) {
                if (employeeVO != null && StringUtils.isNotBlank(employeeVO.getEmployeeNo())) {
                    if (empMap.get() != null && empMap.get().containsKey(employeeVO.getEmployeeNo())) {
                        //需要修改
                        employeeVO.setId(empMap.get().get(employeeVO.getEmployeeNo()).getId());
                        updateList.add(employeeVO);
                        //
                        if(employeeVO.getOrganizationCode() != null && orgEmpMap.get() != null && orgEmpMap.get().get(empMap.get().get(employeeVO.getEmployeeNo()).getId()).getAId() !=null && !orgEmpMap.get().get(empMap.get().get(employeeVO.getEmployeeNo()).getId()).getAId().equals(orgMap.get().get(employeeVO.getOrganizationCode()))){
                            EmpOrgVO empOrgVOI = new EmpOrgVO();
                            empOrgVOI.setId(UUID.randomUUID().toString());
                            empOrgVOI.setAId(orgMap.get().get(employeeVO.getOrganizationCode()).getId());
                            empOrgVOI.setBId(employeeVO.getId());
                            emporginsertList.add(empOrgVOI);


                            EmpOrgVO empOrgVOD = new EmpOrgVO();
                            empOrgVOD.setId(orgEmpMap.get().get(empMap.get().get(employeeVO.getEmployeeNo()).getId()).getId());
                            emporgDelList.add(empOrgVOD);
                        }
                    }else{
                        //需要新增
                        employeeVO.setDefaultLanguage("zh_CN");
                        insertList.add(employeeVO);

                        //人员与组织数据
                        if(employeeVO.getOrganizationCode() != null && orgMap.get() != null && orgMap.get().get(employeeVO.getOrganizationCode()) !=null){
                            EmpOrgVO empOrgVO = new EmpOrgVO();
                            empOrgVO.setId(UUID.randomUUID().toString());
                            empOrgVO.setAId(orgMap.get().get(employeeVO.getOrganizationCode()).getId());
                            empOrgVO.setBId(employeeVO.getId());
                            emporginsertList.add(empOrgVO);
                        }
                    }
                }
            }

            //新增人员数据
            if(insertList.size() > 0){
                EmpImportF01SQLIM01 sql0 = new EmpImportF01SQLIM01();
                sql0.setEmpList(insertList);
                updateDAO.execute("EmpImportF01SQL01", sql0);
            }

            //修改人员
            if(updateList.size() > 0 ){
                EmpImportF03SQLIM01 sql1 = new EmpImportF03SQLIM01();
                sql1.setEmpList(updateList);
                updateDAO.execute("EmpImportF03SQL01", sql1);
            }

            //新增人员与组织关系
            if(emporginsertList.size() >0){
                EmpImportF01SQLIM02 sql2 = new EmpImportF01SQLIM02();
                sql2.setEmporgList(emporginsertList);
                updateDAO.execute("EmpImportF01SQL02", sql2);
            }

            //删除人员与组织关系
            if(emporgDelList.size() >0){
                EmpImportF02SQLIM01 sql3 = new EmpImportF02SQLIM01();
                sql3.setEmporgList(emporgDelList);
                updateDAO.execute("EmpImportF02SQL01", sql3);
            }

        }
        return result;
    }


    /**
     * 初始化数据
     */
    private void init() {
        initEmp();
        initOrg();
        initOrgEmp();
    }

    /**
     *初始化人员数据   加载数据到内存 objectMapper： key：EmployeeNo，value：Object
     */
    private void initEmp(){
        NbsEmpDataF04SQLIM01 sql01 = new NbsEmpDataF04SQLIM01();
        List<NbsEmpDataF04SQLOM01> queryList = queryDAO.executeForObjectList("empImportF04SQL01", sql01);
        if (!CollectionUtils.isEmpty(queryList)) {
            HashMap<String,EmployeeVO> empTemp = new HashMap<>();
            for (NbsEmpDataF04SQLOM01 query : queryList) {
                EmployeeVO employeeVO = new EmployeeVO();
                BeanCopierEx.copy(query, employeeVO);
                empTemp.put(query.getEmployeeNo(),employeeVO);
            }
            empMap.set(empTemp);
        }

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
     * 初始化人员与组织的关系
     */
    private void initOrgEmp(){
        HashMap<String, NbsEmpDataF04SQLOM02> orgEmpTemp = new HashMap<String, NbsEmpDataF04SQLOM02>();
        NbsEmpDataF04SQLIM02 sql01 = new NbsEmpDataF04SQLIM02();
        List<NbsEmpDataF04SQLOM02> queryList = queryDAO.executeForObjectList("empImportF04SQL02", sql01);
        if (queryList != null && queryList.size() > 0) {
            for (NbsEmpDataF04SQLOM02 query : queryList) {
                if (StringUtils.isNotBlank(query.getId()) && StringUtils.isNotBlank(query.getAId()) && StringUtils.isNotBlank(query.getBId())) {
                    String equipmentMapKey = query.getBId();
                    orgEmpTemp.put(equipmentMapKey, query);
                }
            }
            orgEmpMap.set(orgEmpTemp);
        }
    }
}
