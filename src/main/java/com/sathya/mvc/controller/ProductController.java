
package com.sathya.mvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sathya.mvc.entity.ProductEntity;
import com.sathya.mvc.model.ProductModel;
import com.sathya.mvc.service.ProductService;

import jakarta.validation.Valid;


@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	 
	//product form
	@GetMapping("/productForm")
	public String getproductform(Model model) 
	{
    ProductModel productModel=new ProductModel();
	model.addAttribute("productModel",productModel);
	model.addAttribute("page","productForm");  //add index page to the product form
		
		return "index";	 // return the index.html form
		}

	
	
	@PostMapping("/saveProduct")
	public String saveProduct(@Valid ProductModel productModel,BindingResult bindingResult,Model model) {

	    if (bindingResult.hasErrors()) {
	        // Validation errors occurred
	        return "add-Product"; // reutun the add-Product.html page
	    }
	    productService.saveProductData(productModel);
	    return "redirect:/success"; // Redirect to product list or success page

	   
	}

//get products
@GetMapping("/getProducts")
public String getProducts(Model model) {
	//create empty object
    List<ProductEntity> products = productService.getAllProducts();
    model.addAttribute("products", products);
    model.addAttribute("page","getProducts");
    return "index"; 
}

// delete product by using requestparam
@GetMapping("/delete")
public String deleteProduct(@RequestParam("proId") Long proId) {
    productService.deleteProductById(proId);
    return "redirect:/getProducts";

}

//edit the product by using id
@GetMapping("/edit")
public String showEditForm(@RequestParam("proId") Long proId, Model model) {
    // Fetch the product by ID and add it to the model
    ProductModel productModel = productService.getProductById(proId);
    model.addAttribute("productModel", productModel);
    model.addAttribute("categories", Arrays.asList("Mobile", "Camera", "Printer", "Laptop", "Accessories"));

    model.addAttribute("proId", proId);
    
    return "editform";  
}

//update product using id
@PostMapping("/update")
public String updateProduct(@ModelAttribute ProductModel productModel, @RequestParam Long proId) {
	productService.updateProduct(proId, productModel);

	return "redirect:/getProducts";  // Adjust the redirect path as necessary
}

// Action method for About Us page
@GetMapping("/about-us")
public String aboutUs(Model model) {
	model.addAttribute("page","about-us");  //add about-us form to index form
    return "index"; // Returns the about-us.html page
}

// Action method for Contact Us page
@GetMapping("/contact-us")
public String contactUs(Model model) {
	model.addAttribute("page","contact-us"); //add contact-us form to the index form
    return "index"; // Returns the contact-us.html page
}
//home page index form
@GetMapping("/")
public String getHomePage() {
	
    return "index"; // Returns the index.html page
}


@GetMapping("/success")
public String message( Model model) {
	
	return "success";
}
 // get the search form
@GetMapping("/search")
public String showSearchForm(Model model) {
	model.addAttribute("page","search");
    // Return the view for the search form
    return "index";  // Corresponds to search-form.html
}

//post mapping search
@PostMapping("/searchProductById")
public String searchProductById(@RequestParam("proId") long proId, Model model) {
    // Fetch product by ID using the service layer
    ProductEntity product = productService.findProductById(proId);
    
    // Add the product entity to the model
    model.addAttribute("product", product);
    model.addAttribute("page","searchProductById");
    // Return the view to display the product details or "not found" message
    return "index";  // Corresponds to search-result.html
}




}



