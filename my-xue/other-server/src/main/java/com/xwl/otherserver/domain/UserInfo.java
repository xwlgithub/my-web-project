package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xwl.comserver.exception.ExceptionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户实例对象",description = "用户实体")
public class UserInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键ID")
    private Long id;
    @NotNull(message = "用户名不能为空!")
    @ApiModelProperty(value = "用户名")
    private String name;
    //@JsonIgnore
    @NotNull(message = "密码不能为空!")
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "地址")
    private String emailAddress;
    @ApiModelProperty(value = "备注")
    private String remark;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime=new Date();

    @TableField(exist = false)
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    //密码重输
    @TableField(exist = false)
    private String againPsd;
    @TableField(exist = false)
    private Integer isReset;
}
