package com.foursys.fourstore.model;

import com.foursys.fourstore.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales")
public class Sale {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale", nullable = false)
    private Long id;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    @Column(nullable = false)
    private Double totalPrice;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    
}
