package com.xwl.otherserver.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xwl.otherserver.domain.PermissionInfo;
import com.xwl.otherserver.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    List<UserInfo> findLimit(@Param("current") Integer current,@Param("size") Integer size,@Param("userName") String userName);
    Integer findCounts(@Param("userName") String userName);

    Integer selectUserInfoByName(@Param("name") String name);

    UserInfo selectUserByName(@Param("name")String name);

    List<PermissionInfo> findRoleIdsByName(@Param("userName") String userName);
}
