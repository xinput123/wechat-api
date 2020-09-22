package com.xinput.wechat;

import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.XmlUtils;
import com.xinput.bleach.util.bean.BeanMapUtils;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.enums.PayUrlEnum;
import com.xinput.wechat.enums.SignTypeEnum;
import com.xinput.wechat.request.pay.CloseOrderRequest;
import com.xinput.wechat.request.pay.DownloadBillRequest;
import com.xinput.wechat.request.pay.MicroPayRequest;
import com.xinput.wechat.request.pay.OrderQueryRequest;
import com.xinput.wechat.request.pay.RefundQueryRequest;
import com.xinput.wechat.request.pay.RefundRequest;
import com.xinput.wechat.request.pay.SandboxSignKeyRequest;
import com.xinput.wechat.request.pay.UnifiedOrderRequest;
import com.xinput.wechat.response.pay.CloseOrderResponse;
import com.xinput.wechat.response.pay.MicroPayResponse;
import com.xinput.wechat.response.pay.OrderQueryResponse;
import com.xinput.wechat.response.pay.RefundQueryResponse;
import com.xinput.wechat.response.pay.RefundResponse;
import com.xinput.wechat.response.pay.SandboxSignKeyResponse;
import com.xinput.wechat.response.pay.UnifiedOrderResponse;
import com.xinput.wechat.util.WechatHttpUtils;
import com.xinput.wechat.util.WechatPayUtils;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-16 17:52
 */
public class WechatPayApi {

    private static final Logger logger = Logs.get();

    private static String getDomain() {
        if (WechatConfig.getUseSandbox()) {
            return PayUrlEnum.SANDBOX_DOMAIN.getUrl();
        } else {
            return PayUrlEnum.DOMAIN.getUrl();
        }
    }

    public static SandboxSignKeyResponse getSandboxnewSignKey(SandboxSignKeyRequest sandboxSignKeyRequest) throws Exception {
        sandboxSignKeyRequest.setSign(
                WechatPayUtils.generateSignature(BeanMapUtils.toMap(sandboxSignKeyRequest), SignTypeEnum.MD5));

        String result = WechatHttpUtils.execute(getDomain() + PayUrlEnum.GET_SIGNKEY.getUrl(), XmlUtils.toXml(sandboxSignKeyRequest), false);
        System.out.println(result);
        return XmlUtils.toBean(result, SandboxSignKeyResponse.class);
    }

    /**
     * 付款码付款
     */
    public static MicroPayResponse microPay(MicroPayRequest microPayRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.MICROPAY.getUrl(), microPayRequest);
        return XmlUtils.toBean(result, MicroPayResponse.class);
    }

    /**
     * 统一下单 - 不需要证书
     *
     * @param unifiedOrderRequest
     * @return
     * @throws Exception
     */
    public static UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest unifiedOrderRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.UNIFIED_ORDER.getUrl(), unifiedOrderRequest);
        return XmlUtils.toBean(result, UnifiedOrderResponse.class);
    }

    /**
     * 查询订单 - 不需要证书
     *
     * @param orderQueryRequest
     * @return
     */
    public static OrderQueryResponse orderQuery(OrderQueryRequest orderQueryRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.ORDER_QUERY.getUrl(), orderQueryRequest);
        return XmlUtils.toBean(result, OrderQueryResponse.class);
    }

    /**
     * 关闭订单 - 不需要证书
     */
    public static CloseOrderResponse closeOrder(CloseOrderRequest closeOrderRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.CLOSE_ORDER.getUrl(), closeOrderRequest);
        return XmlUtils.toBean(result, CloseOrderResponse.class);
    }

    /**
     * 申请退款 - 需要证书
     */
    public static RefundResponse refund(RefundRequest refundRequest) throws Exception {
        String result = WechatHttpUtils.withCertPost(getDomain() + PayUrlEnum.REFUND.getUrl(), refundRequest);
        return XmlUtils.toBean(result, RefundResponse.class);
    }

    /**
     * 查询退款 - 不需要证书
     */
    public static RefundQueryResponse refundQuery(RefundQueryRequest refundQueryRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.REFUND_QUERY.getUrl(), refundQueryRequest);
        return XmlUtils.toBean(result, RefundQueryResponse.class);
    }

    /**
     * 下载交易账单
     */
    public static void downloadBill(DownloadBillRequest downloadBillRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.DOWNLOAD_BILL.getUrl(), downloadBillRequest);
        System.out.println(result);
    }

}
