package com.paasit.pai.core.vo.empImport;

import lombok.Data;

/**
 * 描述：
 *
 * @author:
 * @date: 2022/4/22 15:53
 */
@Data
public class EmployeeVO {

    /**
     * id
     */
    private String id;

    /**
     * 员工工号    EMP_ID
     */

    private String employeeNo;

    /**
     * 来源系统ID
     */
    private String sourceSystemId;

    /**
     * 来源系统编码
     */
    private String sourceSystemCode;

    /**
     * 中文名称
     */
    private String lastName;

    /**
     * 中文名称
     */
    private String firstName;

    /**
     * 性别 0：男  1:女
     */
    private String gender;

    /**
     *  员工类型
     */
    private String employeeType;

    /**
     * 联系方式 -- (基础属性),（非必填) -- phone
     */
    private String phone;

    /**
     * 昵称-- (基础属性),（非必填)
     */
    private String nickName;

    /**
     * 头像 -- (基础属性),（非必填) -- picSrc
     */
    private String picSrc;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 所属组织ID
     */
    private String organizationId;

    /**
     * 所属组织编码
     */
    private String organizationCode;

    /**
     * 所属组织名称
     */
    private String organizationName;

    /**
     * 所属组织全路径
     */
    private String orgFullPath;

    /**
     * 默认组织标识
     */
    private String defaultOrgId;


    /**
     * 生效日期
     */
    private String effectiveDate;

    /**
     * 失效日期
     */
    private String expirationDate;

    /**
     * 离职在职
     */
    private String status;

    private String defaultLanguage;
}
