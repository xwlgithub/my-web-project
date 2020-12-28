package com.xwl.comserver.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 13:36
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("ALL")
@Entity
@Table(name = "menu_info")
public class PermissionInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String menuName;
    private String menuCode;
    private String menuPath;
    private String menuType;
    /**
     * 层级 分主子及以下 0为主
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    private List<PermissionInfo> permissionInfoList;
}
