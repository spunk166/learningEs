package com.fangcai.es.practise1_2020_06_09.controller;


import com.alibaba.fastjson.JSON;
import com.fangcai.es.practise1_2020_06_09.common.exception.EsDemoException;
import com.fangcai.es.practise1_2020_06_09.common.response.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author lc
 * @version 1.0
 * @date 2019/6/11 16:25
 */
@RestControllerAdvice
public class GlobalExceptionController {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);




    /**
     * 自定义抛出的错误处理
     * @param ex
     * @return
     */
    @ExceptionHandler (EsDemoException.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMsg EsDemoException(EsDemoException ex){
        return new ResponseMsg(ex.getCode(), ex.getMessage(), ex.getMsg());
    }


    /**
     * 不知名异常抛出的处理
     * @param ex
     * @return
     */
    @ExceptionHandler (Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMsg Ex(Exception ex){
        logger.error(JSON.toJSONString(ex));
         return new ResponseMsg(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getLocalizedMessage(),
                 "服务器异常，请联系管理员");
    }
}
