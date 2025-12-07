package hn.shoppingcart.shoppingcart_payments.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class OrderDetailDto {
	private int productId;
	private int quantity;
	private double unitPrice;
}
