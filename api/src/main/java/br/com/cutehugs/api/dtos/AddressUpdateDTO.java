package br.com.cutehugs.api.dtos;

public record AddressUpdateDTO(
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
