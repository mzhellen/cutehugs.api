package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

public record MercadoPagoDTO(
		String token,
		BigDecimal amount,
		String description,
		int installments,
		String paymentMethodId,
		String issuerId,
		String email,
		String cpf) {

}
