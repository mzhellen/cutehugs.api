package br.com.cutehugs.api.mappers;

import br.com.cutehugs.api.dtos.UserCreateDTO;
import br.com.cutehugs.api.dtos.UserResponseDTO;
import br.com.cutehugs.api.entities.User;

public class UserMapper {
	
	public static UserResponseDTO toDTO(User user) {
		return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.isAdmin());
	    }
	public static User toEntity(UserCreateDTO userCreateDTO) {
			User user = new User();
			user.setName(userCreateDTO.name());
			user.setEmail(userCreateDTO.email());
			user.setPassword(userCreateDTO.password());
			user.setAdmin(userCreateDTO.admin());
			return user;
		}

		
}
