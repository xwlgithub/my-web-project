package com.xwl.showserver.controller;

import com.xwl.comserver.utils.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 薛
 * @Date: 2020/7/7 10:11
 * @Description:
 */
@RestController
@RequestMapping("/ha")
@AllArgsConstructor
@Api(value = "哈哈",tags = "走你")
public class DemoController {
    @GetMapping("haha")
    @ApiOperation(value = "哈哈",notes = "说明")
    @ApiParam(value = "传入Id",name = "id",required = true,type = "String")
    public R getString(@RequestParam("id") Integer id) {
        return R.data("张三" + id);
    }

}
