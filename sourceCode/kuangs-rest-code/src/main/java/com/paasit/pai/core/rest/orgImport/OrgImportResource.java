package com.paasit.pai.core.rest.orgImport;

import com.codahale.metrics.annotation.Timed;
import com.paasit.pai.core.blogic.dto.orgImport.OrgImportF01ReqtM01;
import com.paasit.pai.core.blogic.dto.orgImport.OrgImportF01RespM01;
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
 * 描述：组织同步
 *
 * @author:Yinzh
 * @date: 2022/4/15 14:26
 */
@RestController("OrgImportResource")
@RequestMapping("/api")
@Api(value="[组织]资源操作接口")
public class OrgImportResource {

    private final Logger log = LoggerFactory.getLogger(OrgImportResource.class);


    @Autowired
    @Qualifier("OrgImportF01BLogic")
    private final BizLogic<OrgImportF01ReqtM01, OrgImportF01RespM01> OrgImportF01BLogic= null;

    @PostMapping("/orgImport")
    @Timed
    @ApiOperation(value="组织数据批量导入", notes="组织数据批量导入")
    public ResponseEntity<OrgImportF01RespM01> orgImport(@ApiParam @RequestBody @Validated OrgImportF01ReqtM01 orgImportF01ReqtM01) throws Exception {
        log.debug("REST请求添加组织资源 : {}", orgImportF01ReqtM01);

        OrgImportF01RespM01 result = OrgImportF01BLogic.execute(orgImportF01ReqtM01);


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
