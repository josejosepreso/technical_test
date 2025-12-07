package hn.shoppingcart.shoppingcart_security.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import hn.shoppingcart.shoppingcart_security.Configuration;
import hn.shoppingcart.shoppingcart_security.UserRole;

@org.springframework.context.annotation.Configuration
public class AuthorizationConfig {

	@Bean
	public Map<String, List<UserRole>> authorizations() {
		final Map<String, List<UserRole>> paths = new HashMap<>();

		paths.put(Configuration.ORDERS_SERVICE_BASE_URL + "/create", List.of(UserRole.CLIENT));
		paths.put(Configuration.ORDERS_SERVICE_BASE_URL + "/confirm", List.of(UserRole.CLIENT));
		paths.put(Configuration.ORDERS_SERVICE_BASE_URL + "/all", List.of(UserRole.ADMIN));

		paths.put(Configuration.PAYMENTS_SERVICE_BASE_URL + "/create", List.of(UserRole.CLIENT));
		paths.put(Configuration.PAYMENTS_SERVICE_BASE_URL + "/all", List.of(UserRole.ADMIN));

		return paths;
	}
}
