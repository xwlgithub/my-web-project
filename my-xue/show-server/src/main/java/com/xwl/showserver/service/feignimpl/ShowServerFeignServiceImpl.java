package com.xwl.showserver.service.feignimpl;

import com.xwl.showserver.entity.LspWx;
import com.xwl.showserver.feign.ShowServerFeign;
import com.xwl.showserver.mapper.LspMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xueWenLiang
 * @date 2021/8/31 11:01
 * @Description feign接口业务实现类
 */
@Service
@AllArgsConstructor
public class ShowServerFeignServiceImpl implements ShowServerFeign {
    private LspMapper lspMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveLsp(LspWx lspWx) {
        return lspMapper.insert(lspWx) > 0 ? true : false;
    }
}
