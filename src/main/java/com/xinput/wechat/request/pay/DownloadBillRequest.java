package com.xinput.wechat.request.pay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 下载交易账单 - 不需要证书
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 23:37
 */
@XStreamAlias("xml")
public class DownloadBillRequest extends BaseWeChatPayReq {

    /**
     * 对账单日期
     * 必填: 是
     * 类型: String(8)
     * 示例值: 20140603
     * 描述: 下载对账单的日期，格式：20140603
     */
    @XStreamAlias("bill_date")
    private String bill_date;

    /**
     * 账单类型
     * 必填: 否
     * 类型: String(8)
     * 示例值: ALL
     * 描述: {@link com.xinput.wechatpay.consts.BillTypeEnum}
     */
    @XStreamAlias("bill_type")
    private String bill_type;

    /**
     * 压缩账单
     * 必填: 否
     * 类型: String
     * 示例值: GZIP
     * 描述: 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式
     */
    @XStreamAlias("tar_type")
    private String tar_type;

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getTar_type() {
        return tar_type;
    }

    public void setTar_type(String tar_type) {
        this.tar_type = tar_type;
    }

    @Override
    public String toString() {
        return "DownloadBillRequest{" +
                super.toString() + '\'' +
                ", bill_date='" + bill_date + '\'' +
                ", bill_type='" + bill_type + '\'' +
                ", tar_type='" + tar_type + '\'' +
                '}';
    }
}
