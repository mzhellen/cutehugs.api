package br.com.cutehugs.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cutehugs.api.dtos.OrderCreateDTO;
import br.com.cutehugs.api.dtos.OrderItemCreateDTO;
import br.com.cutehugs.api.dtos.OrderItemResponseDTO;
import br.com.cutehugs.api.dtos.OrderResponseDTO;
import br.com.cutehugs.api.enums.OrderStatus;
import br.com.cutehugs.api.services.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//create
	@PostMapping
	public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderCreateDTO orderCreateDTO){
		OrderResponseDTO orderResponseDTO = orderService.create(orderCreateDTO);
		return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
	}
	
	//insert
	@PostMapping("/insertItem")
	public ResponseEntity<OrderItemResponseDTO> insert(@RequestBody OrderItemCreateDTO orderItemCreateDTO){
		OrderItemResponseDTO orderItemResponseDTO = orderService.insert(orderItemCreateDTO);
		return new ResponseEntity<>(orderItemResponseDTO, HttpStatus.CREATED);
	}
	
	//list items
	@GetMapping("/list/{order_id}")
	public ResponseEntity<List<OrderItemResponseDTO>> list(@PathVariable Long  order_id){
		return new ResponseEntity<>(orderService.list(order_id), HttpStatus.OK);
	}
	
	//show order
	@GetMapping("/show/{order_id}")
	public ResponseEntity<OrderResponseDTO> show(@PathVariable Long order_id){
		try {
			return new ResponseEntity<>(orderService.show(order_id), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//update order
	@PatchMapping("/{order_id}")
	public ResponseEntity<OrderResponseDTO> update(@PathVariable Long order_id, @RequestParam OrderStatus orderStatus){
		try{
			return new ResponseEntity<>(orderService.update(order_id, orderStatus), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//destroy order
	@DeleteMapping("/delete/{order_id}")
	public ResponseEntity<String> destroy(@PathVariable Long order_id){
		try {
			orderService.destroy(order_id);
			return new ResponseEntity<>("Pedido deletado com sucesso.", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	

}
