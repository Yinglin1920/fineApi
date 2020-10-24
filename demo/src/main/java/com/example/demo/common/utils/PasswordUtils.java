package com.example.demo.common.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import org.bouncycastle.util.encoders.Base64;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 15:07
 * @desc
 */
public class PasswordUtils {

    // bouncycastle算法名称
    public static final String SECURITY_PROVIDER_NAME = "BC";
    // SHA算法
    public static final String HASH_ALGORITHM_NAME = "PBKDF2WithHmacSHA1";
    // 生成Hash值长度
    private static final int HASH_SIZE = 256;
    // 生成盐值长度
    private static final int SALT_SIZE = 32;
    // 计算迭代次数
    private static final int ITERATIONS = 32;
    // 默认
    public static final String defaultPassword = "Ytbitsys3#*!";

    /**
     * 获取Salt
     *
     * @return Salt
     */
    public static String salt() {
        return Base64.toBase64String(new SecureRandom().generateSeed(SALT_SIZE));
    }

    /**
     * 计算密码的散列值（不可逆）
     *
     * @param plainText 明文
     * @param salt      盐值
     * @return 密码
     */
    public static String cipher(String plainText, String salt) {
        char[] chars = plainText.toCharArray();
        byte[] saltBytes = Base64.decode(salt);
        PBEKeySpec pbeKeySpec = new PBEKeySpec(chars, saltBytes, ITERATIONS, HASH_SIZE);
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM_NAME, SECURITY_PROVIDER_NAME);
            byte[] hashBytes = factory.generateSecret(pbeKeySpec).getEncoded();
            return Base64.toBase64String(hashBytes);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

}
