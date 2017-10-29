package com.wg.test.shoppingcart.Entity;

/**
 * Created by 赵涛 on 2017/10/29.
 * 折扣的属性（起止日期，产品类型，折扣率）
 */

public class DiscountEntity {
    private String start_date;
    private String end_date;
    private String type;
    private int rate;

    public DiscountEntity(String type, int rate, String start_date, String end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.type = type;
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
