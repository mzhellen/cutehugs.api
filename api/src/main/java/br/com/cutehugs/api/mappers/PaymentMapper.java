package br.com.cutehugs.api.mappers;

import br.com.cutehugs.api.dtos.PaymentCreateDTO;
import br.com.cutehugs.api.dtos.PaymentResponseDTO;
import br.com.cutehugs.api.entities.Order;
import br.com.cutehugs.api.entities.Payment;
import br.com.cutehugs.api.enums.PaymentStatus;

public class PaymentMapper {
	
	public static PaymentResponseDTO toDTO(Payment payment) {
		return new PaymentResponseDTO(payment.getId(), payment.getOrder().getId(), payment.getAmount(), payment.getPaymentMethod(), payment.getPaymentStatus());
	}
	
	public static Payment toEntity(PaymentCreateDTO paymentCreateDTO, Order order) {
		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setAmount(paymentCreateDTO.amount());
		payment.setPaymentMethod(paymentCreateDTO.paymentMethod());
		payment.setPaymentStatus(PaymentStatus.APPROVED);
		return payment;
	}
}
