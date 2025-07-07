package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

public record OrderCreateDTO(
		Long user_id,
		BigDecimal totalPrice) {}