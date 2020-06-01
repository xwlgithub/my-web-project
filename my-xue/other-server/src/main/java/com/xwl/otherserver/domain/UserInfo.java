package com.xwl.otherserver.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String id;
    private String name;
    private String password;
    private String callNum;
    private String remark;
    private String roleId;
    @JsonFormat(pattern = "yyyy-MM_dd HH:mm:ss")
    private Date createTime;
}
