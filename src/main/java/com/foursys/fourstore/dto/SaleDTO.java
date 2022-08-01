package com.foursys.fourstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foursys.fourstore.enums.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class SaleDTO {
    private Long id;
    private List<SaleItemDTO> items;
    private UserDTO user;
    private Double totalPrice;
    private String dateTime;
    private PaymentMethod paymentMethod;
    
}