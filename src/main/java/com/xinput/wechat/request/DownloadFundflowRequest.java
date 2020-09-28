package com.xinput.wechat.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 下载交易账单 - 需要证书
 * 注意：签名类型，目前仅支持HMAC-SHA256
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-17 23:37
 */
@XStreamAlias("xml")
public class DownloadFundflowRequest extends BaseWeChatPayRequest {

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
     * 资金账户类型
     * 必填: 是
     * 类型: String(8)
     * 示例值: Basic
     * 描述: 账单的资金来源账户 {@link com.xinput.wechat.enums.AccountTypeEnum}
     */
    @XStreamAlias("account_type")
    private String account_type;

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

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getTar_type() {
        return tar_type;
    }

    public void setTar_type(String tar_type) {
        this.tar_type = tar_type;
    }

}
