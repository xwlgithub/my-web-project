package com.xwl.showserver.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
