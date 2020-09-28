package com.xinput.wechat;

import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.ObjectId;
import com.xinput.bleach.util.StringUtils;
import com.xinput.bleach.util.XmlUtils;
import com.xinput.bleach.util.bean.BeanMapUtils;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.consts.WechatConsts;
import com.xinput.wechat.enums.PayUrlEnum;
import com.xinput.wechat.enums.SignTypeEnum;
import com.xinput.wechat.enums.TradeTypeEnum;
import com.xinput.wechat.exception.WechatException;
import com.xinput.wechat.request.CloseOrderRequest;
import com.xinput.wechat.request.DownloadBillRequest;
import com.xinput.wechat.request.DownloadFundflowRequest;
import com.xinput.wechat.request.MicroPayRequest;
import com.xinput.wechat.request.OrderQueryRequest;
import com.xinput.wechat.request.QueryCommentRequest;
import com.xinput.wechat.request.RefundQueryRequest;
import com.xinput.wechat.request.RefundRequest;
import com.xinput.wechat.request.SandboxSignKeyRequest;
import com.xinput.wechat.request.UnifiedOrderRequest;
import com.xinput.wechat.response.CloseOrderResponse;
import com.xinput.wechat.response.MicroPayResponse;
import com.xinput.wechat.response.OrderQueryResponse;
import com.xinput.wechat.response.QueryCommentResponse;
import com.xinput.wechat.response.RefundQueryResponse;
import com.xinput.wechat.response.RefundResponse;
import com.xinput.wechat.response.SandboxSignKeyResponse;
import com.xinput.wechat.response.UnifiedOrderResponse;
import com.xinput.wechat.result.BillCount;
import com.xinput.wechat.result.WechatBillInfo;
import com.xinput.wechat.result.WechatFundFlowBaseResult;
import com.xinput.wechat.result.WechatFundFlowResult;
import com.xinput.wechat.result.WechatPayBillResult;
import com.xinput.wechat.util.CsvUtils;
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
     * 统一下单
     *
     * @param tradeTypeEnum 支付方式
     * @param openId        用户openID
     * @param deviceInfo    设备名称
     * @param outTradeNo    商户订单号
     * @param body          商品名称
     * @param ip            获取客户端的ip地址
     * @param totalFee      支付金额
     * @param notifyUrl     回调地址
     */
    public static UnifiedOrderResponse unifiedOrder(TradeTypeEnum tradeTypeEnum, String openId, String deviceInfo,
                                                    String outTradeNo, String body, String ip, Integer totalFee,
                                                    String notifyUrl) throws Exception {
        UnifiedOrderRequest request = BuilderUtils.of(UnifiedOrderRequest::new)
                .with(UnifiedOrderRequest::setBody, body)
                .with(UnifiedOrderRequest::setSpbill_create_ip, ip)
                .with(UnifiedOrderRequest::setOut_trade_no, outTradeNo)
                .with(UnifiedOrderRequest::setTotal_fee, totalFee)
                .with(UnifiedOrderRequest::setNotify_url, notifyUrl)
                .with(UnifiedOrderRequest::setTrade_type, tradeTypeEnum.getTradeType())
                .with(UnifiedOrderRequest::setOpenid, openId)
                .with(UnifiedOrderRequest::setDevice_info, deviceInfo)
                .build();

        return unifiedOrder(request);
    }

    /**
     * 统一下单
     *
     * @param unifiedOrderRequest
     * @return
     * @throws Exception
     */
    public static UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest unifiedOrderRequest) throws Exception {
        if (StringUtils.isNullOrEmpty(unifiedOrderRequest.getNotify_url())) {
            unifiedOrderRequest.setNotify_url(WechatConfig.getWechatNotifyUnifiedOrderUrl());
        }

        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.UNIFIED_ORDER.getUrl(), unifiedOrderRequest);
        Map<String, Object> params = WechatXmlUtils.toMap(result);
        UnifiedOrderResponse response = BeanMapUtils.toBean(params, UnifiedOrderResponse.class);

        // 验证签名是否合法
        if (params.containsKey("sign")
                && !WechatPayUtils.isSignatureValid(params, SignTypeEnum.getSignType(unifiedOrderRequest.getSign_type()))) {
            throw new WechatException(String.format("Invalid sign value in unified order response : [%s]", JsonUtils.toJsonString(response, true)));
        }

        return response;
    }

    /**
     * 查询订单 - 根据微信订单号
     *
     * @param transactionId 微信订单号
     * @return
     */
    public static OrderQueryResponse orderQueryByTransaction(String transactionId) throws Exception {
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
        return OrderQueryResponse.createOrderQueryResponse(params, SignTypeEnum.getSignType(orderQueryRequest.getSign_type()));
    }

    /**
     * 关闭订单
     *
     * @param outTradeNo 商户订单号
     */
    public static CloseOrderResponse closeOrder(String outTradeNo) throws Exception {
        CloseOrderRequest closeOrderRequest = BuilderUtils.of(CloseOrderRequest::new)
                .with(CloseOrderRequest::setOut_trade_no, outTradeNo)
                .build();

        return closeOrder(closeOrderRequest);
    }

    /**
     * 关闭订单
     */
    public static CloseOrderResponse closeOrder(CloseOrderRequest closeOrderRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.CLOSE_ORDER.getUrl(), closeOrderRequest);
        Map<String, Object> params = WechatXmlUtils.toMap(result);
        CloseOrderResponse closeOrderResponse = BeanMapUtils.toBean(params, CloseOrderResponse.class);
        // 验证签名是否合法
        if (params.containsKey("sign")
                && !WechatPayUtils.isSignatureValid(params, SignTypeEnum.getSignType(closeOrderRequest.getSign_type()))) {
            throw new WechatException(String.format("Invalid sign value in close order response : [%s]", JsonUtils.toJsonString(closeOrderResponse, true)));
        }

        return closeOrderResponse;
    }

    /**
     * 申请退款
     *
     * @param outTradeNo 商户订单号
     * @param totalFee   订单金额
     * @param refundFee  退款金额
     */
    public static RefundResponse refund(String outTradeNo, Integer totalFee, Integer refundFee) throws Exception {
        RefundRequest request = BuilderUtils.of(RefundRequest::new)
                .with(RefundRequest::setOut_trade_no, outTradeNo)
                .with(RefundRequest::setTotal_fee, totalFee)
                .with(RefundRequest::setRefund_fee, refundFee)
                .build();

        return refund(request);
    }

    /**
     * 申请退款
     *
     * @param refundRequest 申请退款请求
     */
    public static RefundResponse refund(RefundRequest refundRequest) throws Exception {
        Integer totalFee = refundRequest.getTotal_fee();
        if (totalFee == null || totalFee <= 0) {
            throw new WechatException(String.format("账单总金额错误,请输入正确的账单总金额. total_fee:[%s]", totalFee));
        }

        Integer refundFee = refundRequest.getRefund_fee();
        if (refundFee == null || refundFee <= 0) {
            throw new WechatException(String.format("退款金额错误,请输入正确的退款金额. refund_fee:[%s]", refundFee));
        }

        if (totalFee < refundFee) {
            throw new WechatException(String.format("退款金额不能大于账单总金额,请检查. total_fee:[%s], refund_fee:[%s]", totalFee, refundFee));
        }

        if (StringUtils.isNullOrEmpty(refundRequest.getOut_refund_no())) {
            refundRequest.setOut_refund_no(ObjectId.stringId());
        }

        if (StringUtils.isNullOrEmpty(refundRequest.getNotify_url())) {
            refundRequest.setNotify_url(WechatConfig.getWechatNotifyRefundUrl());
        }

        String result = WechatHttpUtils.withCertPost(getDomain() + PayUrlEnum.REFUND.getUrl(), refundRequest);
        Map<String, Object> params = WechatXmlUtils.toMap(result);

        return RefundResponse.createRefundResponse(params, SignTypeEnum.getSignType(refundRequest.getSign_type()));
    }

    /**
     * 查询退款
     *
     * @param transactionId 微信订单号
     */
    public static RefundQueryResponse refundQueryByTransaction(String transactionId) throws Exception {
        RefundQueryRequest request = BuilderUtils.of(RefundQueryRequest::new)
                .with(RefundQueryRequest::setTransaction_id, transactionId)
                .build();

        return refundQuery(request);
    }

    /**
     * 查询退款
     *
     * @param outTradeNo 商户订单号
     */
    public static RefundQueryResponse refundQueryByOutTradeNo(String outTradeNo) throws Exception {
        RefundQueryRequest request = BuilderUtils.of(RefundQueryRequest::new)
                .with(RefundQueryRequest::setOut_trade_no, outTradeNo)
                .build();

        return refundQuery(request);
    }


    /**
     * 查询退款
     *
     * @param outRefundNo 商户退款单号
     */
    public static RefundQueryResponse refundQueryByOutRefundNo(String outRefundNo) throws Exception {
        RefundQueryRequest request = BuilderUtils.of(RefundQueryRequest::new)
                .with(RefundQueryRequest::setOut_refund_no, outRefundNo)
                .build();

        return refundQuery(request);
    }

    /**
     * 查询退款
     *
     * @param refundId 微信退款单号
     */
    public static RefundQueryResponse refundQueryByRefund(String refundId) throws Exception {
        RefundQueryRequest request = BuilderUtils.of(RefundQueryRequest::new)
                .with(RefundQueryRequest::setRefund_id, refundId)
                .build();

        return refundQuery(request);
    }

    /**
     * 查询退款
     */
    public static RefundQueryResponse refundQuery(RefundQueryRequest refundQueryRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.REFUND_QUERY.getUrl(), refundQueryRequest);
        Map<String, Object> params = WechatXmlUtils.toMap(result);
        return RefundQueryResponse.createRefundQueryResponse(params, SignTypeEnum.getSignType(refundQueryRequest.getSign_type()));
    }

    /**
     * 下载交易账单
     */
    public static String downloadBillContent(DownloadBillRequest downloadBillRequest) throws Exception {
        return WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.DOWNLOAD_BILL.getUrl(), downloadBillRequest).replaceAll("`", "");
    }

    /**
     * 下载交易账单
     */
    public static WechatPayBillResult downloadBill(DownloadBillRequest downloadBillRequest) throws Exception {
        String result = WechatHttpUtils.withoutCertQequest(getDomain() + PayUrlEnum.DOWNLOAD_BILL.getUrl(), downloadBillRequest).replaceAll("`", "");

        // 微信总共返回的数据条数，包括表头
        List<BillCount> billCounts = CsvUtils.readCsv(result, BillCount.class);
        int billCount = Integer.parseInt(billCounts.get(billCounts.size() - 1).getTotalRecord());
        List<WechatPayBillResult> wechatPayBillResults = CsvUtils.readCsv(result, billCount + 2, WechatPayBillResult.class);
        WechatPayBillResult wechatPayBillResult = wechatPayBillResults.get(0);

        List<WechatBillInfo> wechatBillInfos = CsvUtils.readCsv(result, 1, billCount - 3, WechatBillInfo.class);
        wechatPayBillResult.setWechatBillInfos(wechatBillInfos);
        return wechatPayBillResult;
    }

    /**
     * 下载资金账单
     */
    public static WechatFundFlowResult downloadFundflow(DownloadFundflowRequest request) throws Exception {
        String result = WechatHttpUtils.withCertPost(
                getDomain() + PayUrlEnum.DOWNLOAD_FUND_FLOW.getUrl(), request)
                .replaceAll("`", StringUtils.EMPTY);
        logger.info(result);
        if (result.startsWith(WechatConsts.XML)) {
            return XmlUtils.toBean(result, WechatFundFlowResult.class);
        }

        // 查看返回了多少条数据. 其中第一行为表头，倒数第二行为资金账单统计标题
        String[] temp = result.split("\n");
        int resultSize = temp.length;
        temp = null; // only gc

        if (resultSize < 4) {
            return null;
        }

        List<WechatFundFlowResult> wechatFundFlowResults
                = CsvUtils.readCsv(result, resultSize - 1, WechatFundFlowResult.class);
        List<WechatFundFlowBaseResult> wechatFundFlowBaseResults
                = CsvUtils.readCsv(result, 1, resultSize - 2, WechatFundFlowBaseResult.class);

        WechatFundFlowResult fundFlowResult = wechatFundFlowResults.get(0);
        fundFlowResult.setBaseResults(wechatFundFlowBaseResults);

        return fundFlowResult;
    }

    /**
     * 拉取订单评价数据
     *
     * @param beginTime 开始时间
     * @param endTime   结束是爱你
     */
    public static QueryCommentResponse queryComment(String beginTime, String endTime) throws Exception {
        return queryComment(beginTime, endTime, 0);
    }

    /**
     * 拉取订单评价数据
     *
     * @param beginTime 开始时间
     * @param endTime   结束是爱你
     * @param offset    偏移量
     */
    public static QueryCommentResponse queryComment(String beginTime, String endTime, int offset) throws Exception {
        return queryComment(beginTime, endTime, offset, 200);
    }

    /**
     * 拉取订单评价数据
     *
     * @param beginTime 开始时间
     * @param endTime   结束是爱你
     * @param offset    偏移量
     * @param limit     条数
     */
    public static QueryCommentResponse queryComment(String beginTime, String endTime, int offset, int limit) throws Exception {
        QueryCommentRequest request = BuilderUtils.of(QueryCommentRequest::new)
                .with(QueryCommentRequest::setBegin_time, beginTime)
                .with(QueryCommentRequest::setEnd_time, endTime)
                .with(QueryCommentRequest::setOffset, offset)
                .with(QueryCommentRequest::setLimit, limit)
                .build();

        return queryComment(request);
    }

    /**
     * 拉取订单评价数据
     *
     * @param request 订单评价数据请求
     */
    public static QueryCommentResponse queryComment(QueryCommentRequest request) throws Exception {
        if (request.getOffset() < 0) {
            request.setOffset(0);
        }
        if (request.getLimit() < 1 || request.getLimit() > 200) {
            request.setLimit(200);
        }
        String result = WechatHttpUtils.withCertPost(getDomain() + PayUrlEnum.QUERY_COMMENT.getUrl(), request);
        return QueryCommentResponse.fromXml(result);
    }

}
