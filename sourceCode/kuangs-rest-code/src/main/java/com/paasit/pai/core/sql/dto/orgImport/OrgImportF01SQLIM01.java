package com.paasit.pai.core.sql.dto.orgImport;

import com.paasit.pai.core.vo.orgImport.OrganizationVO;
import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/18 16:50
 */
@Data
public class OrgImportF01SQLIM01 {

    private List<OrganizationVO> orgList = null;
}
