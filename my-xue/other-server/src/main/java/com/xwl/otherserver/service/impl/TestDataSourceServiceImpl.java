package com.xwl.otherserver.service.impl;

import com.xwl.comserver.domain.UserInfo;
import com.xwl.otherserver.mapper.UserInfoMapper;
import com.xwl.otherserver.service.TestDataSourceService;
import com.xwl.showserver.entity.LspWx;
import com.xwl.showserver.feign.ShowServerFeign;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xueWenLiang
 * @date 2021/8/31 11:04:wq:wq
 *:WQ
 *:wq:
 * @Description 描述信息
 */
@Service
@RequiredArgsConstructor
public class TestDataSourceServiceImpl  implements TestDataSourceService {
    private final UserInfoMapper userInfoMapper;
    private  final  ShowServerFeign showServerFeign;
    @Transactional
    @Override
    public Boolean saveMesg() {
        LspWx lspWx = new LspWx();
        showServerFeign.saveLsp(lspWx);
        UserInfo userInfo = new UserInfo();
        userInfoMapper.insert(userInfo);
        return Boolean.TRUE;
    }
}
