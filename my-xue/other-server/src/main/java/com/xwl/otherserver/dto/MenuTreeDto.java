package com.xwl.otherserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/6 09:48
 * @Description: 树形封装Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeDto {
    private Long id;
    private String label;
    private List<MenuTreeChildren> children;
}
