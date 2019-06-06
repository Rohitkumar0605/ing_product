package com.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.ProductDetails;
import com.product.service.ProductService;

@RestController("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/getAllProductGroup")
	public List<ProductDetails> getAllproductGroup() {
		return productService.getProductGroup();
	}

	@GetMapping("/getproductDetails/{productNameId}")
	public Optional<ProductDetails> getproductDetails(@PathVariable Long productNameId) {
		return productService.getProductGroupDetails(productNameId);
	}
	
	@PutMapping("/updateOverviewCount")
    public ResponseEntity<String> updateOverviewCount() {
        return productService.updateOverview();

    }
	@PutMapping("/updateProductGroupCount/{{productGroupId}}")
    public ResponseEntity<String> updateProductGroupCount(Long productGroupId) {
        return productService.updateProductGroup(productGroupId);

    }
}
