package com.foursys.fourstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    
	private Long id;
    private String fullName;
    private String cpf;
    private String email;
    private List<AddressDTO> adresses;
    private List<SaleDTO> sales;
}
