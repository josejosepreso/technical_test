package hn.shoppingcart.shoppingcart_orders.dto;

import java.util.List;

import lombok.Data;

@Data
public final class ProductPriceRequestDto {
	private final List<Integer> productsIds;
}
