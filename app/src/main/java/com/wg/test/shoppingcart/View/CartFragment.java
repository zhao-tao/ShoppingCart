package com.wg.test.shoppingcart.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wg.test.shoppingcart.Entity.ProductEntity;
import com.wg.test.shoppingcart.R;
import com.wg.test.shoppingcart.Tools;

import java.util.ArrayList;

/**
 * Created by 赵涛 on 2017/10/29.
 */

class CartFragment extends Fragment {

    private MainActivity activity;
    private listAdapter listAdapter;
    private TextView tvTotalPrice;

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
        listAdapter = new listAdapter();
        lvCart.setAdapter(listAdapter);
    }

    private void initListener() {

    }

    /**
     * 计算总价
     * 获取现有的优惠券，折扣券
     */
    private void calculatePrice(ArrayList<ProductEntity> data) {
        if (null == data) return;
        int totalPrice = 0;
        for (int i = 0; i < data.size(); i++) {
            totalPrice += data.get(i).getPrice() * data.get(i).getNum();
        }
        tvTotalPrice.setText("合计：￥" + Tools.intToBigDecimal(totalPrice));
//        if (null != activity.discountList) {
//
//
//        }


    }

    public void refreshOrder() {
        if (null != listAdapter) {
            listAdapter.notifyDataSetChanged();
            calculatePrice(activity.cartList);
        }
    }

    private class listAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return null == activity.cartList ? 0 : activity.cartList.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_cart_list, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
                holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(activity.cartList.get(position).getName());
            holder.tvPrice.setText("￥" + Tools.intToBigDecimal(activity.cartList.get(position).getPrice()));
            holder.tvNum.setText("X " + activity.cartList.get(position).getNum());
            return convertView;
        }
    }

    private final class ViewHolder {
        TextView tvNum;
        TextView tvName;
        TextView tvPrice;
    }
}
