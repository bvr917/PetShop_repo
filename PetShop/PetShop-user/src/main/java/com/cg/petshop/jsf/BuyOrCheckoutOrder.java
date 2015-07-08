package com.cg.petshop.jsf;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cg.petshop.core.entitybean.Order;
import com.cg.petshop.core.entitybean.OrderDetails;
import com.cg.petshop.core.entitybean.Product;
import com.cg.petshop.service.BuyProductService;
import com.cg.petshop.service.IBuyProductService;
import com.cg.petshop.service.ProductSearch;
import com.cg.petshop.service.productSearchImpl;

/**
 * This Class is Responsible for Buying the Products,Adding to the Cart,Checkout the Order 
 * @author bomvenka
 *
 */
@ManagedBean(name="buyOrCheckOut")
@SessionScoped
public class BuyOrCheckoutOrder {
	
	@EJB
	ProductSearch productService;
	
	@EJB
	IBuyProductService buyProductSerive;
	
	final static Logger logger = Logger.getLogger(productSearchImpl.class.getName());

	
	private int quantity;
	private Set<OrderDetails> productsInCart;
	private int noOfProductsInCart; 
	private String BuyProducts;
	public void setBuyProducts(String buyProducts) {
		BuyProducts = buyProducts;
	}

	Set<OrderDetails> boughtProductDetails;
	

	public void setBoughtProductDetails(Set<OrderDetails> boughtProductDetails) {
		this.boughtProductDetails = boughtProductDetails;
	}

	public int getNoOfProductsInCart() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		productsInCart = (Set<OrderDetails>)session.getAttribute("PRODUCTS_IN_CART");
		
		if(productsInCart!=null)
			return productsInCart.size();
		else
			return 0;
	}

	public void setNoOfProductsInCart(int noOfProductsInCart) {
		this.noOfProductsInCart = noOfProductsInCart;
	}

	/**
	 * This is responsible for adding Product to Cart and Showing Home Page to User
	 * @return next Page
	 */
	public String addProductToCart(){
		
		logger.info("Adding a Product To Cart Strats ::::");
		FacesContext context = FacesContext.getCurrentInstance();

		try{
	        String selectedProductID = (String) context.getExternalContext().getRequestParameterMap().get("productId");
	        
	        Product product = productService.getProductById(selectedProductID);

			logger.debug("Selected Product to Add to Cart:::: "+selectedProductID);
			
			OrderDetails selectedProduct = new OrderDetails();
			
			selectedProduct.setProduct_id(selectedProductID);
			selectedProduct.setPrd_quantity(Integer.toString(quantity));
			selectedProduct.setPrice_per_product(product.getPrice().multiply(new BigDecimal(quantity)).toString());
			
			HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
			
			productsInCart = (Set<OrderDetails>)session.getAttribute("PRODUCTS_IN_CART");
			
			if(productsInCart==null)
				productsInCart = new HashSet<OrderDetails>();

			productsInCart.add(selectedProduct);
			
			session.setAttribute("PRODUCTS_IN_CART", productsInCart);

			logger.info("Adding a Product To Cart Ends ::::");
			
		}
		catch(Exception exp){
			System.out.println("Exception in Adding Product to Cart :::"+exp);
			
		}
		
		return "User_Home";
		
		
		
	}
	/**
	 * This is responsible for buying the Products
	 * @return
	 */
	public Set<OrderDetails> getBoughtProductDetails(){
		
		logger.info("Buying the Product::: Starts:::");
		
		Set<OrderDetails> ordersInCart=null;
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
			ordersInCart = (Set<OrderDetails>)session.getAttribute("PRODUCTS_IN_CART");
			
			/*String orderID = buyProductSerive.getNextOrderID();
			
			Order newOrder = new Order();
			
			newOrder.setOrder_id(orderID);
			newOrder.setOrder_status("NEW");
			newOrder.setOrderDetails(ordersInCart);
			newOrder.setUser_id("venkat");
			newOrder.setOrder_price(buyProductSerive.getTotalOrderCost(ordersInCart));*/
			
			
		}
		catch(Exception exp){
			
		}
		
		return ordersInCart;
		
	}
	
	
	public String getBuyProducts(){
		
		return "BuyProducts";
	}
	
	
	
	
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Set<OrderDetails> getProductsInCart() {
		return productsInCart;
	}

	public void setProductsInCart(Set<OrderDetails> productsInCart) {
		this.productsInCart = productsInCart;
	}

	
	

}
