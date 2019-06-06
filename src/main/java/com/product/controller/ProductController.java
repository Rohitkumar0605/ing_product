package com.product.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

}
