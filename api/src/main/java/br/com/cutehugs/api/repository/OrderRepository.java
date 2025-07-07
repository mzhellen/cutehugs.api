package br.com.cutehugs.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import br.com.cutehugs.api.entities.Order;
import br.com.cutehugs.api.entities.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@NativeQuery("SELECT order_items.* FROM order_items WHERE order_id = :id")
	public List<OrderItem> listOrderItems(Long id);
}
