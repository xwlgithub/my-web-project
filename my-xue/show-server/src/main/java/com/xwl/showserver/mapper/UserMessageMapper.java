package com.xwl.showserver.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xwl.showserver.entity.UserMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMessageMapper extends BaseMapper<UserMessage> {
    @Select("select * from  user_message")
    List<UserMessage> findAllUserMessages();
}
