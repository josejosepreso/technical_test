package hn.shoppingcart.shoppingcart_products.dto;

import hn.shoppingcart.shoppingcart_products.model.Product;
import lombok.Data;

@Data
public final class ProductPriceResponseDto {
	private final int productId;
	private final double unitPrice;

	public ProductPriceResponseDto(Product product) {
		this.productId = product.getId();
		this.unitPrice = product.getPrice();
	}
}
