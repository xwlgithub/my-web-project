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
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "三字母管理",tags = "三字母管理")
@AllArgsConstructor
public class LspController {
private ILspService lspService;

    @GetMapping("findLspList")
    @ApiOperation(value = "列表查询",tags = "传入分页基础参数,另外支持姓名查询")
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
    @ApiOperation(value = "保存或更新",tags = "传入json字符串")
    public R<Boolean> saveOrUpdate( @Valid @RequestBody LspWx lspWxs, BindingResult result){
        if (result.hasErrors()){
            int indexMessage=(int)(Math.random()*result.getAllErrors().size());
            return R.fail(result.getAllErrors().get(indexMessage).getDefaultMessage());
        }
        Boolean aBoolean=false;
        try {
             aBoolean = lspService.saveOrUpdate(lspWxs);
        } catch (ApiException e) {
            return R.errors(e.getExceptionEnum());
        }
        return R.data(aBoolean);
    }
    @ApiOperation(value = "删除",tags = "传入id")
    @PostMapping("deleteById/{id}")
    public  R<Boolean> deleteById(@PathVariable("id") Long id){
        Boolean b =lspService.deleteById(id);
        return R.data(b);
    }
}
