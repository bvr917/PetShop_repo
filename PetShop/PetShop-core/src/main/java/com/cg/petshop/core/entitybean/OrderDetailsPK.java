package com.cg.petshop.core.entitybean;

import java.io.Serializable;

public class OrderDetailsPK implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Override
	public int hashCode() {
		
		try{
			
			return this.order_id.hashCode()+this.product_id.hashCode();
		}
		catch(Exception exp){
			
			return super.hashCode();
		}
	}
	@Override
	public boolean equals(Object obj) {
		
		try{
		OrderDetailsPK pk = (OrderDetailsPK)obj;
		
		if(this.order_id.equals(pk.order_id) && this.product_id.equals(pk.product_id))
			return true;
		else
			return false;
		
		}
		catch(Exception exp){
			
			return false;
		}
		
		
	}
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
	private String product_id;

}
