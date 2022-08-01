package com.foursys.fourstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foursys.fourstore.enums.State;
import com.foursys.fourstore.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
   
	private Long id;
    private State state;
    private String city;
    private String district;
    private String cep;
    private String street;
    private String complement;
    private Integer number;
    private User user;

}
