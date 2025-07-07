package br.com.cutehugs.api.mappers;

import br.com.cutehugs.api.dtos.OrderCreateDTO;
import br.com.cutehugs.api.dtos.OrderResponseDTO;
import br.com.cutehugs.api.entities.Order;
import br.com.cutehugs.api.entities.User;
import br.com.cutehugs.api.enums.OrderStatus;

public class OrderMapper {
	
	public static OrderResponseDTO toDTO(Order order) {
		return new OrderResponseDTO(order.getId(), order.getTotalPrice(), order.getOrderStatus(), order.getUser().getId());
	    }
		
		public static Order toEntity(OrderCreateDTO orderCreateDTO, User user) {
			Order order = new Order();
			order.setTotalPrice(orderCreateDTO.totalPrice());
			order.setOrderStatus(OrderStatus.PENDING);
			order.setUser(user);
			return order;
		}
}	
