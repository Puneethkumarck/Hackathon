package com.easy.repository;

import com.easy.model.Cart;
import com.easy.model.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem,Integer> {
    List<CartItem> findByCart(Cart cart);
}
