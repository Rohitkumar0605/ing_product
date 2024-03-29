package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.entity.ProductAudit;
import com.product.entity.ProductDetails;

@Service
public interface ProductService {

	public List<ProductDetails> getProductGroup();

	public Optional<ProductDetails> getProductGroupDetails(Long productNameId);

	ResponseEntity<String> updateOverview();

	ResponseEntity<String> updateProductGroup(Long productGroupId);

	ResponseEntity<List<ProductAudit>> getProductGroupCount();

	ResponseEntity<ProductAudit> getOverviewCount(Long auditId);
	
	public ResponseEntity<List<ProductAudit>> getAlertDetail();
}
