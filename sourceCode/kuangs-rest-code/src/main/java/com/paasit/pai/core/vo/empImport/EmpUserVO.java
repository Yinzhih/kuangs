package com.paasit.pai.core.vo.empImport;

import com.paasit.pai.core.sql.dto.SqlAuditBaseDto;
import lombok.Data;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/5/7 17:31
 */
@Data
public class EmpUserVO extends SqlAuditBaseDto {

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 员工id
     */
    private String empId;
}
