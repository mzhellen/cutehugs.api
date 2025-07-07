package br.com.cutehugs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cutehugs.api.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
