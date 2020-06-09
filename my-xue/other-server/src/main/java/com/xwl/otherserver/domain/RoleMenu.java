package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: 薛
 * @Date: 2020/6/6 13:13
 * @Description:
 */
@TableName("role_menu")
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
