package hn.shoppingcart.shoppingcart_products.dto;

import java.util.List;

import lombok.Data;

@Data
public final class ProductPriceRequestDto {
	private List<Integer> productsIds;
}
