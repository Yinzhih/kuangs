package com.paasit.pai.core.sql.dto.demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paasit.pai.core.sql.dto.SqlOutputBaseDto;

import lombok.Data;

/**
 * 描述:[com_paasit_pai_core_demo][人员]查询方法的SQLOutputDTO
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Data
public class DemoF04SQL01OM01 extends SqlOutputBaseDto implements Serializable {

    private static final long serialVersionUID = 1495675280489558317L;
    
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 工资
     */
    private BigDecimal salary;
    
    /**
     * 出生日期
     */
    private Date birthDay;
}
