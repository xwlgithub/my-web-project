package com.xwl.otherserver.service.impl;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.otherserver.domain.PermissionInfo;
import com.xwl.otherserver.dto.MenuTableDto;
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
     * 父级菜单-子级
     *
     * @return
     */
    @Override
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
                menuTableDto.setMenuId(permissionInfo.getId());
                menuTableDto.setMenuName(permissionInfo.getMenuName());
                List<PermissionInfo> pnChildrenList = permissInfoMapper.findChildrenMenuList(permissionInfo.getId());
                menuTableDto.setPermissionInfoList(pnChildrenList);
                finalMenuTableDtos.add(menuTableDto);
            });
        } catch (Exception e) {
            throw  new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return menuTableDtos;
    }
}
