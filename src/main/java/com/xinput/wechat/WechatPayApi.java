package com.xinput.wechat;

import com.google.common.collect.Lists;
import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
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
import com.xinput.wechat.response.pay.Coupon;
import com.xinput.wechat.response.pay.MicroPayResponse;
import com.xinput.wechat.response.pay.OrderQueryResponse;
import com.xinput.wechat.response.pay.RefundQueryResponse;
import com.xinput.wechat.response.pay.RefundResponse;
import com.xinput.wechat.response.pay.SandboxSignKeyResponse;
import com.xinput.wechat.response.pay.UnifiedOrderResponse;
import com.xinput.wechat.util.WechatHttpUtils;
import com.xinput.wechat.util.WechatPayUtils;
import com.xinput.wechat.util.WechatXmlUtils;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

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
        logger.info("result : [{}].", result);
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
     * 查询订单 - 根据微信订单号
     *
     * @param transactionId 微信订单号
     * @return
     */
    public static OrderQueryResponse orderQueryByTransactionId(String transactionId) throws Exception {
        OrderQueryRequest orderQueryRequest = BuilderUtils.of(OrderQueryRequest::new)
                .with(OrderQueryRequest::setTransaction_id, transactionId)
                .build();

        return orderQuery(orderQueryRequest);
    }

    /**
     * 查询订单 - 根据商户订单号
     *
     * @param outTradeNo 商户订单号
     * @return
     */
    public static OrderQueryResponse orderQueryByOutTradeNo(String outTradeNo) throws Exception {
        OrderQueryRequest orderQueryRequest = BuilderUtils.of(OrderQueryRequest::new)
                .with(OrderQueryRequest::setOut_trade_no, outTradeNo)
                .build();

        return orderQuery(orderQueryRequest);
    }

    /**
     * 查询订单
     *
     * @param orderQueryRequest 查询订单请求参数
     * @return
     */
    public static OrderQueryResponse orderQuery(OrderQueryRequest orderQueryRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.ORDER_QUERY.getUrl(), orderQueryRequest);
        Map<String, Object> params = WechatXmlUtils.toMap(result);
        OrderQueryResponse response = BeanMapUtils.toBean(params, OrderQueryResponse.class);

        if (!response.isSuccess()) {
            return response;
        }

        // 验证签名是否合法
        if (WechatPayUtils.isSignatureValid(params, SignTypeEnum.getSignType(orderQueryRequest.getSign_type()))) {
            throw new Exception(String.format("Invalid sign value in XML: %s", JsonUtils.toJsonString(response, true)));
        }

        Integer couponCount = response.getCoupon_count();
        if (couponCount == null || couponCount < 1) {
            return response;
        }

        // 对于微信支付返回的带有下标的 _0,_1,_2 类型参数进行封装
        List<Coupon> coupons = Lists.newArrayListWithCapacity(couponCount);
        for (int i = 0; i < couponCount; i++) {
            Coupon coupon = BuilderUtils.of(Coupon::new)
                    .with(Coupon::setIndex, i)
                    .with(Coupon::setCoupon_id, String.valueOf(params.get("coupon_id_" + i)))
                    .with(Coupon::setCoupon_type, String.valueOf(params.get("coupon_type_" + i)))
                    .with(Coupon::setCoupon_fee, Integer.valueOf(String.valueOf(params.get("coupon_fee_" + i))))
                    .with(Coupon::setCoupon_batch, String.valueOf(params.get("coupon_batch_id_" + i)))
                    .build();
            coupons.add(coupon);
        }

        response.setCoupons(coupons);
        return response;
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
