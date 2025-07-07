package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

import br.com.cutehugs.api.enums.PaymentMethod;
import br.com.cutehugs.api.enums.PaymentStatus;

public record PaymentCreateDTO(
		Long order_id,
		BigDecimal amount, 
		PaymentMethod paymentMethod) {
}
   