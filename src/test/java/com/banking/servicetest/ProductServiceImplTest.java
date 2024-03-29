package com.banking.servicetest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
import com.product.entity.ProductGroup;
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
	static ProductDetails pd = new ProductDetails();
	static ProductDetails pd1 = new ProductDetails();

	static Optional<ProductDetails> opd = Optional.empty();

	static ProductAudit pa = new ProductAudit();
	static ProductAudit paa = new ProductAudit();
	static List<ProductAudit> lap = new ArrayList<ProductAudit>();

	static Optional<List<ProductAudit>> paaudit = Optional.empty();

	ResponseEntity<List<ProductAudit>> expectedvalue5 = new ResponseEntity<List<ProductAudit>>(lap, HttpStatus.OK);

	static ProductGroup pg = new ProductGroup();

	ResponseEntity<ProductAudit> expectedvalue1 = new ResponseEntity<ProductAudit>(productAudit, HttpStatus.OK);

	@BeforeClass
	public static void setUp() {

		pg.setCreateDate(new Date());
		pg.setProductGroupId(0L);
		pg.setProductGroupName("Savings");

		pa.setAuditId(1L);
		pa.setCount(10);
		pa.setProductGroupName("Savings");
		pa.setUpdatedDate(new Date());
		lap.add(pa);

		paa.setAuditId(7L);
		paa.setCount(505);
		paa.setProductGroupName("Savings");
		paa.setUpdatedDate(new Date());
		/*
		 * paaudit. paaudit = Optional.of(paa);
		 */

		productAudit.setAuditId(1L);
		productAudit.setCount(1);
		productAudit.setProductGroupName("xyz");
		productAudit1 = Optional.of(productAudit);

		pd.setCreateDate(new Date());
		pd.setDuration("unlimited");
		pd.setInterestRate("fixed");
		pd.setMaxInvestment(25000);
		pd.setMinimalInvestment(5000);
		pd.setPercentage(7.0);
		pd.setProductName("iphone");
		pd.setProductNameId(11L);
		pd.setPutInAndWithdrawel("always possible");
		pd.setSpecial("target saving option");
		pd.setProductGroup(pg);

		opd = Optional.of(pd);

		pd1.setCreateDate(new Date());
		pd1.setDuration("unlimited");
		pd1.setInterestRate("fixed");
		pd1.setMaxInvestment(25000);
		pd1.setMinimalInvestment(5000);
		pd1.setPercentage(7.0);
		pd1.setProductName("iphone");
		pd1.setProductNameId(0L);
		pd1.setPutInAndWithdrawel("always possible");
		pd1.setSpecial("target saving option");

		listDetails.add(pd);
		// listDetails.add(pd1);

	}

	@Test
	public void testGetProductGroup() {
		Mockito.when(productDetailsRepository.findAll()).thenReturn(listDetails);
		List<ProductDetails> prodDetails = productServiceImpl.getProductGroup();
		assertEquals(0, prodDetails.size());
	}

	@Test
	public void testGetProductGroupDetails() {
		Mockito.when(productDetailsRepository.findById(11L)).thenReturn(opd);
		Optional<ProductDetails> pdd = productServiceImpl.getProductGroupDetails(11L);
		assertEquals(opd, pdd);
	}

	@Test
	public void testUpdateOverview() {
		// Mockito.when(productAuditRepository.findById(1L)).thenReturn(productAudit1);
		ResponseEntity<String> actval = productServiceImpl.updateOverview();
		Assert.assertEquals(expectedvalue, actval);

	}

	@Test
	public void testupdateProductGroup() {
		// Mockito.when(productAuditRepository.findById(productAudit.getAuditId())).thenReturn(productAudit1);
		ResponseEntity<String> actval1 = productServiceImpl.updateOverview();
		Assert.assertEquals(expectedvalue, actval1);

	}

	@Test
	public void getProductGroupCount() {
		Mockito.when(productAuditRepository.findAll()).thenReturn(lap);
		ResponseEntity<List<ProductAudit>> lpa1 = productServiceImpl.getProductGroupCount();
		assertEquals(expectedvalue5, lpa1);
	}

	@Test
	public void testGetOverviewCount() {
		Mockito.when(productAuditRepository.findByAuditId(1L)).thenReturn(productAudit);
		ResponseEntity<ProductAudit> actval2 = productServiceImpl.getOverviewCount(1L);
		Assert.assertEquals(expectedvalue1, actval2);

	}

	@Test
	public void getAlertDetail() {
		Mockito.when(productAuditRepository.findByCountGreaterThan(500)).thenReturn(paaudit);
		ResponseEntity<List<ProductAudit>> lpa1 = productServiceImpl.getAlertDetail();
		assertEquals(expectedvalue5, lpa1);
	}
}
