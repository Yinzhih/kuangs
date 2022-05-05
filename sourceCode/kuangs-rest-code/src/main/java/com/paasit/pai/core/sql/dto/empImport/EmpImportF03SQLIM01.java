package com.paasit.pai.core.sql.dto.empImport;

import com.paasit.pai.core.vo.empImport.EmployeeVO;
import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/26 15:29
 */
@Data
public class EmpImportF03SQLIM01 {
    private List<EmployeeVO> empList = null;
}
