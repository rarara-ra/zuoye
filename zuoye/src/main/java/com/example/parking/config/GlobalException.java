package com.example.parking.config;

import com.example.parking.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 全局异常处理,全局异常拦截（拦截项目中的所有异常）
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler
    public R<Object> handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.error("全局异常捕获", e);
        // 返回给前端
        return R.error(e.getMessage());
    }

}
