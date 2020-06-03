package com.xwl.comserver.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/3 09:18
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException  extends RuntimeException{
private ExceptionEnum exceptionEnum;

}
