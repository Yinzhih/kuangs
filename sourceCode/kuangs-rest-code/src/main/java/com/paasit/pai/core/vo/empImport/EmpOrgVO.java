package com.paasit.pai.core.vo.empImport;

import com.paasit.pai.core.sql.dto.SqlAuditBaseDto;
import lombok.Data;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/26 16:05
 */
@Data
public class EmpOrgVO extends SqlAuditBaseDto {

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
}
