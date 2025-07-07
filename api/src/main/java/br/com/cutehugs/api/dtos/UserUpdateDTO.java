package br.com.cutehugs.api.dtos;

public record UserUpdateDTO(
		long id,
		String name,
		String email,
		String password,
		boolean admin) {

}
