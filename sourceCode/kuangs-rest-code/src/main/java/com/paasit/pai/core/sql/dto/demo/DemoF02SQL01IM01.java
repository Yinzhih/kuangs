package com.paasit.pai.core.sql.dto.demo;

import java.io.Serializable;

import com.paasit.pai.core.sql.dto.SqlAuditBaseDto;

import lombok.Data;

/**
 * 描述:[com_paasit_pai_core_demo][人员]删除方法的SQLInputDTO
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-10-11 16:07:48
 */
@Data
public class DemoF02SQL01IM01 extends SqlAuditBaseDto implements Serializable {

    private static final long serialVersionUID = 1495675280489558317L;
    
    /**
     * 主键id
     */
    private String id;
}
