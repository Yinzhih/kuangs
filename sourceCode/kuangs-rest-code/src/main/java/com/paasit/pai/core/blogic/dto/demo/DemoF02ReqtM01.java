package com.paasit.pai.core.blogic.dto.demo;

import java.io.Serializable;

import com.paasit.pai.core.common.constraints.LengthMax;
import com.paasit.pai.core.common.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:[demo][人员]删除方法的RequestDTO
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Data
@ApiModel(description = "[demo][人员]删除方法的RequestDTO")
public class DemoF02ReqtM01 implements Serializable {

    private static final long serialVersionUID = 1495675280489558317L;
    
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id", required = true)
    @NotBlank(message="{com.paasit.pai.core.demo.id.NotBlank}")
    @LengthMax(value=36, message="{com.paasit.pai.core.demo.id.LengthMax}")
    private String id;
}
