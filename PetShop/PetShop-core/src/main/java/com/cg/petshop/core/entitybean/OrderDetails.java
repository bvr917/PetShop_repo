package com.cg.petshop.core.entitybean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "PST_ORDER_DETAIL")
@IdClass(OrderDetailsPK.class)
public class OrderDetails implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getPrd_quantity() {
		return prd_quantity;
	}
	public void setPrd_quantity(String prd_quantity) {
		this.prd_quantity = prd_quantity;
	}
	public String getPrice_per_product() {
		return price_per_product;
	}
	public void setPrice_per_product(String price_per_product) {
		this.price_per_product = price_per_product;
	}
	@Id
	private String product_id;
	private String prd_quantity;
	private String price_per_product;
	
	


}
