package com.vu.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.Nullable;

public class ProductDto {

	private Long id;
	
	@Max(value = 200)
	@NotBlank
    private String name;
	
	@Max(value = 200)
	@NotBlank
    private String link;
	
	@Max(value = 200)
	@NotBlank
    private String shortBrief;

    private Float unitPrice;
    
    @Max(value = 200)
    @NotBlank
    private String image;

    @Nullable
    private Timestamp productDate;
    
    private Boolean available;
    
    @NotBlank
    private String description;
    
    @NumberFormat
    private Integer quantity;
    
    @NumberFormat
    private Integer discount;
    
    private Boolean isNewArrival;
    private Boolean isHotSale;
    
    @NumberFormat
    private Integer views;
    
	public ProductDto() {
		super();
	}
	
	public ProductDto(Long id, String name, String link, String shortBrief, Float unitPrice, String image,
			Timestamp productDate, Boolean available, String description, Integer quantity, Integer discount,
			Boolean isNewArrival, Boolean isHotSale, Integer views) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.shortBrief = shortBrief;
		this.unitPrice = unitPrice;
		this.image = image;
		this.productDate = productDate;
		this.available = available;
		this.description = description;
		this.quantity = quantity;
		this.discount = discount;
		this.isNewArrival = isNewArrival;
		this.isHotSale = isHotSale;
		this.views = views;
	}
	
	public ProductDto(String name, String link, String shortBrief, Float unitPrice, String image, Timestamp productDate,
			Boolean available, String description, Integer quantity, Integer discount, Boolean isNewArrival,
			Boolean isHotSale, Integer views) {
		super();
		this.name = name;
		this.link = link;
		this.shortBrief = shortBrief;
		this.unitPrice = unitPrice;
		this.image = image;
		this.productDate = productDate;
		this.available = available;
		this.description = description;
		this.quantity = quantity;
		this.discount = discount;
		this.isNewArrival = isNewArrival;
		this.isHotSale = isHotSale;
		this.views = views;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getShortBrief() {
		return shortBrief;
	}

	public void setShortBrief(String shortBrief) {
		this.shortBrief = shortBrief;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Timestamp getProductDate() {
		return productDate;
	}

	public void setProductDate(Timestamp productDate) {
		this.productDate = productDate;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Boolean getIsNewArrival() {
		return isNewArrival;
	}

	public void setIsNewArrival(Boolean isNewArrival) {
		this.isNewArrival = isNewArrival;
	}

	public Boolean getIsHotSale() {
		return isHotSale;
	}

	public void setIsHotSale(Boolean isHotSale) {
		this.isHotSale = isHotSale;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}
}
