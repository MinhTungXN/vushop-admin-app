package com.vu.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vu.constant.MessageConstant;
import com.vu.dto.ProductDto;
import com.vu.entity.Product;
import com.vu.repository.ProductRepository;
import com.vu.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	
	public ProductServiceImpl(ProductRepository productRepository, 
								ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product = productRepository.findByLink(productDto.getLink());
		if (Optional.ofNullable(product).isEmpty()) {
			product = modelMapper.map(productDto, Product.class);
			productRepository.save(product);
		}
		ProductDto newProduct = modelMapper.map(product, ProductDto.class);
		return newProduct;
	}

	@Override
	public ProductDto editProduct(Long id, ProductDto productDto) {
		Product product = new Product();
		if (Optional.ofNullable(productRepository.findById(id)).isPresent()) {
			product = productRepository.findById(id).get();
			product = modelMapper.map(productDto, Product.class);
			product.setId(id);
			productRepository.save(product);
		}
		ProductDto existingProduct = modelMapper.map(product, ProductDto.class);
		return existingProduct;
	}

	@Override
	public void removeProduct(Long productId) {
		Product product = productRepository.findById(productId).orElse(null);
        if (Optional.ofNullable(product).isPresent()) {
        	LOGGER.info(MessageConstant.DELETE_PRODUCT_BY_ID, productId);
        	productRepository.deleteById(product.getId());
        }
	}

	@Override
	public List<ProductDto> getProducts() {
		List<Product> products = productRepository.findAll();
		return products
				.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getRandomProducts() {
		List<Product> products = productRepository.findByRandom();
		return products
				.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getProductsByKeyword(Optional<String> keyword) {
		List<Product> products;
		
		if (keyword.isPresent()) {
			String formattedKeyword = "%" + keyword.get() + "%";
			products = productRepository.findByNameContains(formattedKeyword);
		} else {
			products = productRepository.findByRandom();
		}
		
		return products
				.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getProductsByStatus(String productStatus) {
		List<Product> products = productRepository.findByStatus(productStatus);
		return products
				.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductByLink(String productLink) {
		Product product = productRepository.findByLink(productLink);
		return modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Long productId) {
		Product entity = productRepository.findById(productId).orElse(null);
		if (Optional.ofNullable(entity).isPresent()) {
			return modelMapper.map(entity, ProductDto.class);
		}
		return null;
	}

}
