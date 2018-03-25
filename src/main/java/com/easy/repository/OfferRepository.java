package com.easy.repository;

import com.easy.model.Offer;
import com.easy.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository  extends CrudRepository<Offer,Integer> {
    List<Offer> findByProductOrderByPriorityOrderAsc(Product pr);
}

