package com.xwl.showserver.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: 薛
 * @Date: 2020/9/27 15:46
 * @Description:
 */
@TableName("user_message")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @NotNull(message = "姓名不能为空")
    private String name;
    @NotNull(message = "留言信息不能为空")
    private String message;
}
