package com.wg.test.shoppingcart;

import android.content.Context;
import android.net.ParseException;
import android.widget.Toast;

import com.wg.test.shoppingcart.Entity.DiscountEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 赵涛 on 2017/10/29.
 */

public class Tools {
	/**
	 * 获取当前时间
	 *
	 * @return
	 */

	private static Toast toast = null;

	public static String getSystemDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(Calendar.getInstance().getTime());
	}

	public static Date StringToDate(String str) throws Exception {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = format1.parse(str);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 当前题目中只判断截至有效期
	 *
	 * @param discount
	 * @return
	 */
	public static boolean canUseDate(String endDate) {
		try {
			Date date = StringToDate(getSystemDate());
			return 1 != date.compareTo(StringToDate(endDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void showToast(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.show();
	}

	/**
	 * 下单使用BigDecimal转换
	 *
	 * @param price
	 * @return
	 */
	public static BigDecimal intToBigDecimal(int price) {
		BigDecimal bigDecimal = new BigDecimal(price);
		return bigDecimal.divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY);
	}

	public static String intToString(int price) {
		BigDecimal number = intToBigDecimal(price);
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		return decimalFormat.format(number);
	}

}
