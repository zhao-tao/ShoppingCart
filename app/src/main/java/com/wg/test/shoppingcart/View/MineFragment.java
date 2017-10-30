package com.wg.test.shoppingcart.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wg.test.shoppingcart.R;

/**
 * 我的折扣和优惠券
 * Created by 赵涛 on 2017/10/29.
 */

public class MineFragment extends Fragment {

	private MainActivity activity;
	private TabLayout tabTicket;
	private ListView lvTicket;
	private boolean isCoupon = false;
	private MineFragment.ticketAdapter ticketAdapter;
	private Button btnAdd;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shop, null);
		activity = (MainActivity) getActivity();
		initView(view);
		initListener();
		return view;
	}

	private void initView(View view) {
		tabTicket = (TabLayout) view.findViewById(R.id.tl_type);
		lvTicket = (ListView) view.findViewById(R.id.lv_product);
		btnAdd = new Button(activity);
		btnAdd.setText("添加");
		ListView.LayoutParams layoutParams = new ListView.LayoutParams(ListView.LayoutParams.WRAP_CONTENT, ListView.LayoutParams
				.WRAP_CONTENT);
		btnAdd.setLayoutParams(layoutParams);
		btnAdd.setGravity(Gravity.CENTER);
		lvTicket.addFooterView(btnAdd);

		tabTicket.addTab(tabTicket.newTab().setText("折扣"));
		tabTicket.addTab(tabTicket.newTab().setText("优惠券"));

		tabTicket.getTabAt(0).select();

		ticketAdapter = new ticketAdapter();
		lvTicket.setAdapter(ticketAdapter);
	}

	private void initListener() {
		tabTicket.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				isCoupon = tab.getPosition() == 1;
				ticketAdapter.notifyDataSetChanged();
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		btnAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	private class ticketAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (isCoupon) {
				return null == activity.couponList ? 0 : activity.couponList.size();
			} else {
				return null == activity.discountList ? 0 : activity.discountList.size();
			}
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(activity).inflate(R.layout.layout_discount_list, null);
				holder.rlTicket = (RelativeLayout) convertView.findViewById(R.id.rl_ticket);
				holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
				holder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
				holder.tvDiscount = (TextView) convertView.findViewById(R.id.tv_discount);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (isCoupon) {
				holder.rlTicket.setBackgroundColor(Color.GREEN);
				holder.tvDiscount.setVisibility(View.GONE);
				holder.tvName.setText(activity.couponList.get(position).getTitle());
				holder.tvDate.setText(activity.couponList.get(position).getInDate());
			} else {
				holder.rlTicket.setBackgroundColor(Color.YELLOW);
				holder.tvName.setText(activity.discountList.get(position).getTitle());
				holder.tvDate.setText(activity.discountList.get(position).getInDate());
				holder.tvDiscount.setVisibility(View.VISIBLE);
				holder.tvDiscount.setText(activity.discountList.get(position).getRate() / 10 + "折");
			}

			return convertView;
		}
	}

	private final class ViewHolder {
		RelativeLayout rlTicket;
		TextView tvName;
		TextView tvDate;
		TextView tvDiscount;
	}
}
