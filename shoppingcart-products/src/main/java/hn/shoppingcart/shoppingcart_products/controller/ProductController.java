package hn.shoppingcart.shoppingcart_products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.shoppingcart.shoppingcart_products.model.Product;
import hn.shoppingcart.shoppingcart_products.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public List<Product> getAll() {
		return this.productService.getAll();
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable int id) {
		return this.productService.getById(id);
	}
}
