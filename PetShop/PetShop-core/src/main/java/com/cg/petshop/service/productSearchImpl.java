package com.cg.petshop.service;

import java.util.List;










import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.cg.petshop.core.entitybean.Product;
import com.cg.petshop.core.entitybean.ProductCategory;
import com.cg.petshop.core.entitybean.ProductSummary;


/**
 * Session Bean implementation class ManageEmployeeBean
 */
@Stateless
public class productSearchImpl implements ProductSearch{

	
	@PersistenceContext(unitName = "defaultPersistenceUnit")
    private EntityManager entityManager;
	final static Logger logger = Logger.getLogger(productSearchImpl.class.getName());
	
	
	public ProductSummary[] rtrvAllAvailableProducts() {
		
		ProductSummary[] availableProducts;

		
		try{

			Query query = entityManager.createQuery("SELECT p FROM ProductSummary p order by p.productId desc");
			
			List<ProductSummary> products = (List<ProductSummary>)query.getResultList();
			
			System.out.println("Product List is "+products);
			
			availableProducts = new ProductSummary[products.size()];
			
			
			availableProducts = products.toArray(availableProducts);
			
			
			return availableProducts;

		}
		catch(Exception exp){
			System.out.println(exp);
			
			return null;
			
		}
	
	}
	
	/**
	 * 
	 * @return
	 */
	public ProductCategory[] rtrvProductCategories() {
		
		ProductCategory[] availableProductCategories;
		try {
			Query query = entityManager.createQuery("SELECT e FROM ProductCategory e order by e.category asc");
			List<ProductCategory> productCategoriesList = (List<ProductCategory>) query.getResultList();

			System.out.println("productCategoriesList is " +productCategoriesList);
			availableProductCategories = new ProductCategory[productCategoriesList.size()];
			
			availableProductCategories = productCategoriesList.toArray(availableProductCategories);

			return availableProductCategories;
		}
		catch(Exception exp){
			System.out.println(exp);
			return null;
			
		}
		
		
	}

	public ProductSummary[] rtrvAllAvailableProducts(String category,
			String name) {


		ProductSummary[] availableProducts;

		try{
			String queryString = null;
			
			if(name!=null && name.length()!=0)
				queryString= "SELECT e FROM ProductSummary e WHERE e.name LIKE '%" + name +"%' and e.categoryID='"+category+"'";
			else
				queryString= "SELECT e FROM ProductSummary e WHERE  e.categoryID='"+category+"'";
			
			logger.debug("Query String is ::"+queryString);
			
			Query query = entityManager.createQuery(queryString);
			
			List<ProductSummary> products = (List<ProductSummary>)query.getResultList();
			
			System.out.println("Product List is "+products);
			
			availableProducts = new ProductSummary[products.size()];
			
			availableProducts = products.toArray(availableProducts);
			
			return availableProducts;
			
			}
			catch(Exception exp){
				System.out.println(exp);
				return null;
			}
		
	
	}

	public List<Product> getAllProductsList(){
		
		
		List<Product> productsList = null;
		try{
			
			Query query = entityManager.createQuery(" SELECT e FROM Product e order by e.category asc");
			productsList = query.getResultList();
			
		}
		catch(Exception exp){
			
		}
		
		return productsList;
	}
	
	public Product getProductById(String id){
		
		Product prdObject = null;
		try{
			
			prdObject = entityManager.find(Product.class, id);
		}
		catch(Exception exp){
			
		}
		
		return prdObject;
	}

	
}
