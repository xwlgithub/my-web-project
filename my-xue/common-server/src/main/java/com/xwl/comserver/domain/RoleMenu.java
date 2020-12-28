package com.xwl.comserver.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Auther: 薛
 * @Date: 2020/6/6 13:13
 * @Description:
 */
@Table(name = "role_menu")
@Entity
@Data
@AllArgsConstructor
public class RoleMenu {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer menuType;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuPid;

    public RoleMenu(Long roleId,Long menuId,Integer menuType,Long menuPid){
        this.roleId=roleId;
        this.menuId=menuId;
        this.menuType=menuType;
        this.menuPid=menuPid;
    }
}
