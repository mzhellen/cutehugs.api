package br.com.cutehugs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cutehugs.api.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
