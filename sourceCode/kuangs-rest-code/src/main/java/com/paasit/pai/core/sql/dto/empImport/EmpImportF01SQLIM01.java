package com.paasit.pai.core.sql.dto.empImport;
import com.paasit.pai.core.vo.empImport.EmployeeVO;
import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/25 22:58
 */
@Data
public class EmpImportF01SQLIM01 {

    private List<EmployeeVO> empList = null;
}
