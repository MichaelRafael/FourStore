package com.foursys.fourstore.model;

import com.foursys.fourstore.enums.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adresses")
public class Address {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private State state;
    private String city;
    private String district;
    private String cep;
    private String street;
    private String complement;
    private Integer number;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
   
}
