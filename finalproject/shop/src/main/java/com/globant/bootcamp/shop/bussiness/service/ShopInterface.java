package com.globant.bootcamp.shop.bussiness.service;

import com.globant.bootcamp.shop.model.Product;

public interface ShopInterface {

    int getTotalProducts();
    Product getProductById(int id);

}
