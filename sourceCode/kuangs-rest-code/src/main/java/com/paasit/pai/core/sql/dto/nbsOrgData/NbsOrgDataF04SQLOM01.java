package com.paasit.pai.core.sql.dto.nbsOrgData;

import lombok.Data;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/18 13:40
 */
@Data
public class NbsOrgDataF04SQLOM01 {

    /**
     * id
     */
    private String id;

    /**
     * 组织类型
     */
    private Integer orgType;

    /**
     * 组织编码
     */
    private String code;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 组织名称
     */
    public String displayName;

    /**
     * 上级组织ID
     */
    private String orgSuperiorId;

    /**
     * NBS上级组织编码
     */
    private String orgSuperiorCode;

    /**
     * NBS上级组织名称
     */
    private String orgSuperiorName;

}
