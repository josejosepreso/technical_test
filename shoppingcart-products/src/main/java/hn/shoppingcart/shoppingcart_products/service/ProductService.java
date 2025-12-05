package hn.shoppingcart.shoppingcart_products.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hn.shoppingcart.shoppingcart_products.model.Product;

import hn.shoppingcart.shoppingcart_products.Configuration;
import hn.shoppingcart.shoppingcart_products.dto.ProductPriceRequestDto;
import hn.shoppingcart.shoppingcart_products.dto.ProductPriceResponseDto;

@Service
public class ProductService {

	@Autowired
	private RestTemplate restTemplate;

	public List<Product> getAll() {
		final String uri = Configuration.PRODUCTS_API_BASE_URL + "/products";

		final ResponseEntity<Product[]> res = this.restTemplate.getForEntity(uri, Product[].class);

		if (!res.getStatusCode().is2xxSuccessful()) {
			// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
			return Collections.emptyList();
		}

		return Arrays.stream(res.getBody()).toList();
	}

	public Product getById(int id) {
		final String uri = Configuration.PRODUCTS_API_BASE_URL + String.format("/products/%s", id);

		final ResponseEntity<Product> res = this.restTemplate.getForEntity(uri, Product.class);

		if (!res.getStatusCode().is2xxSuccessful()) {
			return null;
		}

		return res.getBody();
	}

	public List<ProductPriceResponseDto> getPricing(ProductPriceRequestDto dto) {
		final List<Integer> productsIds = dto.getProductsIds();

		return this.getAll().stream()
			.filter(product -> productsIds.contains(product.getId()))
			.map(ProductPriceResponseDto::new)
			.toList();
	}

	public List<ProductPriceResponseDto> getPricing() {
		return this.getAll().stream()
			.map(ProductPriceResponseDto::new)
			.toList();
	}
}
