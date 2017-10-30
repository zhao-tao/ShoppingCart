package com.wg.test.shoppingcart.Entity;

import com.wg.test.shoppingcart.Tools;

/**
 * Created by 赵涛 on 2017/10/29.
 * 优惠券（起止日期，最低消费金额，优惠金额）
 */

public class CouponEntity {
	private String start_date;
	private String end_date;
	private int min_price;
	private int minus;

	public CouponEntity(int min_price, int minus, String start_date, String end_date) {
		this.start_date = start_date;
		this.end_date = end_date;
		this.min_price = min_price;
		this.minus = minus;
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

	public int getMin_price() {
		return min_price;
	}

	public void setMin_price(int min_price) {
		this.min_price = min_price;
	}

	public int getMinus() {
		return minus;
	}

	public void setMinus(int minus) {
		this.minus = minus;
	}


	/**
	 * 获取优惠券标题
	 *
	 * @return
	 */
	public String getTitle() {
		return "满" + Tools.intToString(min_price) + "减" + Tools.intToString(minus) + "优惠券";
	}

	/**
	 * 获取有效期时间
	 *
	 * @return
	 */
	public String getInDate() {
		String text = "";
		if (null == start_date || start_date.trim().length() == 0) {
			text = "有效期至" + end_date;
		} else {
			text = "有效期:" + start_date + "至" + end_date;
		}
		return text;
	}
}
