package hn.shoppingcart.shoppingcart_payments.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import hn.shoppingcart.shoppingcart_payments.Configuration;
import hn.shoppingcart.shoppingcart_payments.dto.OrderSummaryDto;

@Component
public class OrderServiceClient {

	@Autowired
	private JwtServiceClient jwtServiceClient;
	
	@Autowired
	private RestTemplate restTemplate;

	public Optional<OrderSummaryDto> getOrderById(int id) {
		final String token = this.jwtServiceClient.getCurrentToken();

		final String uri = Configuration.ORDERS_SERVICE_BASE_URL + "/" + id + "/summary";

		final HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);

		final HttpEntity<?> req = new HttpEntity<>(headers);

		final ResponseEntity<OrderSummaryDto> res = this.restTemplate.exchange(uri, HttpMethod.GET, req, OrderSummaryDto.class);

		return !res.getStatusCode().is2xxSuccessful()
			? Optional.empty()
			: Optional.ofNullable(res.getBody());
	}
}
