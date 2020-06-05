package com.xwl.otherserver.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xwl.otherserver.domain.PermissionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 13:48
 * @Description:
 */
@Mapper
public interface PermissInfoMapper  extends BaseMapper<PermissionInfo>{
    List<PermissionInfo> findMaxMenuList();

    List<PermissionInfo> findChildrenMenuList(@Param("id") Long id);

    List<PermissionInfo> findMenuList(@Param("id")Long id);
}
