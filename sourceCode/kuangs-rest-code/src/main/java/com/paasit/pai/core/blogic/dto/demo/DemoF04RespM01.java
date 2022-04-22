package com.paasit.pai.core.blogic.dto.demo;

import java.io.Serializable;
import java.util.ArrayList;

import com.paasit.pai.core.bean.PageControllerInfo;
import com.paasit.pai.core.bean.ServiceResponseBase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:[com_paasit_pai_core_demo][人员]查询方法的ResponseDTO
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Data
@ApiModel(description = "[com_paasit_pai_core_demo][人员]查询方法的ResponseDTO")
public class DemoF04RespM01 extends ServiceResponseBase implements Serializable {

    private static final long serialVersionUID = 1495675280489558317L;
    
    /**
     * [com_paasit_pai_core_demo][人员]
     */
    @ApiModelProperty(value="人员列表")
    private ArrayList<DemoF04RespS01> demoList = null;
    
    /**
     * 分页DTO
     */
    @ApiModelProperty(value="分页DTO")
    private PageControllerInfo pageControllerInfo;
}
