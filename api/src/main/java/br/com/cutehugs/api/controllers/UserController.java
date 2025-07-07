package br.com.cutehugs.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cutehugs.api.auth.JwtService;
import br.com.cutehugs.api.dtos.AuthDTO;
import br.com.cutehugs.api.dtos.UserCreateDTO;
import br.com.cutehugs.api.dtos.UserResponseDTO;
import br.com.cutehugs.api.dtos.UserUpdateDTO;
import br.com.cutehugs.api.entities.User;
import br.com.cutehugs.api.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public UserController(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody AuthDTO authDTO){
		
		
		
		Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password())); 
		
		User user = (User) auth.getPrincipal();
		String token = jwtService.generateToken(user);
		
		return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> create(@RequestBody UserCreateDTO userCreateDTO) throws Exception {
		UserResponseDTO userResponseDTO = userService.create(userCreateDTO); 
		return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> list(){
		return new ResponseEntity<>(userService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{user_id}")
	public ResponseEntity<UserResponseDTO> show(@PathVariable Long user_id){
		try {
			return new ResponseEntity<>(userService.show(user_id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<UserResponseDTO> update(@RequestBody UserUpdateDTO userUpdateDTO){
		try {
			return new ResponseEntity<>(userService.update(userUpdateDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{user_id}")
	public ResponseEntity<String> destroy(@PathVariable Long user_id){
		try {
			userService.destroy(user_id);
			return new ResponseEntity<>("Usuário deletado com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
