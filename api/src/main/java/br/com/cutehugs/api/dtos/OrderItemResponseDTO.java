package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
		Long id,
		int quantity,
		BigDecimal subTotal,
		Long order_id,
		ProductResponseDTO producT) {

}
