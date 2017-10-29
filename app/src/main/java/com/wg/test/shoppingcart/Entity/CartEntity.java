package com.wg.test.shoppingcart.Entity;

/**
 * Created by 赵涛 on 2017/10/29.
 */

public class CartEntity {
    private ProductEntity product;
    private int num;

    public CartEntity(ProductEntity product, int num) {
        this.product = product;
        this.num = num;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
