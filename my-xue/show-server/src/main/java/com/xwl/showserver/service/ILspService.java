package com.xwl.showserver.service;

import com.xwl.comserver.utils.MyPage;
import com.xwl.showserver.entity.LspWx;

public interface ILspService {
    MyPage<LspWx> findLspList(Integer current, Integer size, String name);

    Boolean saveOrUpdate(LspWx lspWx);

    Boolean deleteById(Long id);
}
