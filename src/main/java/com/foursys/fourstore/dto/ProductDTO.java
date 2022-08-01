package com.foursys.fourstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foursys.fourstore.enums.*;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String description;
    private Type type;
    private Category category;
    private String modelCode;
    private Color color;
    private Sex sex;
    private Size size;
    private Fit fit;
    private Double purchasePrice;
    private Double salePrice;
    private Integer quantity;
    private String sku;

}
