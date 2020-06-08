package com.xwl.otherserver.service.impl;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.otherserver.domain.RoleInfo;
import com.xwl.otherserver.vo.Query;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.mapper.RoleInfoMapper;
import com.xwl.otherserver.mapper.UserInfoMapper;
import com.xwl.otherserver.service.UserService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: 薛
 * @Date: 2020/5/29 16:07
 * @Description:
 */
@Service
@AllArgsConstructor
@SuppressWarnings("ALL")
public class UserServiceImpl implements UserService {
    private UserInfoMapper userInfoMapper;
    private RoleInfoMapper roleInfoMapper;

    /**
     * 分页查询
     *
     * @param query
     * @param userName
     * @return
     */
    public MyPage<UserInfo> fidUserList(Query query, String userName) {
        Integer current = (query.getCurrent() - 1) * query.getSize();
        List<UserInfo> userInfoList = userInfoMapper.findLimit(current, query.getSize(), userName);
        List<RoleInfo> roleInfos = roleInfoMapper.findUserWithRoles();
        Map<Long, String> roleNameMap = roleInfos.stream().collect(Collectors.toMap(roleInfo -> roleInfo.getId(), roleInfo -> roleInfo.getRoleName()));
        userInfoList.stream().forEach(userInfo -> {
            if (!StringUtils.isEmpty(userInfo.getRoleId())) {
                String s = roleNameMap.get(userInfo.getRoleId());
                userInfo.setRoleName(roleNameMap.get(userInfo.getRoleId()));
            }
        });
        Integer totals = userInfoMapper.findCounts(userName);
        return new MyPage<UserInfo>(query.getCurrent(), query.getSize(), userInfoList, totals);
    }


    /**
     * 新增或修改
     *
     * @param user
     */
    @Transactional
    public void saveUserByParams(UserInfo user) throws ApiException {
        //id为空为注册账户
        if (StringUtils.isEmpty(user.getId())) {
            //校验当前用户是否已存在
            Integer isHaveName = userInfoMapper.selectUserInfoByName(user.getName());
            if (isHaveName > 0) {
                throw new ApiException(ExceptionEnum.NAME_ISHAVA);
            }
        }
        try {
            userInfoMapper.insert(user);
            userInfoMapper.updateById(user);
        } catch (Exception e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Transactional
    public Boolean deleteUserById(Long id) throws ApiException {
        try {
            Integer integer = userInfoMapper.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return true;
    }

    @Override
    public UserInfo loginServer(UserInfo userInfo) {
        UserInfo userInfo1 = userInfoMapper.selectUserByName(userInfo.getName());
        if (StringUtils.isEmpty(userInfo1)){
            throw new ApiException(ExceptionEnum.NAME_OR_PSD_ERROR);
        }
        if (!userInfo.getPassword().equals(userInfo1.getPassword())){
            throw new ApiException(ExceptionEnum.NAME_OR_PSD_ERROR);
        }
        return userInfo1;
    }
}
