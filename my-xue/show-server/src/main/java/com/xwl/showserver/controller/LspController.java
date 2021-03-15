package com.xwl.showserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwl.comserver.domain.UserInfo;
import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.utils.MyPage;
import com.xwl.comserver.utils.R;
import com.xwl.showserver.entity.LspWx;
import com.xwl.showserver.service.ILspService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "三字母管理",tags = "三字母管理")
@AllArgsConstructor
public class LspController {
private ILspService lspService;

    @GetMapping("findLspList")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页",name = "current",required = true,dataType = "integer"),
            @ApiImplicitParam(value = "每页显示条数",name = "size",required = true,dataType = "integer"),
            @ApiImplicitParam(value = "姓名",name = "name"),

    })
    public R<MyPage<LspWx>> findLspList(@RequestParam("current") Integer current,
                                        @RequestParam("size")Integer size,
                                        @RequestParam("name")String name){
        MyPage<LspWx> myPage=  lspService.findLspList(current,size,name);
        return R.data(myPage);
    }
    @PostMapping("saveOrUpdate")
    public R<Boolean> saveOrUpdate(@RequestBody String lspWx){
        Object userObject = JSONObject.parseObject(lspWx).get("lspWx");
        LspWx lspWxs = JSONObject.toJavaObject((JSON) userObject, LspWx.class);
        Boolean aBoolean=false;
        try {
             aBoolean = lspService.saveOrUpdate(lspWxs);
        } catch (ApiException e) {
            return R.errors(e.getExceptionEnum());
        }
        return R.data(aBoolean);
    }
    @PostMapping("deleteById/{id}")
    public  R<Boolean> deleteById(@PathVariable("id") Long id){
        Boolean b =lspService.deleteById(id);
        return R.data(b);
    }
}
