package com.xwl.otherserver.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xwl.otherserver.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    List<UserInfo> findLimit(@Param("current") Integer current,@Param("size") Integer size);
    Integer findCounts();
}
