package com.cg.petshop.service;

import java.math.BigInteger;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.cg.petshop.core.entitybean.OrderDetails;

@Stateless
public class BuyProductService implements IBuyProductService {

	
	
	@PersistenceContext(unitName = "defaultPersistenceUnit")
    private EntityManager entityManager;
	final static Logger logger = Logger.getLogger(BuyProductService.class.getName());
	
	
	/**
	 * This Method Returns the New OrderID
	 */
	public String getNextOrderID() {

		BigInteger generatedValue=null;
		try{
			Query query = entityManager.createNativeQuery("select nextval('PSM_ORDER_SEQUENCE')");
			generatedValue = (BigInteger)query.getSingleResult();
			
		}
		catch(Exception exp){
			System.out.println(exp);
			
		}
		return "ORD"+generatedValue.toString();
	}

	/**
	 * This is responsible for getting the Total cost of the Order
	 */
	public BigInteger getTotalOrderCost(Set<OrderDetails> orderDetails) {
		
		int totalPrice=0;
		try{
			
			if(orderDetails!=null){
				
				for(OrderDetails order : orderDetails)
				{
					totalPrice=totalPrice + (Integer.parseInt(order.getPrice_per_product()));
				}
				
			}
		}
		catch(Exception exp){
			
		}
		
		return new BigInteger(Integer.toString(totalPrice));
		
		
	}
	
	
}
