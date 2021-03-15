package com.xwl.showserver.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.comserver.utils.MyPage;
import com.xwl.showserver.entity.LspWx;
import com.xwl.showserver.mapper.LspMapper;
import com.xwl.showserver.service.ILspService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
@SuppressWarnings("all")
public class LspServiceImpl implements ILspService {
    private LspMapper lspMapper;

    @Override
    @SneakyThrows
    public MyPage<LspWx> findLspList(Integer current, Integer size, String name) {
        List<LspWx> lspWxList=lspMapper.findLspList((current-1)*size,size,name);
        Integer counds=lspMapper.findCountByParams((current-1)*size,size,name);
        return new MyPage<>(current,size,lspWxList,counds);
    }

    @Override
    @SneakyThrows
    public Boolean saveOrUpdate(LspWx lspWx) throws ApiException{
        if (StringUtils.isEmpty(lspWx.getId())){
            String lspName=lspMapper.findIsHaveName(lspWx.getLspName());
            if(lspName!=null)throw  new ApiException(ExceptionEnum.NAME_ISHAVA);
            lspMapper.insert(lspWx);
            return true;
        }
        lspMapper.updateById(lspWx);
        return true;
    }

    @Override
    @SneakyThrows
    public Boolean deleteById(Long id) {
        return lspMapper.deleteById(id)>0?true:false;
    }
}
