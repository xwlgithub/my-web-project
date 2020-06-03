package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/3 16:42
 * @Description:
 */
@TableName("role_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public class RoleInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String roleName;
    private String remark;
    private String permissId;
}
