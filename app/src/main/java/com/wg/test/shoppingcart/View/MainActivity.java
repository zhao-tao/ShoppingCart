package com.wg.test.shoppingcart.View;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.wg.test.shoppingcart.Entity.CouponEntity;
import com.wg.test.shoppingcart.Entity.DiscountEntity;
import com.wg.test.shoppingcart.Entity.ProductEntity;
import com.wg.test.shoppingcart.R;
import com.wg.test.shoppingcart.Tools;

import java.util.ArrayList;

import static com.wg.test.shoppingcart.Consts.TYPE_DAILY;
import static com.wg.test.shoppingcart.Consts.TYPE_DIGIT;
import static com.wg.test.shoppingcart.Consts.TYPE_FOOD;
import static com.wg.test.shoppingcart.Consts.TYPE_LIQUOR;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgTab;
    private Fragment[] fragments;
    private Fragment currentFragment;
    //    所有产品
    public ArrayList<ProductEntity> productList;
    //    所有折扣券
    public ArrayList<DiscountEntity> discountList;
    //    所有优惠券
    public ArrayList<CouponEntity> couponList;
    //    购物车列表
    public ArrayList<ProductEntity> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new Fragment[3];
        initData();
        initView();
        initListener();
    }

    /**
     * 创建初始化数据（默认的产品目录，折扣，优惠券）
     */
    private void initData() {
//        初始化产品列表
        productList = new ArrayList<>();
        productList.add(new ProductEntity(TYPE_DIGIT, "ipad", 239900));
        productList.add(new ProductEntity(TYPE_DIGIT, "iphone", 528800));
        productList.add(new ProductEntity(TYPE_DIGIT, "显示器", 89900));
        productList.add(new ProductEntity(TYPE_DIGIT, "笔记本电脑", 239900));
        productList.add(new ProductEntity(TYPE_DIGIT, "键盘", 6800));
        productList.add(new ProductEntity(TYPE_FOOD, "面包", 300));
        productList.add(new ProductEntity(TYPE_FOOD, "饼干", 500));
        productList.add(new ProductEntity(TYPE_FOOD, "蛋糕", 2000));
        productList.add(new ProductEntity(TYPE_FOOD, "牛肉", 2500));
        productList.add(new ProductEntity(TYPE_FOOD, "鱼", 1300));
        productList.add(new ProductEntity(TYPE_FOOD, "蔬菜", 300));
        productList.add(new ProductEntity(TYPE_DAILY, "餐巾纸", 1000));
        productList.add(new ProductEntity(TYPE_DAILY, "收纳箱", 2000));
        productList.add(new ProductEntity(TYPE_DAILY, "咖啡杯", 500));
        productList.add(new ProductEntity(TYPE_DAILY, "雨伞", 4500));
        productList.add(new ProductEntity(TYPE_LIQUOR, "啤酒", 200));
        productList.add(new ProductEntity(TYPE_LIQUOR, "白酒", 15000));
        productList.add(new ProductEntity(TYPE_LIQUOR, "伏特加", 23000));
//        初始化折扣券
        discountList = new ArrayList<>();
        discountList.add(new DiscountEntity(TYPE_DIGIT, 70, null, Tools.getSystemDate()));
//        初始化优惠券
        couponList = new ArrayList<>();
        couponList.add(new CouponEntity(100000, 20000, null, "2020-03-01"));
    }


    private void initView() {
        rgTab = (RadioGroup) findViewById(R.id.rg_tab);

        if (null == fragments[0]) {
            fragments[0] = new ShopFragment();
        }
        if (!fragments[0].isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragments[0]).commit();
            currentFragment = this.fragments[0];
        }
    }

    private void initListener() {
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (i) {
                    case R.id.rb_shop:
                        addOrShowFragment(fragmentTransaction, fragments[0]);
                        break;
                    case R.id.rb_person:
                        if (null == fragments[1]) {
                            fragments[1] = new MineFragment();
                        }
                        addOrShowFragment(fragmentTransaction, fragments[1]);
                        break;
                    case R.id.rb_cart:
                        if (null == fragments[2]) {
                            fragments[2] = new CartFragment();
                        }
                        addOrShowFragment(fragmentTransaction, fragments[2]);
                        ((CartFragment) fragments[2]).refreshOrder();
                        break;
                }
            }
        });
    }

    protected void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment) {
            return;
        }
        if (!fragment.isAdded()) {
            transaction.hide(currentFragment).add(R.id.fl_content, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    /**
     * 给购物车添加商品
     */
    public void addToCart(ProductEntity product) {

        if (null == cartList) {
            cartList = new ArrayList<>();
        }

        if (!cartList.contains(product)) {
            product.setNum(1);
            cartList.add(product);
            Tools.showToast(this, product.getName() + "已添加进购物车");
            return;
        }

        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getName().equals(product.getName())) {
                int num = cartList.get(i).getNum() + 1;
                cartList.get(i).setNum(num);
                Tools.showToast(this, product.getName() + num + "件已添加进购物车");
            }
        }
    }
}
