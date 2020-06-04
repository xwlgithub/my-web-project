package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 13:36
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("ALL")
@TableName(value = "permission_info")
public class PermissionInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String menuName;
    private String menuCode;
    /**
     * 层级 分主子及以下 0为主
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;
}
