package com.xwl.otherserver.controller;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.utils.R;
import com.xwl.otherserver.dto.MenuTableDto;
import com.xwl.otherserver.dto.MenuTreeDto;
import com.xwl.otherserver.service.PermissInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 13:37
 * @Description:
 */
@RestController
@RequestMapping("/peron")
@AllArgsConstructor
public class PermissionController {
    private PermissInfoService permissInfoService;

    /**
     * 菜单分层及列表
     */
    @GetMapping("menuList")
    public R<List<MenuTableDto>> findPermissList() {
        List<MenuTableDto> permissionInfos=null;
        try {
            permissionInfos=permissInfoService.findPermissList();
        } catch (ApiException e) {
           return R.errors(e.getExceptionEnum());
        }
        return R.data(permissionInfos);
    }

    /**
     * 树形菜单
     * @return
     */
    @GetMapping("treeMenus")
    public R<List<MenuTreeDto>> findTreeMenus(){
        List<MenuTreeDto> menuTreeDtos=null;
        try {
            menuTreeDtos=permissInfoService.findTreeMenus();
        } catch (ApiException e) {
            return R.errors(e.getExceptionEnum());
        }
        return R.data(menuTreeDtos);
    }
}
