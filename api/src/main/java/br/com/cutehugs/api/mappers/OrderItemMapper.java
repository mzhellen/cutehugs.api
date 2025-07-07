package br.com.cutehugs.api.mappers;

import java.math.BigDecimal;

import br.com.cutehugs.api.dtos.OrderItemCreateDTO;
import br.com.cutehugs.api.dtos.OrderItemResponseDTO;
import br.com.cutehugs.api.entities.Order;
import br.com.cutehugs.api.entities.OrderItem;
import br.com.cutehugs.api.entities.Product;

public class OrderItemMapper {
	public static OrderItemResponseDTO toDTO(OrderItem orderItem) {
		return new 	OrderItemResponseDTO(orderItem.getId(), orderItem.getQuantity(), orderItem.getSubTotal(), orderItem.getOrder().getId(), ProductMapper.toDTO(orderItem.getProduct()));
	}
	
	public static OrderItem toEntity(OrderItemCreateDTO orderItemCreateDTO, Order order, Product product) {
		OrderItem orderItem = new OrderItem();
		orderItem.setQuantity(orderItemCreateDTO.quantity());
		orderItem.setSubTotal(product.getPrice().multiply(BigDecimal.valueOf(orderItemCreateDTO.quantity())));
		orderItem.setOrder(order);;
		orderItem.setProduct(product);
		return orderItem;
	}
}
