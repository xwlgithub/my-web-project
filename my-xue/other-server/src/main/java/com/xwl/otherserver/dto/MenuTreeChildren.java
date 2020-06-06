package com.xwl.otherserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/6 09:48
 * @Description: 菜单
 */
@Data
public class MenuTreeChildren {
private Long id;
private String label;
private List<MenuChildrenPoint> children;
}
