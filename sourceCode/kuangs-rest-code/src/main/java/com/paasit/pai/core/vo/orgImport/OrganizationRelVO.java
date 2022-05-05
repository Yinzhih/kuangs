package com.paasit.pai.core.vo.orgImport;

import com.paasit.pai.core.sql.dto.SqlAuditBaseDto;
import lombok.Data;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/18 14:57
 */
@Data
public class OrganizationRelVO extends SqlAuditBaseDto {

    /**
     * id
     */
    private String id;

    /**
     * aId
     */
    private String aId;

    /**
     * bId
     */
    private String bId;

    private Integer is_del;
}
