/**
 * 
 */
package com.cg.petshop.service;

import java.math.BigInteger;
import java.util.Set;

import javax.ejb.Local;

import com.cg.petshop.core.entitybean.OrderDetails;

/**
 * @author bomvenka
 *
 */
@Local
public interface IBuyProductService {
	
	
	public String getNextOrderID();
	public BigInteger getTotalOrderCost(Set<OrderDetails> orderDetails);
	

}
