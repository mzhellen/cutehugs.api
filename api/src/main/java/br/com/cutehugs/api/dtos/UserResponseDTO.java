package br.com.cutehugs.api.dtos;

public record UserResponseDTO(
		Long id, 
		String name, 
		String email, 
		boolean admin) {
}
