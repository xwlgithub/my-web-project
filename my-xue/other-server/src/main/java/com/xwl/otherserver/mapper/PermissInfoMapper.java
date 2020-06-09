package com.xwl.otherserver.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xwl.otherserver.domain.PermissionInfo;
import com.xwl.otherserver.domain.RoleMenu;
import com.xwl.otherserver.dto.MenuChildrenPoint;
import com.xwl.otherserver.dto.MenuTreeChildren;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 13:48
 * @Description:
 */
@Mapper
@SuppressWarnings("ALL")
public interface PermissInfoMapper  extends BaseMapper<PermissionInfo>{
    List<PermissionInfo> findMaxMenuList();

    List<PermissionInfo> findChildrenMenuList(@Param("id") Long id);

    List<PermissionInfo> findMenuList(@Param("id")Long id);

    List<MenuTreeChildren> findTreeChildrens(@Param("id")Long id);

    List<MenuChildrenPoint> findMenuPoints(@Param("id")Long id);

    List<PermissionInfo> filterMaxIdrList(@Param("permissIds") List<String> permissIds);
    List<RoleMenu> findRoleMenuList(@Param("roleId") Long roleId);

    void deletedByRoleId(@Param("menuIds") List<String> menuIds,@Param("roleId") Long roleId);

    List<RoleMenu> findMenuTypeByRoleId(@Param("roleId")Long roleId);
    List<RoleMenu> findMenuTypeByRoleIdTwo(@Param("roleId")Long roleId);

    Integer findRoleMenuByParentId(@Param("menuId") Long menuId);

    List<PermissionInfo> findAll();

    Integer findRoleMenuByParentIdFather(@Param("menuId")Long menuId);
}
