package com.wg.test.shoppingcart;

import android.content.Context;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(Calendar.getInstance().getTime());
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
     * @param price
     * @return
     */
    public static BigDecimal intToBigDecimal(int price) {
        BigDecimal bigDecimal = new BigDecimal(price);
        System.out.print(bigDecimal.divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY));
        return bigDecimal.divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY);
    }
}
