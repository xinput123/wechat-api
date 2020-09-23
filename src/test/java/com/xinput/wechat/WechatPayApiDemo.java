package com.xinput.wechat;

import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.ObjectId;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.model.OrderSignature;
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
import org.junit.Test;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-18 06:41
 */
public class WechatPayApiDemo {

    private static final Logger logger = Logs.get();

    private static final String openId = "o21I_5QRM2KroSRO_uvjR4X-3BHg";

    private static final String liJunOpenId = "o21I_5SGVI6QmukwPLVttYctCFEU";

    /**
     * 统一下单
     */
    @Test
    public void unifiedOrder() {
        UnifiedOrderRequest request = BuilderUtils.of(UnifiedOrderRequest::new)
                // 商品名称
                .with(UnifiedOrderRequest::setBody, "嘉云升-阿米洛机械键盘")
                // 获取客户端的ip地址
                .with(UnifiedOrderRequest::setSpbill_create_ip, "192.168.10.13")
                .with(UnifiedOrderRequest::setSign_type, "MD5")
                // 商户订单号,自己的订单ID
                .with(UnifiedOrderRequest::setOut_trade_no, ObjectId.stringId())
                // 支付金额，这边需要转成字符串类型，否则后面的签名会失败
                .with(UnifiedOrderRequest::setTotal_fee, 102)
                // 支付成功后的回调地址
                .with(UnifiedOrderRequest::setNotify_url, WechatConfig.getWechatNotifyUrl())
                // 支付方式
                .with(UnifiedOrderRequest::setTrade_type, "JSAPI")
                // 用户的openID，自己获取
                .with(UnifiedOrderRequest::setOpenid, openId)
                .with(UnifiedOrderRequest::setDevice_info, "sandbox")
                .build();
        try {
            UnifiedOrderResponse response = WechatPayApi.unifiedOrder(request);
            logger.info("UnifiedOrderResponse : {}", JsonUtils.toJsonString(response, true));

            // 再次签名
            OrderSignature orderSignature = new OrderSignature(response);
            logger.info("orderSignature : " + JsonUtils.toJsonString(orderSignature, true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询订单
     */
    @Test
    public void orderQuery1() {
        String outTradeNo = "5f697af86b5adf9fdcdf60a5"; // 101
        outTradeNo = "5f6991d36b5adfaa6b9783e6"; // 101
        outTradeNo = "5f6996766b5adfaca6b0e21b"; // 102
        OrderQueryRequest createOrderQueryRequest = BuilderUtils.of(OrderQueryRequest::new)
                .with(OrderQueryRequest::setOut_trade_no, outTradeNo)
                .build();
        try {
            OrderQueryResponse response = WechatPayApi.orderQuery(createOrderQueryRequest);
            System.out.println(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询订单
     */
    @Test
    public void orderQuery2() {
        String outTradeNo = "5f697af86b5adf9fdcdf60a5"; // 101
        outTradeNo = "5f6991d36b5adfaa6b9783e6"; // 101
        outTradeNo = "5f6996766b5adfaca6b0e21b"; // 102

        try {
            OrderQueryResponse response = WechatPayApi.orderQueryByOutTradeNo(outTradeNo);
            System.out.println(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭订单
     */
    @Test
    public void closeOrder() {
        CloseOrderRequest closeOrderRequest = BuilderUtils.of(CloseOrderRequest::new)
                .with(CloseOrderRequest::setOut_trade_no, "5f641e562803199a3ee5281a")
                .build();
        try {
            CloseOrderResponse response = WechatPayApi.closeOrder(closeOrderRequest);
            System.out.println(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 申请退款
     */
    @Test
    public void refund() {
        String out_refund_no = ObjectId.stringId();
        System.out.println("out_refund_no = " + out_refund_no);
        logger.info("out_refund_no = {}", out_refund_no);

        RefundRequest request = BuilderUtils.of(RefundRequest::new)
                // 通过统一下单接口返回的 prepayId 的值
                .with(RefundRequest::setTransaction_id, "4200000764202009188340047847")
                .with(RefundRequest::setOut_trade_no, "5f646c8d280319c40fde6bf9")
                .with(RefundRequest::setOut_refund_no, out_refund_no)
                .with(RefundRequest::setTotal_fee, 100)
                .with(RefundRequest::setRefund_fee, 10)
                .with(RefundRequest::setNotify_url, WechatConfig.getWechatNotifyUrl())
                .build();
        logger.info("申请退款数据 : {}", JsonUtils.toJsonString(request, true));
        try {
            RefundResponse response = WechatPayApi.refund(request);
            System.out.println(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询退款
     */
    @Test
    public void refundquery() {
        RefundQueryRequest request = BuilderUtils.of(RefundQueryRequest::new)
                .with(RefundQueryRequest::setTransaction_id, "4200000764202009188340047847")
                .with(RefundQueryRequest::setOut_trade_no, "5f646c8d280319c40fde6bf9")
                .build();
        logger.info("查询退款:{}.", JsonUtils.toJsonString(request));

        try {
            RefundQueryResponse response = WechatPayApi.refundQuery(request);
            logger.info(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadBill() throws Exception {
        DownloadBillRequest downloadBillRequest = BuilderUtils.of(DownloadBillRequest::new)
                .with(DownloadBillRequest::setBill_date, "20200919")
                .with(DownloadBillRequest::setBill_type, "ALL")
                .build();
        WechatPayApi.downloadBill(downloadBillRequest);
    }

}
