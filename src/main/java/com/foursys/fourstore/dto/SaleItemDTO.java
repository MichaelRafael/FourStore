package com.foursys.fourstore.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.foursys.fourstore.enums.PaymentMethod;
import com.foursys.fourstore.model.Product;
import com.foursys.fourstore.model.Sale;
import com.foursys.fourstore.model.SaleItem;
import com.foursys.fourstore.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleItemDTO {
	
    private Long id;
    private Product product;
    private Integer quantity;
    private Sale sale;
    
}
