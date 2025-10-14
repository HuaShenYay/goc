package com.gs.shop.erp.infrustructure.auth;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.gs.shop.erp.infrustructure.result.ResultCode;
import com.gs.shop.erp.model.entity.SysUserEntity;
import com.gs.shop.erp.model.vo.SysUserVo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 会话服务
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 */
@Service
public class SessionService {

    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    @Value("${jwtSecret:h134rja80sy41214agjsmm1g6y1phwkh}")
    private String jwtSecret;
    @Value("${ttlMillis:100000000}")
    private int ttlMillis = 100000000;
    
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public SysUserVo getLoginUser() {
        return getUser();
    }

    /**
     * 校验登录
     *
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void checkLogin() {
        //获取请求attributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //定义获取Token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //判断是否为空
        Assert.notBlank(token, ResultCode.NOT_LOGIN.getMsg());
    }

    /**
     * 生成Token
     *
     * @param user 被加密的用户
     * @return java.lang.String
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public String createToken(SysUserVo user) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = Maps.newHashMap();
        user.setPassword(null);
        claims.put("loginUser", JSONUtil.toJsonStr(user));
        //生成签发人
        String subject = "gs-personal-manage";
        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(getSigningKey(), signatureAlgorithm);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 获取用户ID
     *
     * @return java.lang.Integer
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public Integer getUserId() {
        SysUserVo user = getUser();
        return user != null ? user.getId() : null;
    }

    /**
     * 获取用户
     *
     * @return com.gs.shop.erp.model.vo.SysUserVo
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    private SysUserVo getUser() {
        //获取请求attributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //定义获取Token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //判断是否为空
        if (StrUtil.isBlank(token)) {
            return new SysUserVo();
        }
        try {
            //解析Token
            //解析token返回
            Claims claims = Jwts.parserBuilder()
                    //设置签名的秘钥
                    .setSigningKey(getSigningKey())
                    //设置需要解析的jwt
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return JSONUtil.parse(claims.get("loginUser")).toBean(SysUserVo.class);
        } catch (MalformedJwtException e) {
            // JWT格式错误，返回空用户
            logger.warn("JWT格式错误: {}", e.getMessage());
            return new SysUserVo();
        } catch (ExpiredJwtException e) {
            // JWT过期，返回空用户
            logger.warn("JWT已过期: {}", e.getMessage());
            return new SysUserVo();
        } catch (UnsupportedJwtException e) {
            // 不支持的JWT，返回空用户
            logger.warn("不支持的JWT: {}", e.getMessage());
            return new SysUserVo();
        } catch (IllegalArgumentException e) {
            // 非法参数，返回空用户
            logger.warn("非法参数: {}", e.getMessage());
            return new SysUserVo();
        } catch (Exception e) {
            // 其他异常，返回空用户
            logger.warn("解析JWT时发生异常: {}", e.getMessage());
            return new SysUserVo();
        }
    }
}