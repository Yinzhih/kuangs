package com.paasit.pai.core.blogic.dto.demo;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paasit.pai.core.sql.dto.SqlOutputBaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:[com_paasit_pai_core_demo][人员]查询方法的子记录DTO
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Data
@ApiModel(description = "[com_paasit_pai_core_demo][人员]查询方法的子记录DTO")
public class DemoF04RespS01 extends SqlOutputBaseDto implements Serializable {

    private static final long serialVersionUID = 1495675280489558317L;
    
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private String id;
    
    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String name;
    
    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    private Integer age;
    
    /**
     * 工资
     */
    @ApiModelProperty(value="工资")
    private BigDecimal salary;
    
    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    private Date birthDay;
    
}
