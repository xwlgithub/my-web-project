package com.xwl.otherserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/8 14:53
 * @Description:角色权限菜单及按钮返回Dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuPermissDto {
    private Long id;
    private List<String> menus;
    private List<String> point;
}
