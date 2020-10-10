package com.xinput.wechatpay;

import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.ObjectId;
import com.xinput.wechatpay.config.WechatConfig;
import com.xinput.wechatpay.enums.SignTypeEnum;
import com.xinput.wechatpay.exception.WechatPayException;
import com.xinput.wechatpay.request.MicroPayRequest;
import com.xinput.wechatpay.request.SandboxSignKeyRequest;
import com.xinput.wechatpay.response.MicroPayResponse;
import com.xinput.wechatpay.response.SandboxSignKeyResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 微信沙箱环境测试
 * <p>
 * 1、微信官方demo不能直接用，参数太少
 * 2、沙箱环境也是 正式账号，其signkey是根据正式key获取到的，沙箱只支持MD5加密，然后获取后带哦用借口时候需要替换正式key
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-21 11:16
 */
public class SandboxDemo {

    @Test
    public void createSandboxnewSignKey() throws WechatPayException {
        SandboxSignKeyResponse response = WechatPayApi.getSandboxnewSignKey();
        System.out.println(JsonUtils.toJsonString(response, true));
    }

    @Test
    public void getSandboxnewSignKey() throws WechatPayException {
        SandboxSignKeyRequest request = BuilderUtils.of(SandboxSignKeyRequest::new)
                .with(SandboxSignKeyRequest::setMch_id, WechatConfig.getWechatMchId())
                .with(SandboxSignKeyRequest::setNonce_str, ObjectId.stringId())
                .with(SandboxSignKeyRequest::setSign_type, SignTypeEnum.MD5.getType())
                .build();
        SandboxSignKeyResponse response = WechatPayApi.getSandboxnewSignKey(request);
        System.out.println(JsonUtils.toJsonString(response, true));
    }

    @Test
    public void getSandboxnewSignKeyException() {
        SandboxSignKeyRequest mchRequest = BuilderUtils.of(SandboxSignKeyRequest::new)
                .with(SandboxSignKeyRequest::setMch_id, WechatConfig.getWechatMchId())
                .build();

        // mch_id
        WechatPayException we = assertThrows(WechatPayException.class,
                () -> WechatPayApi.getSandboxnewSignKey(mchRequest));
        assertEquals("[nonce_str] 不能为空", we.getMessage());

    }

    /**
     * 付款码付款
     *
     * @throws Exception
     */
    @Test
    public void microPay() throws Exception {
        MicroPayRequest request = BuilderUtils.of(MicroPayRequest::new)
                .with(MicroPayRequest::setDevice_info, "手机收款")
                .with(MicroPayRequest::setNonce_str, "手机收款")
                .with(MicroPayRequest::setBody, "手机收款 - 100元")
                .with(MicroPayRequest::setOut_trade_no, ObjectId.stringId())
                .with(MicroPayRequest::setTotal_fee, 101)
                .with(MicroPayRequest::setSpbill_create_ip, "192.168.10.5")
                .with(MicroPayRequest::setAuth_code, "135373102661736702")
                .build();

        MicroPayResponse response = WechatPayApi.microPay(request);
        System.out.println(JsonUtils.toJsonString(response, true));

    }

}
