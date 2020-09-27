package com.xinput.wechat.testcase;

import com.xinput.bleach.util.BuilderUtils;
import com.xinput.bleach.util.FileHelper;
import com.xinput.bleach.util.JsonUtils;
import com.xinput.wechat.WechatPayApi;
import com.xinput.wechat.enums.BillTypeEnum;
import com.xinput.wechat.request.pay.DownloadBillRequest;
import com.xinput.wechat.result.WechatBillInfo;
import com.xinput.wechat.result.WechatPayBillResult;
import org.junit.Test;

import java.util.List;

/**
 * 1005-必选用例-交易对账单下载
 * <p>
 * 用例简述
 * 使用了免充值券的订单，免充值券部分的金额不计入结算金额。
 * 验证商户对账能正确理解到这一点，对账无误。
 */
public class Case3_5 {

    @Test
    public void download() throws Exception {
        DownloadBillRequest downloadBillRequest = BuilderUtils.of(DownloadBillRequest::new)
                .with(DownloadBillRequest::setBill_type, "ALL")
                .build();
        WechatPayBillResult result = WechatPayApi.downloadBill(downloadBillRequest);

        System.out.println(JsonUtils.toJsonString(result));
    }

    @Test
    public void downloadAll() throws Exception {
        DownloadBillRequest downloadBillRequest = BuilderUtils.of(DownloadBillRequest::new)
                .with(DownloadBillRequest::setBill_type, "ALL")
                .build();
        String result = WechatPayApi.downloadBillContent(downloadBillRequest);
        System.out.println(result);
    }

    @Test
    public void downloadSuccess() throws Exception {
        DownloadBillRequest downloadBillRequest = BuilderUtils.of(DownloadBillRequest::new)
                .with(DownloadBillRequest::setBill_type, BillTypeEnum.SUCCESS.getBillType())
                .build();
        String result = WechatPayApi.downloadBillContent(downloadBillRequest);
        System.out.println(result);
    }

    @Test
    public void downloadRefund() throws Exception {
        DownloadBillRequest downloadBillRequest = BuilderUtils.of(DownloadBillRequest::new)
                .with(DownloadBillRequest::setBill_type, BillTypeEnum.REFUND.getBillType())
                .build();
        String result = WechatPayApi.downloadBillContent(downloadBillRequest);
        System.out.println(result);
    }

    @Test
    public void downloadRechargeRefund() throws Exception {
        DownloadBillRequest downloadBillRequest = BuilderUtils.of(DownloadBillRequest::new)
                .with(DownloadBillRequest::setBill_type, BillTypeEnum.RECHARGE_REFUND.getBillType())
                .build();
        String result = WechatPayApi.downloadBillContent(downloadBillRequest);
        System.out.println(result);
    }

    @Test
    public void crate() {
        StringBuilder sb = new StringBuilder(aa());
        List<WechatBillInfo> wechatBillInfos = FileHelper.readCsv(sb.toString(), WechatBillInfo.class);
        System.out.println(wechatBillInfos.size());

        System.out.println(JsonUtils.toJsonString(wechatBillInfos));
    }

    private String aa() {
        return "交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,应结订单金额,代金券金额,微信退款单号,商户退款单号,退款金额,充值券退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率,订单金额,申请退款金额,费率备注\n" +
                "`2016-05-04  02:18:18,`wxf7c30a8258df4208,`10014843,`0,`harryma007,`4.00123E+27,`autotest_20160501030456_45023,`oT2kauIMXH398DZBeJ4m22CuSDQ0,`NATIVE,`REFUND,`PAB_DEBIT,`CNY,`0,`0,`2.00123E+27,`REF4001232001201605015390231647,`0.01,`0,`ORIGINAL,`PROCESSING,`body中文测试,`attach中文测试,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  21:00:00,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504210043_86327,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504210043_98253,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  21:00:00,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504210051_67591,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  21:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504210051_67591,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504210051_18702,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  21:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504210136_85029,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  21:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504210136_85029,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504210136_58755,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  21:04:04,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504210418_19938,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0.01,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.02,`0,`\n" +
                "`2016-05-04  21:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504215916_81523,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  21:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504215935_87181,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  21:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504215945_80754,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  21:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504215926_39934,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  21:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504215926_39934,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`1.00148E+35,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  22:04:04,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220413_77215,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0.01,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.02,`0,`\n" +
                "`2016-05-04  22:04:04,`wxf7c30a8258df4208,`10014843,`0,`harryma007,`4.00934E+27,`autotest_20160504220434_46712,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`PAB_DEBIT,`CNY,`0.01,`0,`0,`0,`0,`0,`body中文测试,`attach中文测试,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  22:00:00,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220045_73975,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  22:00:00,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220045_73975,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504220045_69599,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  22:00:00,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220054_59830,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  22:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220054_59830,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504220054_70622,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  22:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220139_96403,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  22:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220139_96403,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504220139_15782,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  22:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220147_43192,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.02,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.02,`0,`\n" +
                "`2016-05-04  22:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220147_43192,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504220147_52444,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  22:02:02,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220211_39060,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  22:02:02,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504220211_39060,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504220210_33550,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  22:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504225936_68548,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  22:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504225945_60328,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:00:00,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230056_88794,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230056_88794,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504230056_80444,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  23:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230156_76791,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.02,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.02,`0,`\n" +
                "`2016-05-04  23:02:02,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230156_76791,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504230156_77168,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  23:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230143_70777,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:01:01,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230143_70777,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504230143_85961,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  23:02:02,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230224_35671,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:02:02,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230224_35671,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504230224_21804,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  23:05:05,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230500_51077,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0.01,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.02,`0,`\n" +
                "`2016-05-04  22:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504225916_73727,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  22:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504225926_71728,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  22:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504225926_71728,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`1.00148E+35,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  23:00:00,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230048_19813,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:00:00,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504230048_19813,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`autotest_refund_20160504230048_51316,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  23:05:05,`wxf7c30a8258df4208,`10014843,`0,`harryma007,`4.00934E+27,`autotest_20160504230519_46991,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`PAB_DEBIT,`CNY,`0.01,`0,`0,`0,`0,`0,`body中文测试,`attach中文测试,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504235944_54173,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504235915_73349,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504235935_74589,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504235926_40200,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`SUCCESS,`CFT,`CNY,`0.01,`0,`0,`0,`0,`0,`body,`autotestpow,`0,`0.60%,`0.01,`0,`\n" +
                "`2016-05-04  23:59:59,`wxf7c30a8258df4208,`10014843,`0,`powautotest001,`4.00934E+27,`autotest_20160504235926_40200,`oT2kauKVSSo8ppmc5k8CW8U-oqr8,`NATIVE,`REFUND,`CFT,`CNY,`0,`0,`2.00934E+27,`1.00148E+35,`0.01,`0,`ORIGINAL,`SUCCESS,`body,`autotestpow,`0,`0.60%,`0,`0.01,`\n" +
                "`2016-05-04  14:51:51,`wxf7c30a8258df4208,`10014843,`0,`harryma8888,`4.00968E+27,`wxautotest1462344441,`oT2kauGtJag902bjdvevrJbpGuxo,`NATIVE,`SUCCESS,`CMBC_CREDIT,`CNY,`0.05,`0.01,`0,`0,`0,`0,`中文[body],`测试中文[attach],`0,`0.60%,`0.05,`0`";
    }
}
