package hn.shoppingcart.shoppingcart_payments.dto;

import java.util.List;

import lombok.Data;

@Data
public final class OrderSummaryDto {
	private final int orderId;
	private final int clientId;
	private final List<OrderDetailDto> orderDetails;
}
