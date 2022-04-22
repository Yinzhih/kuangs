package com.paasit.pai.core.rest.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paasit.pai.core.blogic.dto.demo.DemoF01ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF01RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF02ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF02RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF03ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF03RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF04ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF04RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF04RespS01;
import com.paasit.pai.core.service.BizLogic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述:[demo][人员]资源api
 * @version: 0_1
 * @author: AutoGenerate
 * @date:  2017-10-11 16:07:48
 */
@RestController
@RequestMapping("/api")
@Api(value="[人员]资源操作接口")
public class DemoResource {
        private final Logger log = LoggerFactory.getLogger(DemoResource.class);

    /**
     * 插入人员机能
     */
    @Autowired
    private final BizLogic<DemoF01ReqtM01, DemoF01RespM01> demoF01BLogic = null;

    /**
     * 删除人员机能
     */
    @Autowired
    private final BizLogic<DemoF02ReqtM01, DemoF02RespM01> demoF02BLogic = null;

    /**
     * 修改人员机能
     */
    @Autowired
    private final BizLogic<DemoF03ReqtM01, DemoF03RespM01> demoF03BLogic = null;

    /**
     * 获取人员列表机能
     */
    @Autowired
    private final BizLogic<DemoF04ReqtM01, DemoF04RespM01> demoF04BLogic = null;

    /**
     * POST  /demo : 添加[人员]记录
     *
     * @param demoF01ReqtM01 人员的添加机能的输入DTO
     * @return ResponseEntity 200：成功，其他响应表示有问题。
     */
    @PostMapping("/demo")
    @ApiOperation(value="创建人员", notes="根据DemoF01ReqtM01对象新增人员")
    public ResponseEntity<DemoF01RespM01> createDemo(@ApiParam @RequestBody @Validated DemoF01ReqtM01 demoF01ReqtM01)
            throws Exception {
        DemoF01RespM01 result = demoF01BLogic.execute(demoF01ReqtM01);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * DELETE  /demo : 删除[人员]记录
     *
     * @param demoF02ReqtM01 人员的删除机能的输入DTO
     * @return ResponseEntity 200：成功，其他响应表示有问题，删除只是逻辑删除，不是物理删除
     */
    @DeleteMapping("/demo")
    @ApiOperation(value="删除人员", notes="根据主键id删除人员")
    public ResponseEntity<DemoF02RespM01> deleteDemo(@ApiParam @RequestBody @Validated DemoF02ReqtM01 demoF02ReqtM01)
            throws Exception {
        DemoF02RespM01 result = demoF02BLogic.execute(demoF02ReqtM01);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * PUT  /demo : 修改[人员]记录
     *
     * @param demoF03ReqtM01 人员的修改机能的输入DTO
     * @return ResponseEntity 200：成功，其他响应表示有问题。
     */
    @PutMapping("/demo")
    @ApiOperation(value="修改人员", notes="根据DemoF03ReqtM01修改人员")
    public ResponseEntity<DemoF03RespM01> updateDemo(@ApiParam @RequestBody @Validated DemoF03ReqtM01 demoF03ReqtM01)
            throws Exception {
        DemoF03RespM01 result = demoF03BLogic.execute(demoF03ReqtM01);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * POST  /demo : 根据查询条件对[人员]分页查询
     *
     * @param demoF04ReqtM01 人员的分页机能的输入DTO
     * @return ResponseEntity 200：成功，其他响应表示有问题，返回满足条件的人员数据
     */
    @PostMapping("/demo/page")
    @ApiOperation(value="人员查询排序和分页", notes="根据DemoF04ReqtM01进行分页和排序查询")
    public ResponseEntity<DemoF04RespM01> getDemoByPage(@ApiParam @RequestBody @Validated DemoF04ReqtM01 demoF04ReqtM01) throws Exception {
        DemoF04RespM01 result = demoF04BLogic.execute(demoF04ReqtM01);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET  /demo : 根据主键ID请求[人员]
     *
     * @param id 人员的主键ID
     * @return ResponseEntity 200：成功，其他响应表示有问题，返回满足条件的人员数据
     */
    @GetMapping("/demo/{id}")
    @ApiOperation(value="获取单个人员", notes="根据主键id获取单个人员")
    public ResponseEntity<DemoF04RespS01> getDemoById(@ApiParam(value="主键id", required = true) @PathVariable String id) throws Exception {
        DemoF04ReqtM01 demoF04ReqtM01 = new DemoF04ReqtM01();
        demoF04ReqtM01.setId(id);
        DemoF04RespM01 demoF04RespM01 = demoF04BLogic.execute(demoF04ReqtM01);
        //如果查询的数据为空，则返回空
        if(demoF04RespM01 == null
            || demoF04RespM01.getDemoList().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        DemoF04RespS01 result = (DemoF04RespS01) demoF04RespM01.getDemoList().get(0);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

