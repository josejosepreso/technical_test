package hn.shoppingcart.shoppingcart_payments.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import hn.shoppingcart.shoppingcart_payments.Configuration;
import hn.shoppingcart.shoppingcart_payments.dto.OrderSummaryDto;

@Component
public class OrderServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;

	public Optional<OrderSummaryDto> getOrderById(int id) {
		final String uri = Configuration.ORDERS_SERVICE_BASE_URL + "/" + id + "/summary";

		final ResponseEntity<OrderSummaryDto> res = this.restTemplate.getForEntity(uri, OrderSummaryDto.class);

		if (!res.getStatusCode().is2xxSuccessful()) {
			return Optional.empty();
		}

		return Optional.ofNullable(res.getBody());
	}
}
