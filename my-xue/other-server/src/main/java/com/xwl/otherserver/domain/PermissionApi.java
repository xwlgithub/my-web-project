package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/4 13:43
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("ALL")
@TableName(value = "permission_api")
public class PermissionApi {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String apiName;
    private String apiCode;
    private String apiServer;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long permissId;
}
