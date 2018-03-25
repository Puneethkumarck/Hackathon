package com.easy.repository;

import com.easy.model.Cart;
import com.easy.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository  extends CrudRepository<Cart,Integer> {
    List<Cart> findByUser(User user);
}
