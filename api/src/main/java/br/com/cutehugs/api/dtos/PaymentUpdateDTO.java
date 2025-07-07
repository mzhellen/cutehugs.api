package br.com.cutehugs.api.dtos;

import br.com.cutehugs.api.enums.PaymentStatus;

public record PaymentUpdateDTO(
		Long id,
		PaymentStatus paymentStatus) {
}
