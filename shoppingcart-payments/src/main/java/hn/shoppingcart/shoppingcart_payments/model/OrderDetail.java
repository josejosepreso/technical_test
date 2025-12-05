package hn.shoppingcart.shoppingcart_payments.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetail {

	private int productId;

	private int quantity;

	private double unitPrice;
}
