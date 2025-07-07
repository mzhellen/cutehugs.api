package br.com.cutehugs.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cutehugs.api.dtos.CartCreateDTO;
import br.com.cutehugs.api.dtos.UserCreateDTO;
import br.com.cutehugs.api.dtos.UserResponseDTO;
import br.com.cutehugs.api.dtos.UserUpdateDTO;
import br.com.cutehugs.api.entities.User;
import br.com.cutehugs.api.mappers.UserMapper;
import br.com.cutehugs.api.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private CartService cartService;
	
	public UserService(CartService cartService) {
		this.cartService = cartService;
	}
	
	public UserResponseDTO create(UserCreateDTO userCreateDTO) throws Exception{
		User user = UserMapper.toEntity(userCreateDTO);
		if(user.getEmail() == "") {
			throw new Exception("E-mail não informado");
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User userR = userRepository.save(user);
		
		cartService.create(new CartCreateDTO(userR.getId()));
		
		UserResponseDTO userResponseDTO = UserMapper.toDTO(userR);
		return userResponseDTO;
		}
	
	public List<UserResponseDTO> list(){
		return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
	}
	
	public UserResponseDTO show(Long id){
		User user = userRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuário com o id (" + id + ") não encontrado")
		);
		return UserMapper.toDTO(user);
	}
	
	@Transactional
	public UserResponseDTO update(UserUpdateDTO userUpdateDTO) {
		User user = userRepository.findById(userUpdateDTO.id()).orElseThrow(
				() -> new RuntimeException("Usuário com o id (" + userUpdateDTO.id() + "), não encontrado para alteração")
		);
		
		if(userUpdateDTO.name() != "" || userUpdateDTO.name() != null) {
			user.setName(userUpdateDTO.name());
		}
		if(userUpdateDTO.email() != "" || userUpdateDTO.email() != null) {
			user.setEmail(userUpdateDTO.email());
		}
		if(userUpdateDTO.password() != "" || userUpdateDTO.password() != null) {
			user.setPassword(userUpdateDTO.password());
		}
	
		return UserMapper.toDTO(userRepository.save(user));
	}
	
	@Transactional
	public void destroy(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuário com o id (" + id + "), não encontrado para deleção")
		);
		userRepository.delete(user);
	}
}