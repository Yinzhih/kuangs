package com.paasit.pai.core.blogic.dto.demo;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paasit.pai.core.common.constraints.DateRange;
import com.paasit.pai.core.common.constraints.DecimalRange;
import com.paasit.pai.core.common.constraints.Digits;
import com.paasit.pai.core.common.constraints.IntegerRange;
import com.paasit.pai.core.common.constraints.LengthMax;
import com.paasit.pai.core.common.constraints.LengthRange;
import com.paasit.pai.core.common.constraints.NotBlank;
import com.paasit.pai.core.common.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 描述:[demo][人员]更新方法的RequestDTO
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Data
@ApiModel(description = "[demo][人员]更新方法的RequestDTO")
public class DemoF03ReqtM01 implements Serializable {

    private static final long serialVersionUID = 1495675280489558317L;
    
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id", required = true)
    @NotBlank(message="{com.paasit.pai.core.demo.id.NotBlank}")
    @LengthMax(value=36, message="{com.paasit.pai.core.demo.id.LengthMax}")
    private String id;
    
    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    @NotBlank(message="{com.paasit.pai.core.demo.name.NotBlank}")
    @LengthRange(min=5, max=20, message="{com.paasit.pai.core.demo.name.LengthRange}")
    private String name;
    
    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    @IntegerRange(min=20, max=100, message="{com.paasit.pai.core.demo.age.Integer}")
    @NotNull(message="{com.paasit.pai.core.demo.age.NotNull}")
    private Integer age;
    
    /**
     * 工资
     */
    @ApiModelProperty(value="工资")
    @DecimalRange(min="2400.00", max="25000.00", message="{com.paasit.pai.core.demo.salary.Decimal}")
    @Digits(integer=5, fraction=2, message="{com.paasit.pai.core.demo.salary.Digits}")
    @NotNull(message="{com.paasit.pai.core.demo.salary.NotNull}")
    private BigDecimal salary;
    
    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    @NotNull(message="{com.paasit.pai.core.demo.birthDay.NotNull}")
    @DateRange(min="1980-01-01", max="2000-12-31", message="{com.paasit.pai.core.demo.birthDay.Date}")
    private Date birthDay;
    
}
