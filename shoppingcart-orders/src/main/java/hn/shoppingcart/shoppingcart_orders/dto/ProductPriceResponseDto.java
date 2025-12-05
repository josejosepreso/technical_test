package hn.shoppingcart.shoppingcart_orders.dto;

import lombok.Data;

@Data
public final class ProductPriceResponseDto {
	private final int productId;

	private final double unitPrice;
}
