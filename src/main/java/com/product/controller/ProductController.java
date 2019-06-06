package com.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.ProductAudit;
import com.product.entity.ProductDetails;
import com.product.repository.ProductAuditRepository;
import com.product.service.ProductService;

@RestController("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductAuditRepository productAuditRepository;

	@GetMapping("/getAllProductGroup")
	public List<ProductDetails> getAllproductGroup() {
		return productService.getProductGroup();
	}

	@GetMapping("/getProductDetails/{productNameId}")
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

	@GetMapping("/getAuditDetail")
	public ResponseEntity<List<ProductAudit>> getProductGroupCount() {

		return productService.getProductGroupCount();

	}

	@GetMapping("/getOverviewAuditDetail")
	public ResponseEntity<ProductAudit> getOverviewCount() {
		return productService.getOverviewCount(999L);

	}

	@GetMapping("/getAlertDetail")
	public ResponseEntity<List<ProductAudit>> getAlertDetail() {
		return productService.getAlertDetail();
	}
}
