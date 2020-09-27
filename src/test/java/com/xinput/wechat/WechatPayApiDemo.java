package com.xinput.wechat;

import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.ObjectId;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.request.CloseOrderRequest;
import com.xinput.wechat.request.DownloadBillRequest;
import com.xinput.wechat.request.OrderQueryRequest;
import com.xinput.wechat.request.RefundQueryRequest;
import com.xinput.wechat.request.RefundRequest;
import com.xinput.wechat.request.UnifiedOrderRequest;
import com.xinput.wechat.response.CloseOrderResponse;
import com.xinput.wechat.response.OrderQueryResponse;
import com.xinput.wechat.response.RefundQueryResponse;
import com.xinput.wechat.response.RefundResponse;
import com.xinput.wechat.response.UnifiedOrderResponse;
import com.xinput.wechat.result.OrderSignature;
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
                .with(UnifiedOrderRequest::setNotify_url, WechatConfig.getWechatNotifyRefundUrl())
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
        String transaction_id = "4634523320920200923160408583947";
        String outTradeNo = "5f6996766b5adfaca6b0e21b";
        try {
            OrderQueryResponse response = WechatPayApi.orderQueryByTransaction(transaction_id);
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
//        outTradeNo = "5f6b049c6b5adf3de4824edc"; // 用例3【公众-异常】订单金额1.30元，用户支付成功，商户未收到微信支付结果通知
//        outTradeNo = "5f6b10ab6b5adf43576f0ce9"; // 用例4【公众-异常】订单金额1.31元，用户支付失败，商户未收到微信支付结果通知
//        outTradeNo = "5f6b12c46b5adf4468b1dc1b"; // 用例5【公众-异常】订单金额1.32元，用户支付成功，微信支付重复通知商户
//        outTradeNo = "5f6b13216b5adf449cceb8d0"; // 用例6【公众-异常】订单金额1.33元，用户支付成功，微信支付通知签名非法
//        outTradeNo = "5f6b1e8e6b5adf4fc2ca1b0f"; // 用例7【公众-异常】订单金额1.34元，用户支付成功，微信支付通知关键信息不一致

        try {
            OrderQueryResponse response = WechatPayApi.orderQueryByOutTradeNo(outTradeNo);
            System.out.println(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询订单
     */
    @Test
    public void orderQuery() {
        String outTradeNo = "5f6b35706b5adf5e85d457de";
        try {
            OrderQueryRequest orderQueryRequest = BuilderUtils.of(OrderQueryRequest::new)
                    .with(OrderQueryRequest::setOut_trade_no, outTradeNo)
                    .build();
            OrderQueryResponse response = WechatPayApi.orderQuery(orderQueryRequest);
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
                .with(CloseOrderRequest::setOut_trade_no, "5f6991d36b5adfaa6b9783e6")
                .build();
        try {
            CloseOrderResponse response = WechatPayApi.closeOrder(closeOrderRequest);
            System.out.println(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭订单
     */
    @Test
    public void closeOrder2() {
        String outTradeNo = "5f6991d36b5adfaa6b9783e6"; // 101
        try {
            CloseOrderResponse response = WechatPayApi.closeOrder(outTradeNo);
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

        String outTradeNo = "";
        outTradeNo = "5f6991d36b5adfaa6b9783e6"; // 101
        outTradeNo = "5f6996766b5adfaca6b0e21b"; // 102
        outTradeNo = "5f6b35706b5adf5e85d457de";

        RefundRequest request = BuilderUtils.of(RefundRequest::new)
                .with(RefundRequest::setOut_trade_no, outTradeNo)
                .with(RefundRequest::setOut_refund_no, out_refund_no)
                .with(RefundRequest::setTotal_fee, 502)
                .with(RefundRequest::setRefund_fee, 502)
                .with(RefundRequest::setNotify_url, WechatConfig.getWechatNotifyRefundUrl())
                .build();
        logger.info("申请退款数据 : {}", JsonUtils.toJsonString(request, true));
        try {
            RefundResponse response = WechatPayApi.refund(request);
            logger.info(JsonUtils.toJsonString(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 申请退款
     */
    @Test
    public void refund2() {
        String outTradeNo = "5f6b35706b5adf5e85d457de";
        try {
            RefundResponse response = WechatPayApi.refund(outTradeNo, 502, 502);
            logger.info(JsonUtils.toJsonString(response, true));
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
//                .with(RefundQueryRequest::setTransaction_id, "4274180775120200924120824396328")
                .with(RefundQueryRequest::setOut_trade_no, "5f6b35706b5adf5e85d457de")
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
