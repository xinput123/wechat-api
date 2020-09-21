package com.xinput.wechat.exception;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-16 18:33
 */
public class WechatException extends RuntimeException {

    public WechatException() {
    }

    public WechatException(String message) {
        super(message);
    }

    public WechatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatException(Throwable cause) {
        super(cause);
    }

    public WechatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
