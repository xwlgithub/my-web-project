package com.xwl.otherserver.dto;

import com.xwl.otherserver.domain.PermissionInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 15:47
 * @Description:表格菜单Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTableDto {
    private Long id;
    private String menuCode;
    private String menuName;
    private String menuPath;
    private List<PermissionInfo> permissionInfoList;
}
