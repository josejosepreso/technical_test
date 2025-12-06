package hn.shoppingcart.shoppingcart_orders.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import hn.shoppingcart.shoppingcart_orders.Configuration;
import hn.shoppingcart.shoppingcart_orders.dto.jwt.JwtRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.jwt.JwtResponseDto;

@Component
public class JwtServiceClient {

	@Autowired
	private RestTemplate restTemplate;

	public boolean isTokenValid(String token) {
		final String uri = Configuration.SECURITY_SERVICE_BASE_URL + "/validateToken";

		JwtRequestDto req = new JwtRequestDto(token);

		final ResponseEntity<JwtResponseDto> res = this.restTemplate.postForEntity(uri, req, JwtResponseDto.class);

		if (!res.getStatusCode().is2xxSuccessful()) {
			return false;
		}

		return res.getBody().isValid();
	}
}
