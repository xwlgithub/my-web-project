package com.xwl.otherserver.service;

import com.xwl.otherserver.dto.MenuTableDto;

import java.util.List;

public interface PermissInfoService {
    List<MenuTableDto> findPermissList();

}
