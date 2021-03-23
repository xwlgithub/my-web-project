package com.xwl.showserver.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;

@Data
@ApiModel("三字母返回实体")
@TableName("lsp_wx")
@NoArgsConstructor
@AllArgsConstructor
public class LspWx {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String lspName;
    private String coorDinate;
    private String salary;
    private String email;
    @Max(value = 150,message = "所填年龄值超出最大值")
    @Min(value = 1,message = "所填年龄值最小为1")
    private Integer age;
    private Integer isHaveNarcissistic;
    private Integer isHavePicture;
    private Integer sex;
    private String traffic;
    private Integer girlFriend;
    private Integer room;
    private Integer tag;
    private String centimeter;
    private String remark;
}
