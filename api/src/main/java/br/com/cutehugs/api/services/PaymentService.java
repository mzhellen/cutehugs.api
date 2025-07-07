package br.com.cutehugs.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cutehugs.api.dtos.PaymentCreateDTO;
import br.com.cutehugs.api.dtos.PaymentResponseDTO;
import br.com.cutehugs.api.dtos.PaymentUpdateDTO;
import br.com.cutehugs.api.entities.Order;
import br.com.cutehugs.api.entities.Payment;
import br.com.cutehugs.api.mappers.PaymentMapper;
import br.com.cutehugs.api.repository.OrderRepository;
import br.com.cutehugs.api.repository.PaymentRepository;
import jakarta.transaction.Transactional;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	public PaymentResponseDTO create(PaymentCreateDTO paymentCreateDTO) {
		Order order = orderRepository.findById(paymentCreateDTO.order_id()).orElseThrow(
				() -> new RuntimeException("Pedido não encontrado")
		);
		
		Payment payment = PaymentMapper.toEntity(paymentCreateDTO, order);
		PaymentResponseDTO paymentResponseDTO = PaymentMapper.toDTO(paymentRepository.save(payment));
		return paymentResponseDTO;
	}
	
	public PaymentResponseDTO show(Long id) {
		Payment payment = paymentRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Pagamento não encontrado")
				
		);
		return PaymentMapper.toDTO(payment);
	}
	
	@Transactional
	public PaymentResponseDTO update(PaymentUpdateDTO paymentUpdateDTO) {
		Payment payment = paymentRepository.findById(paymentUpdateDTO.id()).orElseThrow(
				() -> new RuntimeException("Pagamento não encontrado")	
		);
		if(paymentUpdateDTO.paymentStatus() != null) {
			payment.setPaymentStatus(paymentUpdateDTO.paymentStatus());
		}
	
		return PaymentMapper.toDTO(paymentRepository.save(payment));
	}
	
	@Transactional
	public void destroy(Long id) {
		Payment payment = paymentRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Pagamento não encontrado")	
		);
		paymentRepository.delete(payment);
	}

}
