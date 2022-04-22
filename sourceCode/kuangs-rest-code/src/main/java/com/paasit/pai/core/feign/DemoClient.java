package com.paasit.pai.core.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paasit.pai.core.blogic.dto.demo.DemoF01ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF01RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF02ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF02RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF03ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF03RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF04ReqtM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF04RespM01;
import com.paasit.pai.core.blogic.dto.demo.DemoF04RespS01;

/**
 * 描述:使用FeignClient进行调用示例
 * @version: 0_1
 * @author: 谷春
 * @date: 2017-12-19 20:17:06
 */
@FeignClient("k2wrapper-test")
public interface DemoClient {
    
    /**
     * 添加[人员]记录
     */
    @RequestMapping(value = "/api/demo", method = RequestMethod.POST)
    DemoF01RespM01 createDemo(DemoF01ReqtM01 demoF01ReqtM01);
    
    /**
     * 删除[人员]记录
     */
    @RequestMapping(value = "/api/demo", method = RequestMethod.DELETE)
    DemoF02RespM01 deleteDemo(DemoF02ReqtM01 demoF02ReqtM01);
    
    /**
     * 修改[人员]记录
     */
    @RequestMapping(value = "/api/demo", method = RequestMethod.PUT)
    DemoF03RespM01 updateDemo(DemoF03ReqtM01 demoF03ReqtM01);
    
    /**
     * 分页查询[人员]记录
     */
    @RequestMapping(value = "/api/demo", method = RequestMethod.POST)
    DemoF04RespM01 getDemoByPage(DemoF04ReqtM01 demoF04ReqtM01);
    
    /**
     * 获取单个[人员]记录
     */
    @RequestMapping(value = "/api/demo/{id}", method = RequestMethod.GET)
    DemoF04RespS01 getDemoById(@PathVariable("id") String id);
}
