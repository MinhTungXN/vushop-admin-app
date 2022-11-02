package com.vu.service;

import java.util.List;
import java.util.Optional;

import com.vu.dto.ProductDto;

public interface ProductService {

	ProductDto addProduct(ProductDto productDto);
	
	ProductDto editProduct(Long id, ProductDto productDto);
	
	void removeProduct(Long productId);

	List<ProductDto> getProducts();
	
	List<ProductDto> getRandomProducts();
	
	List<ProductDto> getProductsByKeyword(Optional<String> keyword);
	
	List<ProductDto> getProductsByStatus(String productStatus);
	
	ProductDto getProductByLink(String productLink);
	
	ProductDto getProductById(Long productId);
}
