package com.paasit.pai.core.blogic.dto.demo;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paasit.pai.core.common.constraints.Digits;
import com.paasit.pai.core.common.constraints.LengthMax;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 描述:[demo][人员]查询方法的RequestDTO
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Data
@ApiModel(description = "[demo][人员]查询方法的RequestDTO")
public class DemoF04ReqtM01 implements Serializable {

    private static final long serialVersionUID = 1495675280489558317L;
    
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id", required = true)
    @LengthMax(value=36, message="{com.paasit.pai.core.demo.id.LengthMax}")
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
    @Digits(integer=5, fraction=2, message="{com.paasit.pai.core.demo.salary.Digits}")
    private BigDecimal salary;
    
    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    private Date birthDay;
    
}
