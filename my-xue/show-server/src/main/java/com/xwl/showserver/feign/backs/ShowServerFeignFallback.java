package com.xwl.showserver.feign.backs;

import com.xwl.showserver.entity.LspWx;
import com.xwl.showserver.feign.ShowServerFeign;
import org.springframework.stereotype.Service;

/**
 * @author xueWenLiang
 * @date 2021/8/31 10:58
 * @Description feign调用异常统一回执处理
 */
@Service
public class ShowServerFeignFallback  implements ShowServerFeign {
    @Override
    public Boolean saveLsp(LspWx lspWx) {
        return false;
    }
}
