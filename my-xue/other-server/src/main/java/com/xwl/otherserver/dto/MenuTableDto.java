package com.xwl.otherserver.dto;

import com.xwl.otherserver.domain.PermissionInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 15:47
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTableDto {
    private Long menuId;
    private String menuName;
    private List<PermissionInfo> permissionInfoList;
}
