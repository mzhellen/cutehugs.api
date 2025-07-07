package br.com.cutehugs.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cutehugs.api.dtos.AddressCreateDTO;
import br.com.cutehugs.api.dtos.AddressResponseDTO;
import br.com.cutehugs.api.dtos.AddressUpdateDTO;
import br.com.cutehugs.api.entities.Address;
import br.com.cutehugs.api.entities.User;
import br.com.cutehugs.api.mappers.AddressMapper;
import br.com.cutehugs.api.repository.AddressRepository;
import br.com.cutehugs.api.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AddressService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public AddressResponseDTO create(AddressCreateDTO addressCreateDTO) {
		User user = userRepository.findById(addressCreateDTO.user_id()).orElseThrow(
				() -> new RuntimeException("Usuário com o id (" + addressCreateDTO.user_id() + ") não encontrado para cadastro de endereço")
		);
		Address address = AddressMapper.toEntity(addressCreateDTO, user);
		AddressResponseDTO addressResponseDTO = AddressMapper.toDTO(addressRepository.save(address));
		return addressResponseDTO;
	}
		
	public List<AddressResponseDTO> list() {
		return addressRepository.findAll().stream().map(AddressMapper::toDTO).toList();
		
	}
	
	public AddressResponseDTO show(Long id){
		Address address = addressRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Endereço com o id (" + id + ") não encontrado")
		);
		return AddressMapper.toDTO(address);
	}
	
	@Transactional
	public AddressResponseDTO update(AddressUpdateDTO addressUpdateDTO) {
		Address address = addressRepository.findById(addressUpdateDTO.id()).orElseThrow(
				() -> new RuntimeException("Endereço com o id (" + addressUpdateDTO.id() + "), não encontrado para alteração")
		);
		if(addressUpdateDTO.street() != null) {
			address.setStreet(addressUpdateDTO.street());
		}
		if(addressUpdateDTO.number() != null) {
			address.setNumber(addressUpdateDTO.number());
		}
		if(addressUpdateDTO.neighborhood() != null) {
			address.setNeighborhood(addressUpdateDTO.neighborhood());
		}
		if(addressUpdateDTO.city() != null) {
			address.setCity(addressUpdateDTO.city());
		}
		if(addressUpdateDTO.state() != null) {
			address.setState(addressUpdateDTO.state());
		}
		if(addressUpdateDTO.complement() != null) {
			address.setComplement(addressUpdateDTO.complement());
		}
		if(addressUpdateDTO.zipcode() != -1) {
			address.setZipcode(addressUpdateDTO.zipcode());
		}
		
		return AddressMapper.toDTO(addressRepository.save(address));
	}
	
	@Transactional
	public void destroy(Long id) {
		Address address = addressRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Endereço com o id (" + id + "),  não encontrado para deleção")
		);
		addressRepository.delete(address);
	}
}
