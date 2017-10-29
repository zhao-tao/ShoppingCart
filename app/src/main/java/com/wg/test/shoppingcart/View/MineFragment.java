package com.wg.test.shoppingcart.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wg.test.shoppingcart.R;

/**
 * 我的折扣和优惠券
 * Created by 赵涛 on 2017/10/29.
 */

class MineFragment extends Fragment {

    private MainActivity activity;
    private TabLayout tabTicket;
    private ListView lvTicket;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        activity = (MainActivity) getActivity();
        initView(view);
//        initListener();
        return view;
    }

    private void initView(View view) {
        tabTicket = (TabLayout) view.findViewById(R.id.tl_type);
        lvTicket = (ListView) view.findViewById(R.id.lv_product);

        tabTicket.addTab(tabTicket.newTab().setText("折扣"));
        tabTicket.addTab(tabTicket.newTab().setText("优惠券"));

        tabTicket.getTabAt(0).select();
    }

}
