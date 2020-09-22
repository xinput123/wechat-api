package com.xinput.wechat.util;

import com.xinput.bleach.util.StringUtils;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-22 10:06
 */
public class RandomStringUtils extends org.apache.commons.lang3.RandomStringUtils {

    /**
     * 获取随机生成的字符串
     *
     * @param count 指定长度
     * @return
     */
    public static String getRandom(int count) {
        if (count < 1) {
            return StringUtils.EMPTY;
        }

        return randomAlphanumeric(count);
    }

}
