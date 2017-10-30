package com.wg.test.shoppingcart.Entity;

import static com.wg.test.shoppingcart.Consts.TYPE_DAILY;
import static com.wg.test.shoppingcart.Consts.TYPE_DIGIT;
import static com.wg.test.shoppingcart.Consts.TYPE_FOOD;
import static com.wg.test.shoppingcart.Consts.TYPE_LIQUOR;

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


	public String getTitle() {
		String s = "";
		switch (type) {
			case TYPE_DIGIT:
				s = "电子";
				break;
			case TYPE_FOOD:
				s = "食品";
				break;
			case TYPE_DAILY:
				s = "日用品";
				break;
			case TYPE_LIQUOR:
				s = "酒";
				break;
		}

		return s + "类折扣";
	}

	public String getInDate() {
		String text = "";
		if (null == start_date || start_date.trim().length() == 0) {
			text = "截至日期:" + end_date;
		} else {
			text = "有效期:" + start_date + "至" + end_date;
		}
		return text;
	}
}
