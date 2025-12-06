package hn.shoppingcart.shoppingcart_security.dto;

import lombok.Data;

@Data
public final class ValidateJwtRequestDto {
	private final String token;
	private final String url;
}
