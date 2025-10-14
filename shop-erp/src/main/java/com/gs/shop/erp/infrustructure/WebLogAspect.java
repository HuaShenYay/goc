package com.gs.shop.erp.infrustructure;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.gs.shop.erp.infrustructure.annotation.IgnoreLog;
import com.gs.shop.erp.infrustructure.auth.SessionService;
import com.gs.shop.erp.infrustructure.result.PageResult;
import com.gs.shop.erp.infrustructure.result.Result;
import com.gs.shop.erp.model.entity.OperateLogEntity;
import com.gs.shop.erp.model.vo.SysUserVo;
import com.gs.shop.erp.service.OperateLogService;
import com.gs.shop.erp.infrustructure.util.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 日志打印切面
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023/11/29
 */
@Aspect
@Configuration
@SuppressWarnings("ALL")
@RequiredArgsConstructor
public class WebLogAspect implements InitializingBean, DisposableBean {

    private final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    private ThreadPoolTaskExecutor executor;
    private final SessionService sessionService;
    private final OperateLogService operateLogService;


    @Around("@annotation(operation)")
    public Object around(ProceedingJoinPoint pjp, ApiOperation operation) throws Throwable {
        //记录请求信息
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        //判断方法是否忽略
        if (method.isAnnotationPresent(IgnoreLog.class)) {
            return pjp.proceed();
        }
        //获取描述（如果是继承自BaseApi则需要替换描述内容）
        Api api = pjp.getTarget().getClass().getAnnotation(Api.class);
        //定义方法描述
        String methodDesc = operation.value();
        if (methodDesc.contains(StrUtil.DELIM_START + StrUtil.DELIM_END)) {
            methodDesc = methodDesc.replace("{}", ArrayUtil.isEmpty(api.tags()) ? StrUtil.EMPTY : api.tags()[0]);
        }
        //获取登录用户
        SysUserVo sessionUser = sessionService.getLoginUser();
        //前置日志
        String params = getParams(pjp, method);
        logger.info("进入方法：{}，参数：{}", methodDesc, params);
        
        // 只有当用户ID不为空时才记录用户ID
        if (sessionUser != null && sessionUser.getId() != null) {
            logger.info("用户ID：{}", sessionUser.getId());
        } else {
            logger.info("用户未登录或用户ID为空");
        }

        //记录请求时长
        LocalDateTime startDateTime = LocalDateTime.now();
        // 以上为：方法执行前
        Object result = pjp.proceed();
        // 以下为：方法执行后
        LocalDateTime endDateTime = LocalDateTime.now();
        //持久化日志
        saveLog(sessionUser, methodDesc, params);
        //定义消息
        Object message = null;
        //判断返回类型
        if (result instanceof Result) {
            message = ((Result<?>) result).getMsg();
        }
        if (result instanceof PageResult) {
            message = ((PageResult<?>) result).getMsg();
        }
        if (message == null) {
            message = JSONUtil.toJsonStr(result);
        }

        //后置日志
        logger.info("结束方法：{}，用时：{}秒，执行结果：{}", methodDesc, LocalDateTimeUtil.between(startDateTime, endDateTime).getSeconds(), message);

        return result;
    }

    /**
     * 获取请求参数
     *
     * @param pjp    切入点
     * @param method 方法
     * @return java.lang.String
     * @author Guo Shuai
     * @date 2023/11/29
     * @since 1.0
     **/
    private String getParams(ProceedingJoinPoint pjp, Method method) {
        Object[] args = pjp.getArgs();
        Parameter[] parameters = method.getParameters();
        Map<String, Object> params = MapUtil.newHashMap();
        for (int i = 0; i < parameters.length; i++) {
            params.put(parameters[i].getName(), args[i]);
        }
        return JSONUtil.toJsonStr(params);
    }

    /**
     * 保存日志
     *
     * @param sysUser    当前登录用户
     * @param methodDesc 方法描述
     * @param params     参数
     * @return void
     * @author Guo Shuai
     * @date 2023/11/29
     * @since 1.0
     **/
    private void saveLog(SysUserVo sysUser, String methodDesc, String params) {
        //获取当前请求对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNull(requestAttributes)) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //异步保存
        this.executor.submit(() -> {
            OperateLogEntity apiLog = new OperateLogEntity();
            apiLog.setInsertDate(LocalDateTime.now());
            // 只有当用户不为空时才设置用户信息
            if (sysUser != null) {
                apiLog.setPhone(sysUser.getPhone());
                apiLog.setUserId(sysUser.getId());
                apiLog.setUsername(sysUser.getName());
            }
            apiLog.setActionName(methodDesc);
            apiLog.setRemoteAddress(IpUtil.getIpAddr(request));
            apiLog.setMethod(request.getMethod().toUpperCase());
            apiLog.setRequestParam(params);
            apiLog.setRequestUrl(request.getRequestURI());
            apiLog.setReferrer(Optional.ofNullable(request.getHeader("Referer")).map(item -> item.split("\\?")[0]).orElse(StrUtil.EMPTY));
            apiLog.setUserAgent(Opt.ofNullable(request.getHeader("User-Agent")).orElse(StrUtil.EMPTY));
            operateLogService.save(apiLog);
        });
    }

    @Override
    public void destroy() throws Exception {
        this.executor.destroy();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(1);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("log-event-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();
    }
}