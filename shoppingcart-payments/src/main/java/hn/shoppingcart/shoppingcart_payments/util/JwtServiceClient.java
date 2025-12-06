package hn.shoppingcart.shoppingcart_payments.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import hn.shoppingcart.shoppingcart_payments.Configuration;
import hn.shoppingcart.shoppingcart_payments.dto.jwt.JwtRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.jwt.JwtResponseDto;
import lombok.Data;

@Data
@Component
public class JwtServiceClient {

	@Autowired
	private RestTemplate restTemplate;

	private String currentToken;

	public JwtServiceClient() {
		this.currentToken = null;
	}

	public boolean authorize(String url) {
		if (this.currentToken == null) {
			return false;
		}

		final String uri = Configuration.SECURITY_SERVICE_BASE_URL + "/validateAuth";

		JwtRequestDto req = new JwtRequestDto(this.currentToken, url);

		final ResponseEntity<JwtResponseDto> res = this.restTemplate.postForEntity(uri, req, JwtResponseDto.class);

		if (!res.getStatusCode().is2xxSuccessful()) {
			return false;
		}

		return res.getBody().isValid();
	}
}
