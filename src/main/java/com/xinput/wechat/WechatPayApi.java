package com.xinput.wechat;

import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.XmlUtils;
import com.xinput.wechat.enums.PayUrlEnum;
import com.xinput.wechat.enums.SignTypeEnum;
import com.xinput.wechat.request.pay.CloseOrderRequest;
import com.xinput.wechat.request.pay.DownloadBillRequest;
import com.xinput.wechat.request.pay.OrderQueryRequest;
import com.xinput.wechat.request.pay.RefundQueryRequest;
import com.xinput.wechat.request.pay.RefundRequest;
import com.xinput.wechat.request.pay.UnifiedOrderRequest;
import com.xinput.wechat.response.pay.CloseOrderResponse;
import com.xinput.wechat.response.pay.OrderQueryResponse;
import com.xinput.wechat.response.pay.RefundQueryResponse;
import com.xinput.wechat.response.pay.RefundResponse;
import com.xinput.wechat.response.pay.UnifiedOrderResponse;
import com.xinput.wechat.util.WechatHttpUtils;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-16 17:52
 */
public class WechatPayApi {

    private static final Logger logger = Logs.get();

    /**
     * 统一下单 - 不需要证书
     *
     * @param unifiedOrderRequest
     * @return
     * @throws Exception
     */
    public static UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest unifiedOrderRequest) throws Exception {
        // 设置有效的验证方式
        unifiedOrderRequest.setSign_type(SignTypeEnum.getAllowSign(unifiedOrderRequest.getSign_type()));

        UnifiedOrderResponse response = null;
        try {
            String result = WechatHttpUtils.post(PayUrlEnum.DOMAIN.getUrl() + PayUrlEnum.UNIFIED_ORDER.getUrl(), unifiedOrderRequest);
            response = XmlUtils.toBean(result, UnifiedOrderResponse.class);
        } catch (Exception e) {
            logger.error("统一下单接口异常.", e);
            throw e;
        }
        return response;
    }

    /**
     * 查询订单 - 不需要证书
     *
     * @param orderQueryRequest
     * @return
     */
    public static OrderQueryResponse orderQuery(OrderQueryRequest orderQueryRequest) {
        OrderQueryResponse response = null;
        try {
            String result = WechatHttpUtils.post(PayUrlEnum.DOMAIN.getUrl() + PayUrlEnum.ORDER_QUERY.getUrl(), orderQueryRequest);
            response = XmlUtils.toBean(result, OrderQueryResponse.class);
        } catch (Exception e) {
            logger.error("查询订单接口异常.", e);
            throw e;
        }
        return response;
    }

    /**
     * 关闭订单 - 不需要证书
     */
    public static CloseOrderResponse closeOrder(CloseOrderRequest closeOrderRequest) {
        CloseOrderResponse response;
        try {
            String result = WechatHttpUtils.post(PayUrlEnum.DOMAIN.getUrl() + PayUrlEnum.CLOSE_ORDER.getUrl(), closeOrderRequest);
            response = XmlUtils.toBean(result, CloseOrderResponse.class);
        } catch (Exception e) {
            logger.error("关闭订单接口异常.", e);
            throw e;
        }
        return response;
    }

    /**
     * 申请退款 - 需要证书
     */
    public static RefundResponse refund(RefundRequest refundRequest) {
        RefundResponse response;
        try {
            String result = WechatHttpUtils.useCertPost(PayUrlEnum.DOMAIN.getUrl() + PayUrlEnum.REFUND.getUrl(), refundRequest);
            response = XmlUtils.toBean(result, RefundResponse.class);
        } catch (Exception e) {
            logger.error("申请退款接口异常.", e);
            throw e;
        }
        return response;
    }

    /**
     * 查询退款 - 不需要证书
     */
    public static RefundQueryResponse refundQuery(RefundQueryRequest refundQueryRequest) {
        RefundQueryResponse response;
        try {
            String result = WechatHttpUtils.post(PayUrlEnum.DOMAIN.getUrl() + PayUrlEnum.REFUND_QUERY.getUrl(), refundQueryRequest);
            response = XmlUtils.toBean(result, RefundQueryResponse.class);
        } catch (Exception e) {
            logger.error("查询退款接口异常.", e);
            throw e;
        }
        return response;
    }

    /**
     * 下载交易账单
     */
    public static void downloadBill(DownloadBillRequest downloadBillRequest) {
        RefundQueryResponse response;
        try {
            String result = WechatHttpUtils.post(PayUrlEnum.DOMAIN.getUrl() + PayUrlEnum.DOWNLOAD_BILL.getUrl(), downloadBillRequest);
            System.out.println(result);
        } catch (Exception e) {
            logger.error("申请退款接口异常.", e);
            throw e;
        }
    }

}
