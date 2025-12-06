package hn.shoppingcart.shoppingcart_products;

import lombok.Data;

@Data
public class ErrorResponse {
	private boolean ok;
	private String msg;

	public ErrorResponse(String msg) {
		this.ok = false;
		this.msg = msg;
	}
}
