package com.easy.repository;

import com.easy.model.ProductStore;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StoreRepository  extends PagingAndSortingRepository<ProductStore, Integer> {

}
