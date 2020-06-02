package com.xwl.otherserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwl.otherserver.domain.Query;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.service.UserService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @Auther: 薛
 * @Date: 2020/5/29 15:59
 * @Description:
 */
@RestController
@RequestMapping("/userList")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    /**
     * 分页查询
     * @param query
     * @return
     */
    @GetMapping("findUserList")
    public MyPage<UserInfo> findUserList(Query query,String userName){
        MyPage<UserInfo> infoMyPage = userService.fidUserList(query,userName);
        return   infoMyPage;
    }

    /**
     * 新增或修改
     * @param userJson
     * @return
     */
    @PostMapping("saveUserByParams")
    public Map<String,Object> saveUserByParams(@RequestBody String userJson){
        Map<String,Object> returnMap=new HashMap<String, Object>();
        Object userObject = JSONObject.parseObject(userJson).get("userJson");
        UserInfo user = JSONObject.toJavaObject((JSON) userObject, UserInfo.class);
        if (StringUtils.isEmpty(user)){
            returnMap.put("error","部分字段为空清核实!");
            return returnMap;
        }
        userService.saveUserByParams(user);
        returnMap.put("success","操作成功!");
        return returnMap;
    }

    /**
     * 删除用户-ID
     * @param id
     * @return
     */
    @PostMapping("deleteUserById/{id}")
    public Map<String,Object> deleteUserById(@PathVariable("id")Long id){
        Map<String,Object> returnMap=new HashMap<String, Object>();
        if (StringUtils.isEmpty(id)){
            returnMap.put("error","缺少必要请求参数,请重试");
            return returnMap;
        }
      Boolean isSuccess=  userService.deleteUserById(id);
        if (isSuccess){
            returnMap.put("success","删除成功!");
            return returnMap;
        }
        returnMap.put("error","服务器异常");
        return returnMap;
    }
}
