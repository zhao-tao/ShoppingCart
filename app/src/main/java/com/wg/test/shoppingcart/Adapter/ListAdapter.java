package com.wg.test.shoppingcart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wg.test.shoppingcart.Entity.CouponEntity;
import com.wg.test.shoppingcart.Entity.DiscountEntity;
import com.wg.test.shoppingcart.Entity.ProductEntity;
import com.wg.test.shoppingcart.R;
import com.wg.test.shoppingcart.Tools;
import com.wg.test.shoppingcart.View.MainActivity;

import java.util.ArrayList;

/**
 * 几种列表的适配器
 * Created by 赵涛 on 2017/10/29.
 */

public class ListAdapter extends BaseAdapter {

    private MainActivity context;
    private String currentType;
    private ArrayList<ProductEntity> mData;
    private ArrayList<ProductEntity> currentList;
    private ArrayList<DiscountEntity> discountList;
    private ArrayList<CouponEntity> couponList;

    /**
     * 产品列表
     */
    public ListAdapter(MainActivity context, ArrayList<ProductEntity> mData, String currentType) {
        this.context = context;
        this.mData = mData;
        this.currentType = currentType;

    }

    /**
     * 折扣和优惠券列表
     */
    public ListAdapter(MainActivity context, ArrayList<DiscountEntity> discountList, ArrayList<CouponEntity> couponList) {
        this.context = context;
        this.discountList = discountList;
        this.couponList = couponList;
    }

    /**
     * 购物车列表
     */
    public ListAdapter(MainActivity context, ArrayList<ProductEntity> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        currentList = new ArrayList<>();
        for (ProductEntity product : mData) {
            if (product.getType().equals(currentType)) {
                currentList.add(product);
            }
        }
        return currentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_product_list, null);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.ivAdd = (Button) convertView.findViewById(R.id.iv_add);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(currentList.get(position).getName());
        holder.tvPrice.setText("￥" + Tools.intToBigDecimal(currentList.get(position).getPrice()));

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.addToCart(currentList.get(position));
            }
        });

        return convertView;
    }

    /**
     * 刷新当前显示的产品类型
     *
     * @param currentType
     */
    public void changeProductType(String currentType) {
        this.currentType = currentType;
        notifyDataSetChanged();
    }

    /**
     * 刷新优惠列表类型
     *
     * @param currentType
     */
    public void changeMinusType(String currentType) {
        this.currentType = currentType;
        notifyDataSetChanged();
    }

    private final class ViewHolder {
        Button ivAdd;
        TextView tvName;
        TextView tvPrice;
    }
}
