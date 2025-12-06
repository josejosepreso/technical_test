package hn.shoppingcart.shoppingcart_security;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public final class Configuration {
	public static final String secretString = "1c8ba7d0-264d-4317-854e-3d835eeb6531";
	public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Configuration.secretString.getBytes(StandardCharsets.UTF_8));
	// 
	public static final Duration JWT_EXPIRATION_DURATION = Duration.ofMinutes(1);

	public static final String PRODUCTS_SERVICE_BASE_URL = "http://localhost:8080/api/products";
	public static final String ORDERS_SERVICE_BASE_URL = "http://localhost:8081/api/orders";
	public static final String PAYMENTS_SERVICE_BASE_URL = "http://localhost:8082/api/payments";
}
