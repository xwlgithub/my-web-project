package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public RoleMenu(Long roleId,Long menuId){
        this.roleId=roleId;
        this.menuId=menuId;
    }
}
