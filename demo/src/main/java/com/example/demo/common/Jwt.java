package com.example.demo.common;


import com.example.demo.common.utils.DateUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTParser;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/11 17:29
 * @desc
 */
public class Jwt {

    /**
     * 秘钥
     */
    private static final byte[] SECRET = "3d990d2276917dfac04467df11fff26d".getBytes();

    /**
     * 初始化head部分的数据为
     * {
     * "alg":"HS256",
     * "type":"JWT"
     * }
     */
    private static final JWSHeader HEADER = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);

    /**
     * 生成token，该方法只在用户登录成功后调用
     *
     * @param payload 集合，可以存储用户id，token生成时间，token过期时间等自定义字段
     * @return token字符串, 若失败则返回null
     */
    public static String createToken(Map<String, Object> payload) {
        String tokenString = null;
        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(HEADER, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(SECRET));
            tokenString = jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("签名失败:" + e.getMessage());
            e.printStackTrace();
        }
        return tokenString;
    }


    /**
     * 校验token是否合法，返回Map集合,集合中主要包含    state状态码   data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     *
     * @param token
     * @return Map<String, Object>
     */
    public static Map<String, Object> validTokens(String token) {
        Map<String, Object> resultMap = new HashMap<String, Object>(2);
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(SECRET);
            if (jwsObject.verify(verifier)) {
                JSONObject jsonObj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                resultMap.put("state", TokenState.VALID.toString());
                // 若payload包含ext字段，则校验是否过期
                String exp = "exp";
                if (jsonObj.containsKey(exp)) {
                    long expTime = Long.valueOf(jsonObj.get("exp").toString());
                    long curTime = System.currentTimeMillis();
                    // 过期了
                    if (curTime > expTime) {
                        resultMap.clear();
                        resultMap.put("state", TokenState.EXPIRED.toString());
                    }
                }
                resultMap.put("data", jsonObj);

            } else {
                // 校验失败
                resultMap.put("state", TokenState.INVALID.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // token格式不合法导致的异常
            resultMap.clear();
            resultMap.put("state", TokenState.INVALID.toString());
        }
        return resultMap;
    }


    /**
     * 生成token的方法
     * @param uid 需要保存的数据对象字符串
     * @param expTime 有效时间 默认为2小时
     * @return
     */
    public static String getToken(String uid,Long expTime,Object auth){
        //获取生成token
        Map<String, Object> map = new HashMap<>(3);
        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", uid);
        // 添加当前用户的权限信息
        map.put("AuthUser",auth);

        long time= System.currentTimeMillis();
        long exptime;
        if(expTime == null || expTime < 0L ){
            exptime= DateUtils.addHour(new Date(time),24).getTime() ;
        }else{
            exptime=time+expTime;
        }
        //生成时间
        map.put("sta", System.currentTimeMillis());
        //过期时间
        map.put("exp", exptime);
        try {
            String token = Jwt.createToken(map);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成token方法， 有效时间 默认为2小时
     * @param uid
     * @return
     */
    public static String getToken(String uid,Object AuthUser){
        return getToken(uid,null,AuthUser);
    }

    /**
     * 验证token方法
     * @param token
     * @return
     */
    public static Boolean validToken(String token){
        //解析token
        if (ObjectHelper.isNotEmpty(token)) {
            Map<String, Object> validMap = validTokens(token);
            String state=(String)validMap.get("state");
            if(TokenState.VALID.toString().equals(state)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 解析token
     * @param idToken
     * @return
     * @throws ParseException
     */
    public static Map<String,Object> parseToken (String idToken) throws ParseException {
        JWT jwt = JWTParser.parse(idToken);
        Map<String,Object> map=jwt.getJWTClaimsSet().getClaims();
        return map;
    }
}
