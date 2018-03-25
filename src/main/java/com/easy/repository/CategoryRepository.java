package com.easy.repository;

import com.easy.model.ProductCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryName(String categoryName);
}