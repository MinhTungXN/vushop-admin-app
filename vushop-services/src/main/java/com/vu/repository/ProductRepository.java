package com.vu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vu.constant.ParameterConstant;
import com.vu.constant.QueryConstant;
import com.vu.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByLink(String productLink);

	@Query(nativeQuery = true, 
			value = QueryConstant.FIND_PRODUCTS_BY_NAME_CONTAINS_KEYWORD)
	List<Product> findByStatus(@Param(ParameterConstant.KEYWORD_PARAMETER) String productStatus);
	
	@Query(nativeQuery = true, 
			value = QueryConstant.FIND_RANDOM_PRODUCTS)
	List<Product> findByRandom();
	
	@Query(nativeQuery = true, 
			value = QueryConstant.FIND_PRODUCTS_BY_NAME_CONTAINS_KEYWORD)
	List<Product> findByNameContains(@Param(ParameterConstant.KEYWORD_PARAMETER) String keyword);
}
