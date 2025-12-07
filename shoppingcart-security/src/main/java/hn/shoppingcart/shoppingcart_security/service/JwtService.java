package hn.shoppingcart.shoppingcart_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.shoppingcart.shoppingcart_security.Configuration;
import hn.shoppingcart.shoppingcart_security.UserRole;
import hn.shoppingcart.shoppingcart_security.dto.AuthRequestDto;
import hn.shoppingcart.shoppingcart_security.dto.ValidateJwtRequestDto;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

	@Autowired
	private Map<String, List<UserRole>> authorizations;

	public String generateToken(AuthRequestDto authRequestDto) throws Exception {
		final UserRole role = this.validateCredentials(authRequestDto.getUsername(), authRequestDto.getPassword());

		return Jwts.builder()
			.claim("role", role)
			.issuedAt(new Date())
			.expiration(Date.from(Instant.now().plus(Configuration.JWT_EXPIRATION_DURATION)))
			.signWith(Configuration.SECRET_KEY)
			.compact();
	}

	private UserRole validateCredentials(String username, String password) throws Exception {
		if (username.equals("user") && password.equals("abc123")) {
			return UserRole.CLIENT;
		}

		if (username.equals("admin") && password.equals("def456")) {
			return UserRole.ADMIN;
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
		final List<UserRole> roles = this.authorizations.get(dto.getUrl());

		if (roles == null || roles.isEmpty()) {
			return true;
		}

		final String role = this.extractUserRole(dto.getToken());

		final boolean authorized = roles.stream()
			.map(userRole -> userRole.name())
			.toList()
			.contains(role);

		return this.isValid(dto.getToken()) && authorized;
	}
}
