package com.xwl.otherserver.service.impl;

import com.xwl.otherserver.domain.Query;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.mapper.UserInfoMapper;
import com.xwl.otherserver.service.UserService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/5/29 16:07
 * @Description:
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserInfoMapper userInfoMapper;

    public MyPage<UserInfo> fidUserList(Query query) {
        Integer current=(query.getCurrent()-1)*query.getSize();
        List<UserInfo> userInfoList=userInfoMapper.findLimit(current,query.getSize());
        Integer totals=userInfoMapper.findCounts();
        return new MyPage<UserInfo>(query.getCurrent(),query.getSize(),userInfoList,totals);
    }
}
