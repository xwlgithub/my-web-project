package com.xwl.showserver.controller;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xwl.comserver.utils.R;

import com.xwl.showserver.entity.UserMessage;
import com.xwl.showserver.mapper.UserMessageMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/7/7 10:11
 * @Description:
 */
@RestController
@RequestMapping("/webcome")
@AllArgsConstructor
@Api(value = "首页大屏",tags = "大屏相关接口")
public class DemoController {
    private UserMessageMapper userMessageMapper;
    @GetMapping("getUserMessages")
    @ApiOperation(value = "首页大屏历史留言列表",notes = "说明")
    public R<List<UserMessage>> getString() {
        return R.data(userMessageMapper.findAllUserMessages());
    }
    @PostMapping("addUserMessages")
    @ApiOperation(value = "新增留言",notes = "说明")
    @Transactional
    public R<Object> addUserMessages(@RequestBody @Valid UserMessage params, BindingResult bindingResult) {
        //校验数据是否满足规定格式
        if (bindingResult.hasErrors()) {
            //校验结果以集合的形式返回
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            Integer index = (int) (Math.random() * fieldErrorList.size());
            return R.fail(fieldErrorList.get(index).getDefaultMessage());
        }
        String isSuccess="留言成功";
        try {
            userMessageMapper.insert(params);
        } catch (Exception e) {
            isSuccess="失败-服务器异常";
            return R.fail(isSuccess);
        }
        return R.data(isSuccess);
    }
    @PostMapping("deleteUserMessages")
    @ApiOperation(value = "删除留言",notes = "说明")
    @Transactional
    public R<Object> updateUserMessages(@RequestParam("id")Long id) {

        String isSuccess="删除成功";
        try {
            userMessageMapper.deleteById(id);
        } catch (Exception e) {
            isSuccess="失败-服务器异常";
            return R.fail(isSuccess);
        }
        return R.data(isSuccess);
    }
}
