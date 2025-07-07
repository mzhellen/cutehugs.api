package br.com.cutehugs.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cutehugs.api.dtos.OrderCreateDTO;
import br.com.cutehugs.api.dtos.OrderItemCreateDTO;
import br.com.cutehugs.api.dtos.OrderItemResponseDTO;
import br.com.cutehugs.api.dtos.OrderResponseDTO;
import br.com.cutehugs.api.entities.Cart;
import br.com.cutehugs.api.entities.CartItem;
import br.com.cutehugs.api.entities.Order;
import br.com.cutehugs.api.entities.OrderItem;
import br.com.cutehugs.api.entities.Product;
import br.com.cutehugs.api.entities.User;
import br.com.cutehugs.api.enums.OrderStatus;
import br.com.cutehugs.api.mappers.OrderItemMapper;
import br.com.cutehugs.api.mappers.OrderMapper;
import br.com.cutehugs.api.repository.CartItemRepository;
import br.com.cutehugs.api.repository.CartRepository;
import br.com.cutehugs.api.repository.OrderItemRepository;
import br.com.cutehugs.api.repository.OrderRepository;
import br.com.cutehugs.api.repository.ProductRepository;
import br.com.cutehugs.api.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public OrderResponseDTO create(OrderCreateDTO orderCreateDTO) {
		User user = userRepository.findById(orderCreateDTO.user_id()).orElseThrow(
				() -> new RuntimeException("Usário não encontrado para criação de pedido")
		);
		
		Order order = OrderMapper.toEntity(orderCreateDTO, user);
		OrderResponseDTO orderResponseDTO = OrderMapper.toDTO(orderRepository.save(order));
		return orderResponseDTO;
	}
	
	public OrderItemResponseDTO insert(OrderItemCreateDTO orderItemCreateDTO) {
		Order order = orderRepository.findById(orderItemCreateDTO.order_id()).orElseThrow(
				() -> new RuntimeException("Pedido não encontrado!")
		);
		Product product = productRepository.findById(orderItemCreateDTO.product_id()).orElseThrow(
				() -> new RuntimeException("Produto não encontrado!")
		);
		OrderItem orderItem = OrderItemMapper.toEntity(orderItemCreateDTO, order, product);
		OrderItemResponseDTO orderItemResponseDTO = OrderItemMapper.toDTO(orderItemRepository.save(orderItem));
		return orderItemResponseDTO;
	}
	
	public List<OrderItemResponseDTO> list(Long id){
		return orderRepository.listOrderItems(id).stream().map(OrderItemMapper::toDTO).toList();
	}
	
	public OrderResponseDTO show(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Pedido não encontrado")
		);
		return OrderMapper.toDTO(order);	
	}
	
	@Transactional
	public OrderResponseDTO update(Long id, OrderStatus orderStatus) {
		Order order = orderRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Pedido não encontrado")	
		);
		if(orderStatus != null) {
			order.setOrderStatus(orderStatus);
		}
	
		return OrderMapper.toDTO(orderRepository.save(order));
	}
	
	@Transactional
	public void destroy(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Pedido não encontrado")
		);
		
		orderRepository.delete(order);
	}
}
