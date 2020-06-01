package com.xwl.otherserver.utils;

import lombok.Data;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/5/29 16:42
 * @Description:
 */
@Data
public class MyPage<T> {
    private Integer current;
    private Integer size;
    private Integer total;
    private Integer pageCounts;
    private List<T> seconds;

    /**
     * 封装类
     * @param current
     * @param size
     * @param seconds
     * @param total
     */
    public MyPage(Integer current, Integer size, List<T> seconds, Integer total){
        this.current=current;
        this.size=size;
        this.total=total;
        this.pageCounts=total%size==0?total/size:total/size+1;
        this.seconds=seconds;
    }
}
