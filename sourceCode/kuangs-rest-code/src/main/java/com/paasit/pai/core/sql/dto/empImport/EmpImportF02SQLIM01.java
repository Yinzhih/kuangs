package com.paasit.pai.core.sql.dto.empImport;

import com.paasit.pai.core.vo.empImport.EmpOrgVO;
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
public class EmpImportF02SQLIM01 {
    private List<EmpOrgVO> emporgList = null;
}
