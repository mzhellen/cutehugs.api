package br.com.cutehugs.api.dtos;

public record UserCreateDTO (
		String name,
		String email,
		String password,
		boolean admin){
	
}
