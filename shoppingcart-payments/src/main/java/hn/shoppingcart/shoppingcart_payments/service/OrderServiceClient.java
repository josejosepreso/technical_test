package hn.shoppingcart.shoppingcart_payments.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hn.shoppingcart.shoppingcart_payments.Configuration;
import hn.shoppingcart.shoppingcart_payments.dto.OrderSummaryDto;

@Service
public class OrderServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;

	public Optional<OrderSummaryDto> getOrderById(int id) {
		final String uri = Configuration.ORDERS_SERVICE_BASE_URL + "/" + id + "/summary";

		final ResponseEntity<OrderSummaryDto> res = this.restTemplate.getForEntity(uri, OrderSummaryDto.class);

		if (
			!res.getStatusCode().is2xxSuccessful()
			|| res.getBody() == null
		) {
			return Optional.empty();
		}

		return Optional.of(res.getBody());
	}
}
