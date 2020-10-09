package com.xwl.otherserver.service;


import com.xwl.comserver.domain.UserInfo;
import com.xwl.otherserver.dto.ToMenuDto;
import com.xwl.otherserver.vo.Query;
import com.xwl.otherserver.utils.MyPage;

public interface UserService {
    MyPage<UserInfo> fidUserList(Query query, String userName);

    void saveUserByParams(UserInfo user);

    Boolean deleteUserById(Long id);

    UserInfo loginServer(UserInfo userInfo);

    ToMenuDto findAuthByToken(String token);
}
