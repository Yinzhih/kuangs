package com.paasit.pai.core.rest.empImport;

import com.codahale.metrics.annotation.Timed;
import com.paasit.pai.core.blogic.dto.empImport.EmpImportF01ReqtM01;
import com.paasit.pai.core.blogic.dto.empImport.EmpImportF01RespM01;
import com.paasit.pai.core.service.BizLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：人员同步
 *
 * @author:Yinzh
 * @date: 2022/4/22 13:55
 */
@RestController("EmpImportResource")
@RequestMapping("/kuangs-rest/api")
@Api(value="[组织]资源操作接口")
public class EmpImportResource {

    private final Logger log = LoggerFactory.getLogger(EmpImportResource.class);


    @Autowired
    @Qualifier("EmpImportF01Blogic")
    private final BizLogic<EmpImportF01ReqtM01, EmpImportF01RespM01> EmpImportF01BLogic= null;

    @PostMapping("/empImport")
    @Timed
    @ApiOperation(value="人员数据批量导入", notes="人员数据批量导入")
    public ResponseEntity<EmpImportF01RespM01> empImport(@ApiParam @RequestBody @Validated EmpImportF01ReqtM01 empImportF01ReqtM01) throws Exception {
        log.debug("REST请求添加人员数据资源 : {}", empImportF01ReqtM01);

        EmpImportF01RespM01 result = EmpImportF01BLogic.execute(empImportF01ReqtM01);


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
