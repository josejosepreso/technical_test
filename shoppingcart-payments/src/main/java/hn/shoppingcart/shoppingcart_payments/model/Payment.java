package hn.shoppingcart.shoppingcart_payments.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Payment {

	private int id;

	private int orderId;

	private int clientId;

	private Date date;

	private double subtotal;

	private double tax;

	private double total;

	private String paymentStatus;
}
