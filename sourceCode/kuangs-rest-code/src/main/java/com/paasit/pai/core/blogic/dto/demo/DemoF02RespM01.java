package com.paasit.pai.core.blogic.dto.demo;

import java.io.Serializable;

import com.paasit.pai.core.bean.ServiceResponseBase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:[com_paasit_pai_core_demo][人员]删除方法的ResponseDTO
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Data
@ApiModel(description = "[com_paasit_pai_core_demo][人员]删除方法的ResponseDTO")
public class DemoF02RespM01 extends ServiceResponseBase implements Serializable {

    private static final long serialVersionUID = 1495675280489558317L;
    
    /**
     * 受影响的行数
     */
    @ApiModelProperty(value="受影响的行数")
    private Integer result;
}
