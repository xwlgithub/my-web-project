package com.xwl.comserver.utils;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: 薛
 * @Date: 2020/6/8 16:59
 * @Description:JWT-token认证签发与认证工具类
 */
@Component
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
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        } catch (MalformedJwtException e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        } catch (SignatureException e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        } catch (IllegalArgumentException e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return claims;
    }

}
