package com.xinput.wechat.util;

import com.google.common.collect.Lists;
import com.xinput.bleach.util.SecureUtils;
import com.xinput.bleach.util.StringUtils;
import com.xinput.bleach.util.bean.BeanMapUtils;
import com.xinput.bleach.util.date.LocalDateTimeUtils;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.exception.WechatException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-18 13:57
 */
public class WechatPayUtils {

    private static final String SUB_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int SUB_SET_LENGTH = SUB_SET.length();

    private static final DateTimeFormatter DATE_TIME_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    /**
     * 生成支付所需要的随机字符串
     */
    public static String createNonce() {
        return LocalDateTimeUtils.format(LocalDateTime.now(), DATE_TIME_TIMESTAMP_FORMATTER) + getRandom(8);
    }

    /**
     * 生成指定长度随机字符串
     */
    public static String getRandom(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(SUB_SET_LENGTH);
            sb.append(SUB_SET.charAt(index));
        }

        return sb.toString();
    }

    /**
     * 签名字符串
     *
     * @param text 需要签名的字符串
     * @param key  密钥
     * @return 签名结果
     */
    public static String md5Sign(String text, String key) {
        text = text + "&key=" + key;
        return SecureUtils.MD5Encode(text);
    }

    /**
     * 签名算法:对该对象进行MD5签名
     * 使用对象中自带的key或默认的key计算
     *
     * @param obj 要进行计算的对象
     * @return
     */
    public static String md5Sign(Object obj) {
        return md5Sign(BeanMapUtils.toMap(obj));
    }

    /**
     * 签名算法:对该对象进行MD5签名
     * 使用传入进来的key计算
     *
     * @param obj 要进行计算的对象
     * @param key 微信商户秘钥
     * @return
     */
    public static String md5Sign(Object obj, String key) {
        return md5Sign(BeanMapUtils.toMap(obj), key);
    }

    /**
     * 签名算法
     *
     * @param paramMap
     * @return
     */
    public static String md5Sign(Map<String, Object> paramMap) {
        if (StringUtils.isNullOrEmpty(WechatConfig.getWechatApiKey())) {
            throw new WechatException("微信商户API秘钥不能为空...请检查[system.properties]文件");
        }

        return md5Sign(paramMap, WechatConfig.getWechatApiKey());
    }

    /**
     * 签名算法: 微信的签名算法需要两个key，一个key放在末尾处用于标记
     * 见微信开发文档: 签名算法(https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3)
     *
     * @param paramMap 要参与签名的数据
     * @param key      商户key
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String md5Sign(Map<String, Object> paramMap, String key) {
        if (StringUtils.isNullOrEmpty(key)) {
            throw new WechatException("微信商户API秘钥不能为空...");
        }

        ArrayList<String> list = Lists.newArrayListWithCapacity(paramMap.size());
        paramMap.forEach((paramKey, paramValue) -> {
            // key不需要用于计算签名
            if (StringUtils.equalsIgnoreCase("key", paramKey)) {
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

        sb.append("key=").append(key);
        return SecureUtils.MD5Encode(sb.toString()).toUpperCase();
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
