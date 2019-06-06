package com.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.ProductAudit;

@Repository
public interface ProductAuditRepository extends JpaRepository<ProductAudit, Long> {

	ProductAudit findByAuditId(Long auditId);
	
	Optional<ProductAudit> findByProductGroupProductGroupId(Long productGroupId);
}
