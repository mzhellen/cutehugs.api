package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

import br.com.cutehugs.api.enums.OrderStatus;

public record OrderResponseDTO(
		Long id,
		BigDecimal totalPrice,
		OrderStatus orderStatus,
		Long user_id) {}
