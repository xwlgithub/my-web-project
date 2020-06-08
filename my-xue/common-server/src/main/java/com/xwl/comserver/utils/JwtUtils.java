package com.xwl.comserver.utils;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Auther: 薛
 * @Date: 2020/6/8 16:59
 * @Description:JWT-token认证签发与认证工具类
 */
@Component
@NoArgsConstructor
public class JwtUtils {
    private static final String SIGN_NAME="xuewenliang";
    private static long mill=60000L;

    /**
     * 生成token
     * @param name
     * @param emailAdress
     * @return
     */
    public static String createTokenByParams(String name,String emailAdress){
        //当前时间-毫秒
        long now = System.currentTimeMillis();
        long exp=now+mill;
        JwtBuilder builder = Jwts.builder().setId(name)
                .setSubject(emailAdress)
                .signWith(SignatureAlgorithm.HS256, SIGN_NAME)
                .setIssuedAt(new Date())
                .setExpiration(new Date(exp));
       return builder.compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parseClaimsByToken(String token) throws ApiException {
        Claims claims=null;
        try {
             claims = Jwts.parser().setSigningKey(SIGN_NAME).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
           throw new ApiException(ExceptionEnum.TOKEN_IS_ERROR);
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return claims;
    }

    public static void main(String[] args) {
        String ss="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ4d2wiLCJzdWIiOiIyNTMwMTY0NjRAcXEuY29tIiwiaWF0IjoxNTkxNjA5OTcyLCJleHAiOjE1OTE2MTAwMzJ9.IFvI06rel5_D3ZLOZpmLqZjD_1va1bERcf37ZyeX3oU";
        Claims claims = null;
        try {
            claims = parseClaimsByToken(ss);
        } catch (ApiException e) {
            System.out.println(e.getExceptionEnum().toString());
        }
        System.out.println(claims);
    }
}
