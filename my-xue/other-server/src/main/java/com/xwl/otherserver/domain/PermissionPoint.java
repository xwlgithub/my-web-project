package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 13:41
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("ALL")
@TableName(value = "permission_point")
public class PermissionPoint {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String pointName;
    private String pointCode;
    // 对应菜单id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long permissId;
}
