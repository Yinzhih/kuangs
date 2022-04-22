package com.paasit.pai.core.sql.dto.orgImport;

import com.paasit.pai.core.vo.orgImport.OrganizationRelVO;
import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/19 16:53
 */
@Data
public class OrgImportF02SQLIM01 {
    private List<OrganizationRelVO> orgList = null;
}
