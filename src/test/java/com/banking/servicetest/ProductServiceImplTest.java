package com.banking.servicetest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.entity.ProductAudit;
import com.product.entity.ProductDetails;
import com.product.repository.ProductAuditRepository;
import com.product.repository.ProductDetailsRepository;
import com.product.service.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@Mock
	private ProductDetailsRepository productDetailsRepository;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductAuditRepository productAuditRepository;

	static ProductAudit productAudit = new ProductAudit();
	static Optional<ProductAudit> productAudit1 = Optional.empty();

	ResponseEntity<String> expectedvalue = new ResponseEntity<String>("Successfully updated", HttpStatus.OK);

	static List<ProductDetails> listDetails = new ArrayList<ProductDetails>();
	ProductDetails pd = new ProductDetails();

	@BeforeClass
	public static void setUp() {
		productAudit.setAuditId(1L);
		productAudit.setCount(1);
		productAudit.setProductGroupName("xyz");
		productAudit1 = Optional.of(productAudit);
	}

	/* @Test */
	public void testGetProductGroup() {
		Mockito.when(productDetailsRepository.findAll()).thenReturn(listDetails);
		// assertEquals(3, actval.size());

	}

	@Test
	public void testUpdateOverview() {
		Mockito.when(productAuditRepository.findById(1L)).thenReturn(productAudit1);
		ResponseEntity<String> actval = productServiceImpl.updateOverview();
		Assert.assertEquals(expectedvalue, actval);

	}

	@Test
	public void testupdateProductGroup() {
		Mockito.when(productAuditRepository.findById(productAudit.getAuditId())).thenReturn(productAudit1);
		ResponseEntity<String> actval1 = productServiceImpl.updateOverview();
		Assert.assertEquals(expectedvalue, actval1);

	}
}
