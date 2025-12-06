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
import hn.shoppingcart.shoppingcart_payments.dto.order.OrderConfirmRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.order.OrderSummaryDto;

@Component
public class OrderServiceClient {

	@Autowired
	private JwtServiceClient jwtServiceClient;
	
	@Autowired
	private RestTemplate restTemplate;

	public Optional<OrderSummaryDto> getOrderById(int id) throws Exception {
		final String token = this.jwtServiceClient.getCurrentToken();

		final String uri = Configuration.ORDERS_SERVICE_BASE_URL + "/" + id + "/summary";

		final HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);

		final HttpEntity<?> req = new HttpEntity<>(headers);

		final ResponseEntity<OrderSummaryDto> res = this.restTemplate.exchange(uri, HttpMethod.GET, req, OrderSummaryDto.class);

		if (res.getStatusCode().is5xxServerError()) {
			throw new Exception("Internal server error");
		}

		return Optional.ofNullable(res.getBody());
	}

	public boolean confirmOrder(int orderId) throws Exception {
		final String token = this.jwtServiceClient.getCurrentToken();

		final String uri = Configuration.ORDERS_SERVICE_BASE_URL + "/confirm";

		final HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);
		//
		final OrderConfirmRequestDto orderConfirmRequestDto = new OrderConfirmRequestDto(orderId);
		//
		final HttpEntity<?> req = new HttpEntity<>(orderConfirmRequestDto, headers);

		final ResponseEntity<?> res = this.restTemplate.exchange(uri, HttpMethod.POST, req, String.class);

		if (!res.getStatusCode().is2xxSuccessful()) {
			throw new Exception("Error updating order status.");
		}

		return true;
	}
}
