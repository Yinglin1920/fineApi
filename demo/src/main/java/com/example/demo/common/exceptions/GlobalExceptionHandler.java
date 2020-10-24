package com.example.demo.common.exceptions;

import com.example.demo.common.RestResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/23 13:26
 * @desc
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 缺失请求参数处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public RestResultDto handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        String message = "缺失请求参数" + e.getParameterName();
        return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    /**
     * 请求参数类型错误处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public RestResultDto handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String message = "请求参数" + e.getName() + "类型错误";
        return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    /**
     * 参数类型错误异常类型处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public RestResultDto handleHttpMessageNotReadableException(HttpMessageConversionException e, HttpServletRequest request) {
        String message = "参数类型错误";
        return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    /**
     * 空指针异常处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public RestResultDto handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        String message = "空指针异常";
        return ackTransfer(request, message, HttpStatus.BAD_REQUEST.value(), e, true);
    }

    /**
     * MethodArgumentNotValidException 异常处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResultDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        StringBuilder errorMsg = new StringBuilder();
        BindingResult re = e.getBindingResult();
        for (ObjectError error : re.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage()).append(",");
        }
        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
        return ackTransfer(request, errorMsg.toString(), -1, e, false);
    }

    /**
     * 绑定异常处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RestResultDto handleBindException(BindException e, HttpServletRequest request) {
        BindingResult result = e.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage()).append(",");
        }
        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
        return ackTransfer(request, errorMsg.toString(), -1, e, false);
    }


    /**
     * 自定义异常类型异常消息处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({RestException.class})
    @ResponseBody
    public RestResultDto handleDataException(RestException e, HttpServletRequest request) {
        String message = e.getPrompt();
        Integer code = e.getCode();
        return ackTransfer(request, message, code, e, true);
    }

    /**
     * IO异常处理
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({IOException.class})
    @ResponseBody
    public RestResultDto handleIOException(IOException e,HttpServletRequest request){
        String message="IO异常错误!";
        Integer code=Integer.valueOf(500);
        return ackTransfer(request, message, code, e, true);
    }

    /**
     * 处理运行时异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public RestResultDto handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        return ackTransfer(request, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e, true);
    }

    /**
     * 默认异常处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResultDto handleException(Exception e, HttpServletRequest request) {
        return ackTransfer(request, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e, true);
    }

    private RestResultDto ackTransfer(HttpServletRequest request, String message, Integer code, Exception e, boolean printStackTrace) {
        RestResultDto result = new RestResultDto();
        result.setCode(code);
        result.setMsg(message);
        if (printStackTrace) {
            log.error(message, e);
        } else {
            log.error(message);
        }
        return result;
    }

    private RestResultDto ackTransfer(HttpServletRequest request, String message, Integer code, Exception e) {
        return ackTransfer(request, message, code, e, false);
    }
}
