package com.xwl.otherserver.service.impl;

import com.xwl.comserver.domain.PermissionInfo;
import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.otherserver.dto.MenuChildrenPoint;
import com.xwl.otherserver.dto.MenuTableDto;
import com.xwl.otherserver.dto.MenuTreeChildren;
import com.xwl.otherserver.dto.MenuTreeDto;
import com.xwl.otherserver.mapper.PermissInfoMapper;
import com.xwl.otherserver.service.PermissInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 13:47
 * @Description:
 */
@Service
@AllArgsConstructor
public class PermissInfoServiceImpl implements PermissInfoService {
    private PermissInfoMapper permissInfoMapper;

    /**
     * 菜单层级列表集合
     * 父级菜单-子级菜单-菜单内所属按钮
     *
     * @return
     */
    @Override
    @SuppressWarnings("ALL")
    public List<MenuTableDto> findPermissList() throws ApiException{
        List<MenuTableDto> menuTableDtos = null;
        try {
            menuTableDtos = new LinkedList<>();
            List<PermissionInfo> permissionInfos = permissInfoMapper.findMaxMenuList();
            if (permissionInfos.size()==0){
                return menuTableDtos;
            }
            List<MenuTableDto> finalMenuTableDtos = menuTableDtos;
            permissionInfos.stream().forEach(permissionInfo -> {
                MenuTableDto menuTableDto = new MenuTableDto();
                menuTableDto.setId(permissionInfo.getId());
                menuTableDto.setMenuName(permissionInfo.getMenuName());
                menuTableDto.setMenuCode(permissionInfo.getMenuCode());
                menuTableDto.setMenuPath(permissionInfo.getMenuPath());
                List<PermissionInfo> pnChildrenList = permissInfoMapper.findChildrenMenuList(permissionInfo.getId());
                for (PermissionInfo info : pnChildrenList) {
                    List<PermissionInfo> menuList = permissInfoMapper.findMenuList(info.getId());
                    info.setPermissionInfoList(menuList);
                }
                menuTableDto.setPermissionInfoList(pnChildrenList);
                finalMenuTableDtos.add(menuTableDto);
            });
        } catch (Exception e) {
            throw  new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return menuTableDtos;
    }

    /**
     * 树形结构封装菜单列表
     * @return
     */
    @Override
    @SuppressWarnings("ALL")
    public List<MenuTreeDto> findTreeMenus() {
        List<MenuTreeDto> menuTreeDtos  = new LinkedList<>();
        List<PermissionInfo> permissionInfos=null;
        try {
             permissionInfos = permissInfoMapper.findMaxMenuList();
            if (permissionInfos.size()==0){
                return menuTreeDtos;
            }
            permissionInfos.stream().forEach(permissionInfo -> {
                MenuTreeDto menuTreeDto = new MenuTreeDto();
                menuTreeDto.setId(permissionInfo.getId());
                menuTreeDto.setLabel(permissionInfo.getMenuName());
                //子菜单
                List<MenuTreeChildren> pnChildrenList = permissInfoMapper.findTreeChildrens(permissionInfo.getId());
                for (MenuTreeChildren menuTreeChildren : pnChildrenList) {
                    List<MenuChildrenPoint> menuList = permissInfoMapper.findMenuPoints(menuTreeChildren.getId());
                    menuTreeChildren.setChildren(menuList);
                }
                menuTreeDto.setChildren(pnChildrenList);
                menuTreeDtos.add(menuTreeDto);
            });
        } catch (Exception e) {
            throw  new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return menuTreeDtos;
    }
}
