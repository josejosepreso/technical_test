package hn.shoppingcart.shoppingcart_security.dto;

import lombok.Data;

@Data
public final class AuthRequestDto {
	private final String username;
	private final String password;
}
