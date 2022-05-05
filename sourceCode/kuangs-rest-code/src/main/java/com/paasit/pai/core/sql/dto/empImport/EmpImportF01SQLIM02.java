package com.paasit.pai.core.sql.dto.empImport;

import com.paasit.pai.core.vo.empImport.EmpOrgVO;
import com.paasit.pai.core.vo.empImport.EmployeeVO;
import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/26 14:52
 */
@Data
public class EmpImportF01SQLIM02 {

    private List<EmpOrgVO> emporgList = null;
}
