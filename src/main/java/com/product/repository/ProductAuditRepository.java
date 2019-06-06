package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.ProductAudit;

@Repository
public interface ProductAuditRepository extends JpaRepository<ProductAudit, Long> {
	ProductAudit findByProductGroupProductGroupId(Long productGroupId);

	ProductAudit findByAuditId(Long auditId);
}
