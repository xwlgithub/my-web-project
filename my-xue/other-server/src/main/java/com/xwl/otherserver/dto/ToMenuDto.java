package com.xwl.otherserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/9 09:20
 * @Description: 权限分类Dto
 * userName:用户名
 * buttons:按钮集合权限
 * menus:菜单集合权限
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToMenuDto {
    private List<String> menus;
    private List<String> buttons;
}
