package br.com.cutehugs.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;

import br.com.cutehugs.api.dtos.MercadoPagoDTO;
import br.com.cutehugs.api.dtos.PaymentCreateDTO;
import br.com.cutehugs.api.dtos.PaymentResponseDTO;
import br.com.cutehugs.api.dtos.PaymentUpdateDTO;
import br.com.cutehugs.api.services.PaymentService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Value("${mp.key}")
	private String mpKey; 
	
	@PostConstruct
	public void init() {
		MercadoPagoConfig.setAccessToken(mpKey);
	}
	
	@PostMapping
	public ResponseEntity<PaymentResponseDTO> create(@RequestBody PaymentCreateDTO paymentCreateDTO){
		PaymentResponseDTO paymentResponseDTO = paymentService.create(paymentCreateDTO);
		return new ResponseEntity<>(paymentResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/{payment_id}")
	public ResponseEntity<Object> show(@PathVariable Long payment_id){
		try {
			return new ResponseEntity<>(paymentService.show(payment_id), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<Object> update(@RequestBody PaymentUpdateDTO paymentUpdateDTO){
		try {
			return new ResponseEntity<>(paymentService.update(paymentUpdateDTO), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{payment_id}")
	public ResponseEntity<String> destroy(@PathVariable long payment_id){
		try {
			paymentService.destroy(payment_id);
			return new ResponseEntity<>("Pagamento deletado com sucesso", HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/pay")
	public ResponseEntity<?> processPayment(@RequestBody MercadoPagoDTO mercadoPagoDTO){
		
		try {
			
			PaymentClient client = new PaymentClient();
			
			PaymentCreateRequest request = PaymentCreateRequest
					.builder()
					.transactionAmount(mercadoPagoDTO.amount())
					.token(mercadoPagoDTO.token())
					.description(mercadoPagoDTO.description())
					.installments(mercadoPagoDTO.installments())
					.paymentMethodId(mercadoPagoDTO.paymentMethodId())
					.issuerId(mercadoPagoDTO.issuerId())
					.payer(
							PaymentPayerRequest.builder()
							.email(mercadoPagoDTO.email())
							.identification(
									IdentificationRequest.builder()
									.type("CPF")
									.number(mercadoPagoDTO.cpf())
									.build()
									).build()
							).build();
			
			Payment payment = client.create(request);
			
			return ResponseEntity.status(HttpStatus.OK).body(payment);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
	}
}