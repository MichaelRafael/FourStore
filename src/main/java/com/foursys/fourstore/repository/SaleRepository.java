package com.foursys.fourstore.repository;

import com.foursys.fourstore.model.Sale;
import com.foursys.fourstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByUser(User user);
}
