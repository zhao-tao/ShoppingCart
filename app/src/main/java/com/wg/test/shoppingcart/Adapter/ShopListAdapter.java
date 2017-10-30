package com.wg.test.shoppingcart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wg.test.shoppingcart.Entity.ProductEntity;
import com.wg.test.shoppingcart.R;
import com.wg.test.shoppingcart.Tools;
import com.wg.test.shoppingcart.View.MainActivity;

import java.util.ArrayList;

/**
 * 商品和购物车列表Adapter
 * Created by 赵涛 on 2017/10/29.
 */

public class ShopListAdapter extends BaseAdapter {

	private MainActivity context;
	private String currentType;
	private ArrayList<ProductEntity> mData;
	private ArrayList<ProductEntity> currentList;
	private boolean isCart = false;

	/**
	 * 产品列表
	 */
	public ShopListAdapter(MainActivity context, ArrayList<ProductEntity> mData, String currentType) {
		this.context = context;
		this.mData = mData;
		this.currentType = currentType;

	}

	/**
	 * 购物车列表
	 */
	public ShopListAdapter(MainActivity context, ArrayList<ProductEntity> mData) {
		this.context = context;
		currentList = mData;
		isCart = true;
	}

	@Override
	public int getCount() {
		if (!isCart) {
			currentList = new ArrayList<>();
			for (ProductEntity product : mData) {
				if (product.getType().equals(currentType)) {
					currentList.add(product);
				}
			}
		}
		return null == currentList ? 0 : currentList.size();
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
			holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
			holder.ivAdd = (Button) convertView.findViewById(R.id.iv_add);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvName.setText(currentList.get(position).getName());
		holder.tvPrice.setText("￥" + Tools.intToBigDecimal(currentList.get(position).getPrice()));

		if (isCart) {
			holder.ivAdd.setVisibility(View.GONE);
			holder.tvNum.setVisibility(View.VISIBLE);
			holder.tvNum.setText("X " + currentList.get(position).getNum());
		} else {
			holder.ivAdd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					context.addToCart(currentList.get(position));
				}
			});
		}
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
	 * 刷新购物车列表
	 */
	public void reFreshCart(ArrayList<ProductEntity> mData) {
		currentList = mData;
		notifyDataSetChanged();
	}

	private final class ViewHolder {
		Button ivAdd;
		TextView tvName;
		TextView tvPrice;
		TextView tvNum;
	}
}
