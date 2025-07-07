package br.com.cutehugs.api.dtos;

public record CartItemCreateDTO(
		int quantity,
		Long cart_id,
		Long product_id) {

}
