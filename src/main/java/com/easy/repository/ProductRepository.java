package com.easy.repository;

import com.easy.model.Product;
import com.easy.model.ProductBrand;
import com.easy.model.ProductCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository   extends PagingAndSortingRepository<Product,Integer> {
    //	 public List<Product> findBy(Integer manufacturerId);
//	  public List<Product> findBySegmentId(Integer segmentId);
    public List<Product> findByBrand (ProductBrand brand);
    public List<Product> findByCategory (ProductCategory category);
}

