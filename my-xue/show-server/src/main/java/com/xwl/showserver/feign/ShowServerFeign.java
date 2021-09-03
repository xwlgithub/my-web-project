package com.xwl.showserver.feign;

import com.xwl.showserver.entity.LspWx;
import com.xwl.showserver.feign.backs.ShowServerFeignFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xueWenLiang
 * @date 2021/8/31 10:54
 * @Description show-server提供feign调用接口
 */
@FeignClient(name = "show-server", fallback = ShowServerFeignFallback.class)
public interface ShowServerFeign {

    /**
     * 测试数据新增
     * @param lspWx
     * @return
     */
    @PostMapping("saveLsp")
    Boolean saveLsp(@RequestBody LspWx lspWx);
}
