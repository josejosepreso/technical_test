package hn.shoppingcart.shoppingcart_security.dto;

import lombok.Data;

@Data
public final class ValidateJwtResponseDto {
	private final boolean isValid;
}
