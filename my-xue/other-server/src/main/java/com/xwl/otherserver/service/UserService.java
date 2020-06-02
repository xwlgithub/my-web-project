package com.xwl.otherserver.service;


import com.xwl.otherserver.domain.Query;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.utils.MyPage;

public interface UserService {
    MyPage<UserInfo> fidUserList(Query query,String userName);

    void saveUserByParams(UserInfo user);

    Boolean deleteUserById(Long id);
}
