package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.entity.ProductAudit;
import com.product.entity.ProductDetails;
import com.product.repository.ProductAuditRepository;
import com.product.repository.ProductDetailsRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	@Autowired
	private ProductAuditRepository productAuditRepository;

	@Override
	public List<ProductDetails> getProductGroup() {

		List<ProductDetails> listProductDetails = productDetailsRepository.findAll();
		for (ProductDetails pd : listProductDetails) {
			if (pd.getProductGroup().getProductGroupId() == 0) {
				continue;
			}
		}
		return listProductDetails;
	}

	@Override
	public Optional<ProductDetails> getProductGroupDetails(Long productNameId) {
		return productDetailsRepository.findById(productNameId);
	}

	@Override
	public ResponseEntity<String> updateOverview() {
		ProductAudit productAudit = productAuditRepository.findById(1L).get();
		int count = productAudit.getCount() + 1;
		productAudit.setCount(count);
		productAuditRepository.save(productAudit);
		return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateProductGroup(Long productGroupId) {
		ProductAudit productAudit = productAuditRepository.findById(productGroupId).get();
		int count = productAudit.getCount() + 1;
		productAudit.setCount(count);
		productAuditRepository.save(productAudit);

		return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
	}
}
