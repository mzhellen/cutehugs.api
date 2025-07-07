package br.com.cutehugs.api.mappers;

import br.com.cutehugs.api.dtos.AddressCreateDTO;
import br.com.cutehugs.api.dtos.AddressResponseDTO;
import br.com.cutehugs.api.entities.Address;
import br.com.cutehugs.api.entities.User;

public class AddressMapper {
	public static AddressResponseDTO toDTO(Address address) {
		return new AddressResponseDTO(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getComplement(), address.getZipcode(), address.getUser().getId());
	}
	
	public static Address toEntity(AddressCreateDTO addressCreateDTO, User user) {
		Address address = new Address();
		address.setStreet(addressCreateDTO.street());
		address.setNumber(addressCreateDTO.number());
		address.setNeighborhood(addressCreateDTO.neighborhood());
		address.setCity(addressCreateDTO.city());
		address.setState(addressCreateDTO.state());
		address.setComplement(addressCreateDTO.complement());
		address.setUser(user);
		return address;
	}
}
