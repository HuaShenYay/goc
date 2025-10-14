package com.gs.shop.erp.infrustructure;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gs.shop.erp.infrustructure.result.Result;
import com.gs.shop.erp.infrustructure.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 全局系统异常处理
 * 调整异常处理的HTTP状态码，丰富异常处理类型
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 表单绑定到 java bean 出错时抛出 BindException 异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public <T> Result<T> processException(BindException e) {
        e.printStackTrace();
        return Result.failed(ResultCode.PARAM_ERROR, e.getAllErrors().get(0).getDefaultMessage());
    }


    /**
     * `Bean Validation`中的异常信息抓取
     * 针对：控制层中`API`的方法中字段校验规则失败时，异常信息抓取
     *
     * @param exception ConstraintViolationException
     * @return 异常信息
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<String> exception(ConstraintViolationException exception) {
        exception.printStackTrace();
        ConstraintViolation<?> constraintViolation = exception.getConstraintViolations().stream().findFirst().get();
        String message = constraintViolation.getMessage();
        return Result.failed(message);
    }


    /**
     * 错误的SQL语句异常
     *
     * @param exception BadSqlGrammarException
     * @return 异常信息
     */
    @ExceptionHandler({BadSqlGrammarException.class})
    public Result<String> exception(BadSqlGrammarException exception) {
        exception.printStackTrace();
        return Result.failed("系统内部错误,错误的SQL语句");
    }

    /**
     * 字段超出长度异常
     *
     * @param exception MysqlDataTruncation
     * @return 异常信息
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    public Result<String> exception(DataIntegrityViolationException exception) {
        exception.printStackTrace();
        //获取真实类型
        Throwable cause = exception.getCause();
        return Result.failed(cause.getMessage());
    }

    /**
     * `Bean Validation`中的异常信息抓取
     * 针对：实体类对象中字段校验规则失败时，异常信息抓取
     *
     * @param exception MethodArgumentNotValidException
     * @return 异常信息
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<String> exception(MethodArgumentNotValidException exception) {
        exception.printStackTrace();
        List<ObjectError> objectErrorList = exception.getBindingResult().getAllErrors();
        ObjectError objectError = objectErrorList.stream().findFirst().get();
        String message = objectError.getDefaultMessage();
        return Result.failed(message);
    }

    /**
     * NoHandlerFoundException
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public <T> Result<T> processException(NoHandlerFoundException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Result.failed(ResultCode.RESOURCE_NOT_FOUND);
    }

    /**
     * MissingServletRequestParameterException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public <T> Result<T> processException(MissingServletRequestParameterException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        String paramName = e.getParameterName();
        String message = String.format("请求参数 '%s' 不能为空", paramName);
        return Result.failed(ResultCode.PARAM_IS_NULL, message);
    }

    /**
     * MethodArgumentTypeMismatchException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public <T> Result<T> processException(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        String paramName = e.getName();
        String message = String.format("参数 '%s' 类型错误", paramName);
        return Result.failed(ResultCode.PARAM_ERROR, message);
    }

    /**
     * ServletException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServletException.class)
    public <T> Result<T> processException(ServletException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public <T> Result<T> handleIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        log.error("非法参数异常，异常原因：{}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonProcessingException.class)
    public <T> Result<T> handleJsonProcessingException(JsonProcessingException e) {
        e.printStackTrace();
        log.error("Json转换异常，异常原因：{}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    /**
     * HttpMessageNotReadableException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> Result<T> processException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        String errorMessage = "请求体不可为空";
        Throwable cause = e.getCause();
        if (cause != null) {
            errorMessage = convertMessage(cause);
        }
        return Result.failed(errorMessage);
    }

    /**
     * TypeMismatchException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public <T> Result<T> processException(TypeMismatchException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public <T> Result<T> processSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        String errorMsg = e.getMessage();
        if (StrUtil.isNotBlank(errorMsg) && errorMsg.contains("denied to user")) {
            return Result.failed("数据库用户无操作权限，建议本地搭建数据库环境");
        } else {
            return Result.failed(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompletionException.class)
    public <T> Result<T> processException(CompletionException e) {
        e.printStackTrace();
        if (e.getMessage().startsWith("feign.FeignException")) {
            return Result.failed("微服务调用异常");
        }
        return handleException(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public <T> Result<T> handleException(Exception e) {
        e.printStackTrace();
        if (e.getCause() instanceof IllegalArgumentException) {
            return handleIllegalArgumentException((IllegalArgumentException) e.getCause());
        }
        return Result.failed(e.getMessage());
    }

    /**
     * 传参类型错误时，用于消息转换
     *
     * @param throwable 异常
     * @return 错误信息
     */
    private String convertMessage(Throwable throwable) {
        String error = throwable.toString();
        String regulation = "\\[\"(.*?)\"]+";
        Pattern pattern = Pattern.compile(regulation);
        Matcher matcher = pattern.matcher(error);
        String group = "";
        if (matcher.find()) {
            String matchString = matcher.group();
            matchString = matchString
                    .replace("[", "")
                    .replace("]", "");
            matchString = matchString.replaceAll("\\\"", "") + "字段类型错误";
            group += matchString;
        }
        return group;
    }
}
