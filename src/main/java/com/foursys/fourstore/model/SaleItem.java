package com.foursys.fourstore.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale_items")
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale_item", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;

    public SaleItem(Long id, Product product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public SaleItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
