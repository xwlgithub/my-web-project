package com.xwl.otherserver.service.impl;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.otherserver.domain.RoleMenu;
import com.xwl.otherserver.dto.RoleMenuDto;
import com.xwl.otherserver.mapper.PermissInfoMapper;
import com.xwl.otherserver.mapper.RoleMenuMapper;
import com.xwl.otherserver.vo.Query;
import com.xwl.otherserver.domain.RoleInfo;
import com.xwl.otherserver.mapper.RoleInfoMapper;
import com.xwl.otherserver.service.RoleService;
import com.xwl.otherserver.utils.MyPage;
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
        //首先清除上级菜单
        List<String> list = Arrays.asList(permissIds.split(","));
        List<String> roleIds = Arrays.asList(mydeleted.split(","));
        try {
            //先删除
            if (roleIds.size()!=0) {
                permissInfoMapper.deletedByRoleId(roleIds,roleId);
            }
            List<Long> menuIds=permissInfoMapper.filterMaxIdrList(list);
            //查询当前角色已存储权限菜单 -过滤重复
            List<RoleMenu> roleMenuList =permissInfoMapper.findRoleMenuList(roleId);
            Map<Long, Long> longMap = roleMenuList.stream().collect(Collectors.toMap(roleMenu -> roleMenu.getMenuId(), roleMenu -> roleMenu.getRoleId()));
            if (menuIds.size()!=0){
                menuIds.stream().forEach(id ->{
                    if (StringUtils.isEmpty(longMap.get(id))) {
                        roleMenuMapper.insert(new RoleMenu(roleId, id));
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            //throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return true;
    }
}
