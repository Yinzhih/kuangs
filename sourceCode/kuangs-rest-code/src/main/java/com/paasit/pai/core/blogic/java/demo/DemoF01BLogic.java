
package com.paasit.pai.core.blogic.java.demo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paasit.pai.core.blogic.dto.demo.DemoF01ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF01RespM01;
import com.paasit.pai.core.dao.UpdateDAO;
import com.paasit.pai.core.service.BizLogic;
import com.paasit.pai.core.sql.dto.demo.DemoF01SQL01IM01;
import com.paasit.pai.core.utils.BeanCopierEx;

/**
 * 描述:[demo][人员]表的新增机能
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DemoF01BLogic implements BizLogic<DemoF01ReqtM01, DemoF01RespM01> {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(DemoF01BLogic.class);

    /**
     * 定义更新dao
     */
    @Autowired
    private UpdateDAO updateDAO;

    /**
     * [demo][人员]表的新增机能
     * @param params 输入参数
     * @return DemoF01RespM01
     **/
    public DemoF01RespM01 execute(DemoF01ReqtM01 params) throws Exception {
        //定义返回值对象
        DemoF01RespM01 result = new DemoF01RespM01();

        //定义sqlDTO
        DemoF01SQL01IM01 dbcIU001 = new DemoF01SQL01IM01();

        //将request参数拷贝到sql001Dto里
        BeanCopierEx.copy(params, dbcIU001);
        //生成数据库的主键id
        dbcIU001.setId(UUID.randomUUID().toString());

        //执行完全插入sql语句
        Integer rows = updateDAO.execute("DemoF01SQL01", dbcIU001);

        //返回受影响的行数
        result.setResult(rows);

        //返回主键id
        result.setId(dbcIU001.getId());

        return result;
    }
}
