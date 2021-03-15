package com.xwl.showserver.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xwl.showserver.entity.LspWx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LspMapper extends BaseMapper<LspWx> {
    List<LspWx> findLspList(@Param("current") Integer current, @Param("size")Integer size, @Param("name")String name);

    Integer findCountByParams(@Param("current")Integer current, @Param("size")Integer size, @Param("name")String name);
    @Select("select lsp_name from lsp_wx where lsp_name=#{lspName}")
    String findIsHaveName(@Param("lspName") String lspName);
}
