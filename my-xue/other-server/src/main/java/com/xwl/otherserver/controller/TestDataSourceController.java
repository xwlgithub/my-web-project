package com.xwl.otherserver.controller;

import com.xwl.comserver.utils.R;
import com.xwl.otherserver.service.TestDataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueWenLiang
 * @date 2021/8/31 10:13
 * @Description 测试feign异常回滚事务接口入口
 */
@RestController
@RequestMapping("/testDataSource")
@AllArgsConstructor
@Api(value = "测试多数据源问题",tags = "测试多数据源问题")
public class TestDataSourceController {

    private TestDataSourceService testDataSourceService;


    @PostMapping("/saveMsgs")
    @ApiOperation(value = "张三的接口")
    public R<Boolean> saveMesg(){
        Boolean isSuccess= testDataSourceService.saveMesg();
        return R.data(isSuccess);
    }
}
