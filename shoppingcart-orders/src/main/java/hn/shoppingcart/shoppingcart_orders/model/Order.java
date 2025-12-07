package hn.shoppingcart.shoppingcart_orders.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	private int id;

	private Client client;

	private Date date;

	private String status;

	private List<OrderDetail> orderDetails;
}
