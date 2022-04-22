
package com.paasit.pai.core.blogic.java.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paasit.pai.core.blogic.dto.demo.DemoF02ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF02RespM01;
import com.paasit.pai.core.dao.UpdateDAO;
import com.paasit.pai.core.service.BizLogic;
import com.paasit.pai.core.sql.dto.demo.DemoF02SQL01IM01;
import com.paasit.pai.core.utils.BeanCopierEx;

/**
 * 描述:[demo][人员]表的删除机能
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DemoF02BLogic implements BizLogic<DemoF02ReqtM01, DemoF02RespM01> {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(DemoF02BLogic.class);

    /**
     * 定义更新dao
     */
    @Autowired
    private UpdateDAO updateDAO;
    
    /**
     * [demo][人员]表的删除方法 
     * @param params 输入参数
     * @return DemoF02RespM01 
     **/
    public DemoF02RespM01 execute(DemoF02ReqtM01 params) throws Exception {
        //定义返回值
        DemoF02RespM01 result = new DemoF02RespM01();
        //定义updateDAO输入参数
        DemoF02SQL01IM01 dbcIU001 = new DemoF02SQL01IM01();
        BeanCopierEx.copy(params, dbcIU001);
        
        Integer rows = updateDAO.execute("DemoF02SQL01", dbcIU001);
        result.setResult(rows);
        return result;
    }
}
