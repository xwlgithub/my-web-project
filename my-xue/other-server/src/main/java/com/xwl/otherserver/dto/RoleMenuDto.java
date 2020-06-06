package com.xwl.otherserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/6 13:10
 * @Description: 树形菜单回显Dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuDto {
    private Long id;
    private String label;
}
