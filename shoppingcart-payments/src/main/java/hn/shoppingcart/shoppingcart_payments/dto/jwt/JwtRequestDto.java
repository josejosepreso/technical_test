package hn.shoppingcart.shoppingcart_payments.dto.jwt;

import lombok.Data;

@Data
public final class JwtRequestDto {
	private final String token;
	private final String url;
}
