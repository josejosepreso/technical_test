package hn.shoppingcart.shoppingcart_orders.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hn.shoppingcart.shoppingcart_orders.Configuration;
import hn.shoppingcart.shoppingcart_orders.dto.ProductPriceRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.ProductPriceResponseDto;

@Service
public class ProductServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;

	public List<ProductPriceResponseDto> fetchProductsPricing(List<Integer> productsIds) {
		final String uri = Configuration.PRODUCTS_SERVICE_BASE_URL + "/pricing";

		ProductPriceRequestDto req = new ProductPriceRequestDto(productsIds);

		final ResponseEntity<ProductPriceResponseDto[]> res = this.restTemplate.postForEntity(uri, req, ProductPriceResponseDto[].class);

		if (!res.getStatusCode().is2xxSuccessful()) {
			return Collections.emptyList();
		}

		return Arrays.stream(res.getBody()).toList();
	}
}
