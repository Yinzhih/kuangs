
package com.paasit.pai.core.blogic.java.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paasit.pai.core.blogic.dto.demo.DemoF03ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF03RespM01;
import com.paasit.pai.core.dao.UpdateDAO;
import com.paasit.pai.core.service.BizLogic;
import com.paasit.pai.core.sql.dto.demo.DemoF03SQL01IM01;
import com.paasit.pai.core.utils.BeanCopierEx;

/**
 * 描述:[demo][人员]表的保存机能
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DemoF03BLogic implements BizLogic<DemoF03ReqtM01, DemoF03RespM01> {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(DemoF03BLogic.class);

    /**
     * 定义更新dao
     */
    @Autowired
    private UpdateDAO updateDAO;
    
    /**
     * [demo][人员]表的保存方法 
     * @param params 输入参数
     * @return DemoF03RespM01 
     **/
    public DemoF03RespM01 execute(DemoF03ReqtM01 params) throws Exception {
        //定义返回值对象
        DemoF03RespM01 result = new DemoF03RespM01();
        //定义完全更新的sqlDTO
        DemoF03SQL01IM01 dbcIU001 = new DemoF03SQL01IM01();
        
        //将request参数拷贝到sql001Dto里
        BeanCopierEx.copy(params, dbcIU001);
        
        //执行完全插入sql语句
        Integer rows = updateDAO.execute("DemoF03SQL01", dbcIU001);
        
        //返回受影响的行数
        result.setResult(rows);
        return result;
    }
}
