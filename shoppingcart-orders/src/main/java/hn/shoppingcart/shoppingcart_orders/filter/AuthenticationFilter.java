package hn.shoppingcart.shoppingcart_orders.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import hn.shoppingcart.shoppingcart_orders.Configuration;
import hn.shoppingcart.shoppingcart_orders.util.JwtServiceClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtServiceClient jwtServiceClient;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		final String token = authHeader.split(" ")[1];
		final String url = Configuration.API_URL_DOMAIN + request.getServletPath();

		final boolean authorized = this.jwtServiceClient.authorize(token, url);

		if (!authorized) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json");
			response.getWriter().write("{\"msg\": \"Invalid or expired token.\"}");
			return;
		}

		filterChain.doFilter(request, response);
	}
}
