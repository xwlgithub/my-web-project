package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: 薛
 * @Date: 2020/5/29 16:00
 * @Description:
 */
@TableName("user_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    @JsonIgnore
    private String password;
    private String emailAddress;
    private String remark;
    private String roleId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime=new Date();

    @TableField(exist = false)
    private String roleName;
}
