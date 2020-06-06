package com.xwl.otherserver.service;

import com.xwl.otherserver.dto.MenuTableDto;
import com.xwl.otherserver.dto.MenuTreeDto;

import java.util.List;

public interface PermissInfoService {
    List<MenuTableDto> findPermissList();

    List<MenuTreeDto> findTreeMenus();

}
