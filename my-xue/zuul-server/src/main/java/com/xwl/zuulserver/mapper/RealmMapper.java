package com.xwl.zuulserver.mapper;

import com.xwl.otherserver.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: 薛
 * @Date: 2020/6/10 13:51
 * @Description:
 */
@Mapper
public interface RealmMapper  {
  UserInfo selectUserByName(@Param("name")String name);
}
