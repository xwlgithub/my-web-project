package com.xwl.otherserver.controller;

import com.xwl.otherserver.domain.Query;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.service.UserService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: 薛
 * @Date: 2020/5/29 15:59
 * @Description:
 */
@RestController
@RequestMapping("/userList")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private UserService userService;
    @GetMapping("findUserList")
    public MyPage<UserInfo> findUserList(Query query){
        MyPage<UserInfo> infoMyPage = userService.fidUserList(query);
        return   infoMyPage;
    }
}
