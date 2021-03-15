package com.xwl.otherserver.service.impl;

import com.xwl.comserver.domain.PermissionInfo;
import com.xwl.comserver.domain.RoleInfo;
import com.xwl.comserver.domain.RoleMenu;
import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.otherserver.dto.RoleMenuDto;
import com.xwl.otherserver.mapper.PermissInfoMapper;
import com.xwl.otherserver.mapper.RoleMenuMapper;
import com.xwl.otherserver.vo.Query;
import com.xwl.otherserver.mapper.RoleInfoMapper;
import com.xwl.otherserver.service.RoleService;
import com.xwl.comserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: 薛
 * @Date: 2020/6/3 16:45
 * @Description:
 */
@Service
@AllArgsConstructor
@SuppressWarnings("ALL")
public class RoleServiceImpl implements RoleService {
    private RoleInfoMapper roleInfoMapper;
    private PermissInfoMapper permissInfoMapper;
    private RoleMenuMapper roleMenuMapper;

    public MyPage<RoleInfo> findRoleList(Query query, String roleName) {
        Integer current = (query.getCurrent() - 1) * query.getSize();
        List<RoleInfo> userInfoList = roleInfoMapper.findLimit(current, query.getSize(), roleName);
        Integer totals = roleInfoMapper.findCounts(roleName);
        return new MyPage<RoleInfo>(query.getCurrent(), query.getSize(), userInfoList, totals);
    }
    @Transactional
    public void saveRoleByParams(RoleInfo roleInfo) {
        try {
            if (StringUtils.isEmpty(roleInfo.getId())) {
                roleInfoMapper.insert(roleInfo);
            } else {
                roleInfoMapper.updateById(roleInfo);
            }
        } catch (Exception e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
    }

    @Transactional
    public Boolean deleteRoleById(Long id) {
        try {
            Integer integer = roleInfoMapper.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return true;
    }

    public List<RoleInfo> findRoleLists() {
        List<RoleInfo> roleInfos = roleInfoMapper.selectList(null);
        return roleInfos;
    }

    @Override
    public List<RoleMenuDto> findRoleMenuListById(Long id) {
            List<RoleMenuDto> menuDtoList=roleInfoMapper.findRoleMenuListById(id);
            return menuDtoList;
    }

    @Override
    @Transactional
    public Boolean saveRoleWithMenuByIds(String permissIds,Long roleId,String mydeleted) {
        //分配权限菜单列表
        List<String> list = Arrays.asList(permissIds.split(","));
        List<String> roleIds = Arrays.asList(mydeleted.split(","));
        try {
            //要删除的Id集合
            if (roleIds.size()!=0) {
                //先删除按钮
                permissInfoMapper.deletedByRoleId(roleIds,roleId);
            }
            //添加的集合
            if (list.size()!=0) {
                List<PermissionInfo> menuIds = permissInfoMapper.filterMaxIdrList(list);
                //查询当前角色已存储权限菜单 -过滤重复
                List<RoleMenu> roleMenuList = permissInfoMapper.findRoleMenuList(roleId);
                Map<Long, Long> longMap = roleMenuList.stream().collect(Collectors.toMap(roleMenu -> roleMenu.getMenuId(), roleMenu -> roleMenu.getRoleId()));
                if (menuIds.size() != 0) {
                    menuIds.stream().forEach(permissionInfo -> {
                        if (StringUtils.isEmpty(longMap.get(permissionInfo.getId()))) {
                            roleMenuMapper.insert(new RoleMenu(roleId, permissionInfo.getId(),
                                    Integer.parseInt(permissionInfo.getMenuType()), permissionInfo.getParentId()));
                        }
                    });
                }
            }
//            //子级菜单删除
//            List<RoleMenu> menuTypeByRoleId =permissInfoMapper.findMenuTypeByRoleId(roleId);
//            Iterator<RoleMenu> iterator = menuTypeByRoleId.iterator();
//            while (iterator.hasNext()){
//                RoleMenu roleMenu = iterator.next();
//                Integer sums=permissInfoMapper.findRoleMenuByParentId(roleMenu.getMenuId());
//                if (sums>0){
//                    continue;
//                }
//                roleMenuMapper.deleteById(roleMenu.getId());
//            }
//            //父级菜单删除
//            List<RoleMenu> menuTypeFather =permissInfoMapper.findMenuTypeByRoleIdTwo(roleId);
//            Iterator<RoleMenu> iteratorFather = menuTypeFather.iterator();
//            while (iteratorFather.hasNext()){
//                RoleMenu roleMenu = iteratorFather.next();
//                Integer sums=permissInfoMapper.findRoleMenuByParentIdFather(roleMenu.getMenuId());
//                if (sums>0){
//                    continue;
//                }
//                roleMenuMapper.deleteById(roleMenu.getId());
//            }
        } catch (Exception e) {
            e.printStackTrace();
            //throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return true;
    }
}
