package com.xinput.wechat.util;

import com.google.common.collect.Lists;
import com.xinput.bleach.util.SecureUtils;
import com.xinput.bleach.util.StringUtils;
import com.xinput.bleach.util.date.LocalDateTimeUtils;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.enums.SignTypeEnum;
import com.xinput.wechat.exception.WechatException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-18 13:57
 */
public class WechatPayUtils {

    private static final DateTimeFormatter DATE_TIME_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    /**
     * 生成支付所需要的随机字符串
     */
    public static String createNonce() {
        return LocalDateTimeUtils.format(LocalDateTime.now(), DATE_TIME_TIMESTAMP_FORMATTER) + RandomStringUtils.getRandom(8);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param paramMap 待签名数据
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, Object> paramMap, SignTypeEnum signType) throws Exception {
        ArrayList<String> list = Lists.newArrayListWithCapacity(paramMap.size());
        paramMap.forEach((paramKey, paramValue) -> {
            // key不需要用于计算签名
            if (StringUtils.equalsIgnoreCase("key", paramKey) || StringUtils.equalsIgnoreCase("sign", paramKey)) {
                return;
            }

            // 值为空的数据也不需要用于计算md5的值
            if (paramValue == null || StringUtils.isNullOrEmpty(String.valueOf(paramValue))) {
                return;
            }

            list.add(paramKey + "=" + paramValue + "&");
        });

        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);

        StringBuilder sb = new StringBuilder();
        Arrays.stream(arrayToSort).forEach(arr -> sb.append(arr));

        sb.append("key=").append(WechatConfig.getWechatApiKey());

        if (SignTypeEnum.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), WechatConfig.getWechatApiKey()).toUpperCase();
        } else {
            return MD5(sb.toString()).toUpperCase();
        }
    }

    /**
     * 签名字符串
     *
     * @param text 需要签名的字符串
     * @return 签名结果
     */
    public static String MD5(String text) {
        return SecureUtils.MD5Encode(text);
    }

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data) throws Exception {
        if (StringUtils.isNullOrEmpty(WechatConfig.getWechatApiKey())) {
            throw new WechatException("微信商户API秘钥不能为空...请检查[system.properties]文件");
        }
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(WechatConfig.getWechatApiKey().getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @param key  密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
}
