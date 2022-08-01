package com.foursys.fourstore.model;

import com.foursys.fourstore.enums.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "model_code", nullable = false)
    private String modelCode;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;
    @Enumerated(EnumType.STRING)
    private Fit fit;
    private String sku;
    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;
    @Column(name = "sale_price", nullable = false)
    private Double salePrice;
    @Column(nullable = false)
    private Integer quantity;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SaleItem> saleItems;

}
