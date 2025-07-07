package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

public record OrderItemCreateDTO(
		int quantity,
		BigDecimal subTotal,
		Long order_id, 
		Long product_id) {

}
