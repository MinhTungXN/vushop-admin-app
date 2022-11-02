package com.vu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vu.constant.MessageConstant;
import com.vu.constant.UrlConstant;
import com.vu.constant.SecurityConstant;
import com.vu.dto.ProductDto;
import com.vu.payload.request.SearchRequest;
import com.vu.service.ProductService;
import com.vu.service.SecurityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping(UrlConstant.PRODUCT_API_URL)
@Api(value = "vu-shop-v02", description = "Operations pertaining to products in vushopv02")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SecurityService securityService;
	
	@ApiOperation(value = "View product list", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "The resource you were trying to reach is not exist")
	})
	@GetMapping({"/all"})
	public ResponseEntity<?> getAll(@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		List<ProductDto> productDtos = productService.getProducts();
		if (productDtos.isEmpty()) {
			return new ResponseEntity<List<ProductDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.OK);	
	}
	
	@ApiOperation(value = "Get a product with an ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") Long id,
									@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		ProductDto productDto = productService.getProductById(id);
		if (productDto == null) {
			return new ResponseEntity<ProductDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Search products with a keyword")
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest,
									@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {		
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		List<ProductDto> productDtos = null;
		if (searchRequest.getKeyword() != null 
				&& !searchRequest.getKeyword().isEmpty()) {
			productDtos = productService.getProductsByKeyword(Optional.ofNullable(searchRequest.getKeyword()));
			if (productDtos.isEmpty()) {
				return new ResponseEntity<List<ProductDto>>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add a new product")
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody ProductDto productDto,
								 @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			productService.addProduct(productDto);
			return new ResponseEntity<String>("Add successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable("id") Long id, 
								  @RequestBody ProductDto productDto,
								  @RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		try {
			if (productService.getProductById(id) == null) {
				return new ResponseEntity<String>("Not found!", HttpStatus.BAD_REQUEST);
			}
			productService.editProduct(id, productDto);
			return new ResponseEntity<String>("Edit successfully!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id,
									@RequestHeader(SecurityConstant.AUTH_TOKEN_HEADER) final String authToken) {
		if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
			return new ResponseEntity<String>(MessageConstant.UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
		}
		productService.removeProduct(id);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}
