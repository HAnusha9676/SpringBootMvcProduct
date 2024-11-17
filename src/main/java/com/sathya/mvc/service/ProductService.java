package com.sathya.mvc.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.sathya.mvc.entity.ProductEntity;
import com.sathya.mvc.model.ProductModel;
import com.sathya.mvc.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	//save product data
	public void saveProductData(ProductModel productModel)
	{
		double price = productModel.getProPrice();
		String category = productModel.getProCategory();
		double dprice = 0.0; 
		
		 switch (category) {
         case "mobile":
             dprice = price * 0.1; 
             break;
         case "laptop":
        	 dprice = price * 0.15;
             break;
         case "printer":
        	 dprice = price * 0.2;
             break;
         case "camera":
        	 dprice = price * 0.25;
             break;
		 }
		 
	 //Read the data from Model set the data to entity 
	 ProductEntity productEntity = new ProductEntity();
	 productEntity.setProName(productModel.getProName());
	 productEntity.setProPrice(productModel.getProPrice()); // Example for product price
	 productEntity.setProCategory(productModel.getProCategory()); // Example for category
	 productEntity.setProDescription(productModel.getProDescription()); // Example for description
	 productEntity.setProBrand(productModel.getProBrand());
	 
	 productEntity.setProPrice(dprice);
	 productEntity.setCreatedAt(LocalDate.now());
	 productEntity.setCreatedBy(System.getProperty("user.name"));
		 // save the product entity into success
		 productRepository.save(productEntity);	 
	}

	public List<ProductEntity> getAllProducts() {
		List<ProductEntity>products=productRepository.findAll();
		// TODO Auto-generated method stub
		return products;
	}
//deletig the products by uding Proid
	 public void deleteProductById(Long proId) {
	        productRepository.deleteById(proId);
	    }


	


//get the products by using proId in edit method
	    public ProductModel getProductById(Long proId) {		
			ProductEntity productEntity= productRepository.findById(proId).get();
			
		ProductModel productModel = new ProductModel();
	        productModel.setProName(productEntity.getProName());
	        productModel.setProBrand(productEntity.getProBrand());
	        productModel.setProPrice(productEntity.getProPrice());
	        productModel.setProCategory(productEntity.getProCategory());
	        productModel.setProDescription(productEntity.getProDescription());
	        
	        return productModel;

		}
          // update the product data
		 public void updateProduct(Long proId, ProductModel productModel) {
		        // Fetch the existing product entity by ID
		        ProductEntity existingProduct = productRepository.findById(proId).get();

		        // Update the fields with values from the ProductModel
		        existingProduct.setProName(productModel.getProName());
		        existingProduct.setProBrand(productModel.getProBrand());
		        existingProduct.setProPrice(productModel.getProPrice());
		        existingProduct.setProDescription(productModel.getProDescription());
		        existingProduct.setProCategory(productModel.getProCategory());

		        // Save the updated product back to the repository
		        productRepository.save(existingProduct);
		    }
		 
		 //search product
		 public ProductEntity findProductById(long proId) {
			    return productRepository.findById(proId).orElse(null);
			}



}
