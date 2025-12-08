package hn.shoppingcart.shoppingcart_products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.shoppingcart.shoppingcart_products.ErrorResponse;
import hn.shoppingcart.shoppingcart_products.dto.ProductPriceRequestDto;
import hn.shoppingcart.shoppingcart_products.dto.ProductPriceResponseDto;
import hn.shoppingcart.shoppingcart_products.model.Product;
import hn.shoppingcart.shoppingcart_products.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public List<Product> getAll() {
		return this.productService.getAll();
	}

	@GetMapping
	public List<Product> getByCategory(@RequestParam String category) {
		return this.productService.getAll()
			.stream()
			.filter(product -> product.getCategory().equals(category))
			.toList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(this.productService.getById(id));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/pricing")
	public List<ProductPriceResponseDto> getPricing(@RequestBody ProductPriceRequestDto dto) {
		return this.productService.getPricing(dto);
	}

	@GetMapping("/pricing")
	public List<ProductPriceResponseDto> getPricing() {
		return this.productService.getPricing();
	}
}
