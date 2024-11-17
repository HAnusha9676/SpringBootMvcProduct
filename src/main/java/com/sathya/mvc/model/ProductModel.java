
package com.sathya.mvc.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

	public class ProductModel {
	    @NotBlank(message = "Product name is required")
	    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
	    private String proName;

	    @NotNull(message = "Price is required")
	    @DecimalMin(value = "0.1", inclusive = true, message = "Price must be greater than 0")
	    private double proPrice;

	    @NotBlank(message = "Category is required")
	    private String proCategory;

	    @Size(max = 500, message = "Description can be up to 500 characters")
	    private String proDescription;

	    @NotBlank(message = "Brand is required")
	    private String proBrand;

	    @DecimalMin(value = "0.0", inclusive = true, message = "Discounted price must be non-negative")
	    private Double discountedPrice;

	    // Getters and Setters
	


public Double getDiscountedPrice() {
	return discountedPrice;
}
public void setDiscountedPrice(Double discountedPrice) {
	this.discountedPrice = discountedPrice;
}

public String getProName() {
	return proName;
}
public void setProName(String proName) {
	this.proName = proName;
}
public double getProPrice() {
	return proPrice;
}
public void setProPrice(double proPrice) {
	this.proPrice = proPrice;
}
public String getProCategory() {
	return proCategory;
}
public void setProCategory(String proCategory) {
	this.proCategory = proCategory;
}
public String getProDescription() {
	return proDescription;
}
public void setProDescription(String proDescription) {
	this.proDescription = proDescription;
}
public String getProBrand() {
	return proBrand;
}
public void setProBrand(String proBrand) {
	this.proBrand = proBrand;
}
}
