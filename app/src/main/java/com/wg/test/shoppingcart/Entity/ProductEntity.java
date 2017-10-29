package com.wg.test.shoppingcart.Entity;

/**
 * Created by 赵涛 on 2017/10/29.
 * 产品实体包含类型，名称，价格，数量的属性
 */

public class ProductEntity {
    private String type;
    private String name;
    private int price;
    private int num;

    public ProductEntity(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.num = 0;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
