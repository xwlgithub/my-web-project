package com.xwl.otherserver.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xwl.otherserver.domain.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleInfoMapper  extends BaseMapper<RoleInfo> {
    List<RoleInfo> findLimit(@Param("current") Integer current, @Param("size") Integer size, @Param("roleName") String roleName);

    Integer findCounts(@Param("roleName") String roleNam);
}
