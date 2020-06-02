package com.xwl.otherserver.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xwl.otherserver.domain.Query;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.mapper.UserInfoMapper;
import com.xwl.otherserver.service.UserService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    public MyPage<UserInfo> fidUserList(Query query,String userName) {
        Integer current = (query.getCurrent() - 1) * query.getSize();
        List<UserInfo> userInfoList = userInfoMapper.findLimit(current, query.getSize(),userName);
        Integer totals = userInfoMapper.findCounts(userName);
        return new MyPage<UserInfo>(query.getCurrent(), query.getSize(), userInfoList, totals);
    }

    @Transactional
    public void saveUserByParams(UserInfo user) {
        if (StringUtils.isEmpty(user.getId())) {
            userInfoMapper.insert(user);
        }else {
            userInfoMapper.updateById(user);
        }
    }
    @Transactional
    public Boolean deleteUserById(Long id) {
        try {
            Integer integer = userInfoMapper.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
