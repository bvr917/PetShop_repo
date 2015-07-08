package com.cg.petshop.core.entitybean;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="order")
@Table(name = "PST_ORDER_MASTER")
public class Order {
	
	
	@Id
	private String order_id;
	private String user_id;
	private BigInteger order_price;
	private String order_status;
	
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public BigInteger getOrder_price() {
		return order_price;
	}
	public void setOrder_price(BigInteger order_price) {
		this.order_price = order_price;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	
	
	@OneToMany
	@JoinColumn(name="order_id" , referencedColumnName="order_id")
	Set<OrderDetails> orderDetails;


	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
}
