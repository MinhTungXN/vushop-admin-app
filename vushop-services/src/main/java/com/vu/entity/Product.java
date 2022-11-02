package com.vu.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vu.constant.EntityConstant;

@Entity
@Table(name = EntityConstant.PRODUCTS_TABLE)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = EntityConstant.ID_COLUMN)
    private Long id;

	@Column(name = EntityConstant.NAME_COLUMN)
    private String name;

	@Column(name = EntityConstant.LINK_COLUMN)
    private String link;

	@Column(name = EntityConstant.SHORT_BRIEF_COLUMN)
    private String shortBrief;
	
	@Column(name = EntityConstant.UNIT_PRICE_COLUMN)
    private Float unitPrice;

	@Column(name = EntityConstant.IMAGE_COLUMN)
    private String image;
	
	@Column(name = EntityConstant.PRODUCT_DATE_COLUMN)
    private Timestamp productDate;

	@Column(name = EntityConstant.AVAILABLE_COLUMN)
    private Boolean available;

	@Column(name = EntityConstant.DESCRIPTION_COLUMN)
    private String description;
	
	@Column(name = EntityConstant.QUANTITY_COLUMN)
    private Integer quantity;
	
	@Column(name = EntityConstant.DISCOUNT_COLUMN)
    private Integer discount;
	
	@Column(name = EntityConstant.IS_NEW_ARRIVAL_COLUMN)
    private Boolean isNewArrival;
	
	@Column(name = EntityConstant.IS_HOT_SALE_COLUMN)
    private Boolean isHotSale;

	@Column(name = EntityConstant.VIEWS_COLUMN)
    private Integer views;

	public Product() {
		super();
	}

	public Product(Long id, String name, String link, String shortBrief, Float unitPrice, String image,
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

	public Product(String name, String link, String shortBrief, Float unitPrice, String image, Timestamp productDate,
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
