package hn.shoppingcart.shoppingcart_security.service;

import org.springframework.stereotype.Service;

import hn.shoppingcart.shoppingcart_security.Configuration;
import hn.shoppingcart.shoppingcart_security.dto.ValidateJwtRequestDto;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

	private Map<String, List<String>> authorizations;

	public JwtService() {
		this.authorizations.put(Configuration.ORDERS_SERVICE_BASE_URL + "/create", List.of("CLIENT"));
		this.authorizations.put(Configuration.ORDERS_SERVICE_BASE_URL + "/*", List.of("CLIENT"));
		this.authorizations.put(Configuration.ORDERS_SERVICE_BASE_URL + "/all", List.of("ADMIN"));

		this.authorizations.put(Configuration.PRODUCTS_SERVICE_BASE_URL + "/*", List.of());

		this.authorizations.put(Configuration.PAYMENTS_SERVICE_BASE_URL + "/create", List.of("CLIENT"));
		this.authorizations.put(Configuration.PAYMENTS_SERVICE_BASE_URL + "/all", List.of("ADMIN"));
	}

	public String generateToken(String username) throws Exception {
		return Jwts.builder()
			.subject(username)
			.claim("role", username.equals("admin") ? "ADMIN" : "CLIENT")
			.issuedAt(new Date())
			.expiration(Date.from(Instant.now().plus(Configuration.JWT_EXPIRATION_DURATION)))
			.signWith(Configuration.SECRET_KEY)
			.compact();
	}

	private Claims getTokenPayload(String token) {
		return Jwts.parser()
			.verifyWith(Configuration.SECRET_KEY)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	public String extractUserRole(String token) {
		return this.getTokenPayload(token)
			.get("role")
			.toString();
	}

	public boolean isValid(String token) {
		try{
			return this.getTokenPayload(token)
				.getExpiration()
				.after(new Date());
		} catch(Exception e) {
			return false;
		}
	}

	public boolean authorize(ValidateJwtRequestDto dto) {
		List<String> roles = this.authorizations.get(dto.getUrl());

		if (roles == null) {

		}

		if (roles.isEmpty()) {
			return true;
		}

		if (!this.isValid(dto.getToken())) {
			return false;
		}

		return roles.contains(this.extractUserRole(dto.getToken()));
	}
}
