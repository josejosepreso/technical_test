package hn.shoppingcart.shoppingcart_security.service;

import org.springframework.stereotype.Service;

import hn.shoppingcart.shoppingcart_security.Configuration;
import hn.shoppingcart.shoppingcart_security.dto.AuthRequestDto;
import hn.shoppingcart.shoppingcart_security.dto.ValidateJwtRequestDto;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

final class UserRole {
	public static final String CLIENT_ROLE_DESCRIPTION = "CLIENT";
	public static final String ADMIN_ROLE_DESCRIPTION = "ADMIN";
}

@Service
public class JwtService {

	private Map<String, List<String>> authorizations;

	public JwtService() {
		this.authorizations = new HashMap<>();

		this.authorizations.put(Configuration.ORDERS_SERVICE_BASE_URL + "/create", List.of(UserRole.CLIENT_ROLE_DESCRIPTION));
		this.authorizations.put(Configuration.ORDERS_SERVICE_BASE_URL + "/all", List.of(UserRole.ADMIN_ROLE_DESCRIPTION));

		this.authorizations.put(Configuration.PAYMENTS_SERVICE_BASE_URL + "/create", List.of(UserRole.CLIENT_ROLE_DESCRIPTION));
		this.authorizations.put(Configuration.PAYMENTS_SERVICE_BASE_URL + "/all", List.of(UserRole.ADMIN_ROLE_DESCRIPTION));
	}

	public String generateToken(AuthRequestDto authRequestDto) throws Exception {
		final String role = this.validateCredentials(authRequestDto.getUsername(), authRequestDto.getPassword());

		return Jwts.builder()
			.claim("role", role)
			.issuedAt(new Date())
			.expiration(Date.from(Instant.now().plus(Configuration.JWT_EXPIRATION_DURATION)))
			.signWith(Configuration.SECRET_KEY)
			.compact();
	}

	private String validateCredentials(String username, String password) throws Exception {
		if (username.equals("user") && password.equals("user")) {
			return UserRole.CLIENT_ROLE_DESCRIPTION;
		}

		if (username.equals("admin") && password.equals("admin")) {
			return UserRole.ADMIN_ROLE_DESCRIPTION;
		}

		throw new Exception("Invalid credentials.");
	}

	private Claims getTokenPayload(String token) {
		return Jwts.parser()
			.verifyWith(Configuration.SECRET_KEY)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	private String extractUserRole(String token) {
		try { return this.getTokenPayload(token).get("role").toString(); }
		catch(Exception e) { return ""; }
	}

	private boolean isValid(String token) {
		try{ return this.getTokenPayload(token).getExpiration().after(new Date()); }
		catch(Exception e) { return false; }
	}

	public boolean authorize(ValidateJwtRequestDto dto) {
		System.out.println(dto.getUrl());
		System.out.println(this.authorizations);
		final List<String> roles = this.authorizations.get(dto.getUrl());

		if (roles == null || roles.isEmpty()) {
			return true;
		}

		final String role = this.extractUserRole(dto.getToken());

		return this.isValid(dto.getToken()) && roles.contains(role);
	}
}
