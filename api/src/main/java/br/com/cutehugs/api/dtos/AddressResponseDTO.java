package br.com.cutehugs.api.dtos;

public record AddressResponseDTO(
		Long id,
		String street,
		String number,
		String neighborhood,
		String city,
		String state,
		String complement,
		long zipcode,
		Long user_id
		) {

}
