package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAuditRepository extends JpaRepository<ProductAuditRepository, Long>{

}
