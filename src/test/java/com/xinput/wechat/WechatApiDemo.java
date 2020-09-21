package com.xinput.wechat;

import com.xinput.bleach.util.JsonUtils;
import org.junit.Test;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-19 22:40
 */
public class WechatApiDemo {

    @Test
    public void test() {
        String code = "091Iwm000duYjK1PI1200fOFep3Iwm0R";
        System.out.println(JsonUtils.toJsonString(WechatApi.code2Session(code), true));
    }
}
