package com.wg.test.shoppingcart.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wg.test.shoppingcart.Adapter.ShopListAdapter;
import com.wg.test.shoppingcart.R;

import static com.wg.test.shoppingcart.Consts.TYPE_DAILY;
import static com.wg.test.shoppingcart.Consts.TYPE_DIGIT;
import static com.wg.test.shoppingcart.Consts.TYPE_FOOD;
import static com.wg.test.shoppingcart.Consts.TYPE_LIQUOR;

/**
 * 分类商品列表
 * Created by 赵涛 on 2017/10/29.
 */

public class ShopFragment extends Fragment {

    private TabLayout tabType;
    private ListView lvProduct;
    private MainActivity activity;
    private String currentType;
    private ShopListAdapter shopListAdapter;

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
        tabType = (TabLayout) view.findViewById(R.id.tl_type);
        lvProduct = (ListView) view.findViewById(R.id.lv_product);

        String[] strings = {"电子", "食品", "日用品", "酒类"};
        for (String type : strings) {
            tabType.addTab(tabType.newTab().setText(type));
        }
        tabType.getTabAt(0).select();
        currentType = TYPE_DIGIT;

        shopListAdapter = new ShopListAdapter(activity, activity.productList, currentType);
        lvProduct.setAdapter(shopListAdapter);

    }

    private void initListener() {
        tabType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        currentType = TYPE_DIGIT;
                        break;
                    case 1:
                        currentType = TYPE_FOOD;
                        break;
                    case 2:
                        currentType = TYPE_DAILY;
                        break;
                    case 3:
                        currentType = TYPE_LIQUOR;
                        break;
                }
                shopListAdapter.changeProductType(currentType);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
