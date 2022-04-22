
package com.paasit.pai.core.blogic.java.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paasit.pai.core.bean.PageControllerInfo;
import com.paasit.pai.core.blogic.dto.demo.DemoF04ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF04RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF04RespS01;
import com.paasit.pai.core.consts.CommonConsts;
import com.paasit.pai.core.context.BLContext;
import com.paasit.pai.core.dao.QueryDAO;
import com.paasit.pai.core.service.BizLogic;
import com.paasit.pai.core.sql.dto.demo.DemoF04SQL01IM01;
import com.paasit.pai.core.sql.dto.demo.DemoF04SQL01OM01;
import com.paasit.pai.core.utils.BeanCopierEx;
import com.paasit.pai.core.utils.Pub;

/**
 * 描述:[demo][人员]表的列表查询机能
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class DemoF04BLogic implements BizLogic<DemoF04ReqtM01, DemoF04RespM01> {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(DemoF04BLogic.class);

    /**
     * 定义查询dao
     */
    @Autowired
    protected QueryDAO queryDAO;

     /**
     * [demo][人员]表的列表查询方法
     * @param params 输入参数
     * @return DemoF04RespM01
     **/
    public DemoF04RespM01 execute(DemoF04ReqtM01 params) throws Exception {
        //构造sql001的查询条件
        DemoF04SQL01IM01 dbcIU001 = new DemoF04SQL01IM01();
        //将http请求的参数赋值给sqlDTO
        BeanCopierEx.copy(params, dbcIU001);
        //从上下文中获取分页信息
        PageControllerInfo pageInput = (PageControllerInfo)BLContext.getValue(CommonConsts.PAGE_INFO);

        //调用queryDAO进行查询
        //如果查询条件中有主键Id，则不带分页条件
        List<DemoF04SQL01OM01> queryList = null;
        if(StringUtils.isNotBlank(params.getId())) {
            queryList = queryDAO.executeForObjectList("DemoF04SQL01", dbcIU001);
        }
        else {
            queryList = queryDAO.executeForObjectListByPage("DemoF04SQL01", dbcIU001, pageInput);
        }

        //创建返回对象
        DemoF04RespM01 result = new DemoF04RespM01();

        //将sql001返回的list对象设置到response的返回对象中
        ArrayList<DemoF04RespS01> detailList = BeanCopierEx.copy(queryList, DemoF04RespS01.class);
        result.setDemoList(detailList);

        //设置分页信息
        result.setPageControllerInfo(Pub.getPageControllerInfo(queryList));
        return result;
    }
}
