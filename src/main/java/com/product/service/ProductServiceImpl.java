package com.product.service;

import java.util.ArrayList;
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
		List<ProductDetails> listProd = new ArrayList<>();
		List<ProductDetails> listProductDetails = productDetailsRepository.findAll();
		for (ProductDetails pd : listProductDetails) {
			if (pd.getProductGroup().getProductGroupId() == 0) {
				continue;
			}
			listProd.add(pd);
		}
		return listProd;
	}

	@Override
	public Optional<ProductDetails> getProductGroupDetails(Long productNameId) {
		return productDetailsRepository.findById(productNameId);
	}

	@Override
	public ResponseEntity<String> updateOverview() {
		Optional<ProductAudit> productAudit = productAuditRepository.findById(999L);

		if (productAudit.isPresent()) {

			ProductAudit det = productAudit.get();
			int count = det.getCount() + 1;
			det.setCount(count);
			productAuditRepository.save(det);

		}
		return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateProductGroup(Long productGroupId) {
		Optional<ProductAudit> productAudit = productAuditRepository.findByProductGroupProductGroupId(productGroupId);

		if (productAudit.isPresent()) {

			ProductAudit det = productAudit.get();
			int count = det.getCount() + 1;
			det.setCount(count);
			productAuditRepository.save(det);
		}
		return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductAudit>> getProductGroupCount() {
		List<ProductAudit> productAudit = productAuditRepository.findAll();
		return new ResponseEntity<List<ProductAudit>>(productAudit, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ProductAudit> getOverviewCount(Long auditId) {
		ProductAudit productAudit = productAuditRepository.findByAuditId(auditId);
		return new ResponseEntity<ProductAudit>(productAudit, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductAudit>> getAlertDetail() {
		Optional<List<ProductAudit>> listAlertOptional = productAuditRepository.findByCountGreaterThan(500);
		List<ProductAudit> listAlert = null;
		if (listAlertOptional.isPresent()) {
			listAlert = listAlertOptional.get();
		} else {
			return new ResponseEntity<List<ProductAudit>>(HttpStatus.GONE);
		}
		return new ResponseEntity<List<ProductAudit>>(listAlert, HttpStatus.OK);
	}
}
