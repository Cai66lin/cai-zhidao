package com.caizhidao.service;

import com.caizhidao.dto.ShoppingCartDTO;
import com.caizhidao.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查询购物车
     * @return
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * 清空购物车
     */
    void cleanShoppingCart();

    /**
     * 减少购物车数据
     * @param shoppingCartDTO
     */
    void deleteShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
