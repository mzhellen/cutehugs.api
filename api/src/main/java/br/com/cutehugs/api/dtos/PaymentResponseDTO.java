package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

import br.com.cutehugs.api.enums.PaymentMethod;
import br.com.cutehugs.api.enums.PaymentStatus;

public record PaymentResponseDTO(
		Long id,
		Long order_id, 
		BigDecimal amount, 
		PaymentMethod paymentMethod,
		PaymentStatus paymentStatus) {
}
