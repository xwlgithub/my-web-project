package com.xwl.comserver.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Auther: 薛
 * @Date: 2020/6/3 16:42
 * @Description:
 */
@Table(name = "role_info")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public class RoleInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String roleName;
    private String remark;
    private LocalDateTime createTime;

}
