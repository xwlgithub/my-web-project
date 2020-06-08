package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xwl.comserver.exception.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "用户名不能为空!")
    private String name;
    //@JsonIgnore
    @NotNull(message = "密码不能为空!")
    private String password;
    private String emailAddress;
    private String remark;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime=new Date();

    @TableField(exist = false)
    private String roleName;
    //密码重输
    @TableField(exist = false)
    private String againPsd;
    @TableField(exist = false)
    private Integer isReset;
}
