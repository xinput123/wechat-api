package com.xinput.wechat.result;

import com.univocity.parsers.annotations.Parsed;

/**
 * 此类仅仅是为了用于获取交易单数
 */
public class BillCount {

    @Parsed(index = 0)
    private String totalRecord;

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }
}
