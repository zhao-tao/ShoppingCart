package com.wg.test.shoppingcart.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wg.test.shoppingcart.Adapter.ShopListAdapter;
import com.wg.test.shoppingcart.Entity.CouponEntity;
import com.wg.test.shoppingcart.Entity.DiscountEntity;
import com.wg.test.shoppingcart.Entity.ProductEntity;
import com.wg.test.shoppingcart.R;
import com.wg.test.shoppingcart.Tools;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.wg.test.shoppingcart.Tools.intToBigDecimal;

/**
 * Created by 赵涛 on 2017/10/29.
 */

public class CartFragment extends Fragment {

	private MainActivity activity;
	private ShopListAdapter listAdapter;
	private TextView tvTotalPrice;
	private LinearLayout llDisCount;
	private TextView tvDisCount;
	private TextView tvDisCountDate;
	private LinearLayout llCoupon;
	private TextView tvCoupon;
	private TextView tvCouponDate;
	//	已使用了折扣（不再判断优惠券）
	private boolean usedDiscount = false;
	//	最终下单的价格
	private BigDecimal orderPrice;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cart, null);
		activity = (MainActivity) getActivity();
		initView(view);
		initListener();
		calculatePrice(activity.cartList);
		return view;
	}

	private void initView(View view) {
		ScrollListView lvCart = (ScrollListView) view.findViewById(R.id.lv_cart);
		tvTotalPrice = (TextView) view.findViewById(R.id.tv_total);

		llDisCount = (LinearLayout) view.findViewById(R.id.ll_discount);
		tvDisCount = (TextView) view.findViewById(R.id.tv_discount);
		tvDisCountDate = (TextView) view.findViewById(R.id.tv_discount_date);

		llCoupon = (LinearLayout) view.findViewById(R.id.ll_coupon);
		tvCoupon = (TextView) view.findViewById(R.id.tv_coupon);
		tvCouponDate = (TextView) view.findViewById(R.id.tv_coupon_date);

		listAdapter = new ShopListAdapter(activity, activity.cartList);
		lvCart.setAdapter(listAdapter);
	}

	private void initListener() {

	}

	/**
	 * 计算总价
	 * 根据题目的例子，倒推的逻辑为，现有的折扣和优惠券只能用一张，并且先使用折扣券
	 */
	private void calculatePrice(ArrayList<ProductEntity> data) {
		if (null == data) return;
		int i = totalPrice(data);

//		先使用折扣券，折扣券相关逻辑
		if (null != activity.discountList && activity.discountList.size() != 0) {

//			获取购物车中所有产品的类别
			ArrayList<String> type = new ArrayList<>();
			for (ProductEntity productEntity : data) {
				if (type.size() == 0 || !type.contains(productEntity.getType())) {
					type.add(productEntity.getType());
				}
			}

//			折扣券在有效期，购物车中有折扣类型的商品
			for (DiscountEntity discount : activity.discountList) {
				if (type.contains(discount.getType()) && Tools.canUseDate(discount.getEnd_date())) {
					i = i - calDiscount(data, discount);
					break;
				}
			}
		}

//		优惠券相关逻辑
		if (!usedDiscount && null != activity.couponList && activity.couponList.size() != 0) {
			for (CouponEntity coupon : activity.couponList) {
				if (Tools.canUseDate(coupon.getEnd_date()) && i > coupon.getMin_price()) {
					i = i - coupon.getMinus();
					llCoupon.setVisibility(View.VISIBLE);
					tvCoupon.setText(coupon.getTitle());
					tvCouponDate.setText(coupon.getInDate());
					break;
				}
			}
		}

		orderPrice = intToBigDecimal(i);

		tvTotalPrice.setText("合计：￥" + intToBigDecimal(i));
	}

	/**
	 * 计算折扣需要减去的价格
	 * (题干中看不出是给同类型中单个用折扣券，还是所有一起用折扣券) 当前默认为所有同类产品都用折扣券
	 *
	 * @param data
	 * @param discount
	 */
	private int calDiscount(ArrayList<ProductEntity> data, DiscountEntity discount) {
		int minus = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getType().equals(discount.getType())) {
				minus += (data.get(i).getPrice() * (100 - discount.getRate())) / 100;
			}
		}
		llDisCount.setVisibility(View.VISIBLE);
		tvDisCount.setText(discount.getTitle());
		tvDisCountDate.setText(discount.getInDate());
		usedDiscount = true;
		return minus;
	}

	/**
	 * 计算未使用任何优惠的总价
	 *
	 * @param data
	 * @return
	 */
	private int totalPrice(ArrayList<ProductEntity> data) {
		int totalPrice = 0;
		for (int i = 0; i < data.size(); i++) {
			totalPrice += data.get(i).getPrice() * data.get(i).getNum();
		}
		return totalPrice;
	}

	public void refreshOrder() {
		if (null != listAdapter) {
			listAdapter.reFreshCart(activity.cartList);
			calculatePrice(activity.cartList);
		}
	}
}
