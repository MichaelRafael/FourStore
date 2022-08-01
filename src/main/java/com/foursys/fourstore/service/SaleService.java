package com.foursys.fourstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foursys.fourstore.dto.SaleDTO;
import com.foursys.fourstore.exceptions.EntityNotFoundException;
import com.foursys.fourstore.exceptions.InvalidValueException;
import com.foursys.fourstore.exceptions.UnreportedEssentialFieldException;
import com.foursys.fourstore.model.Product;
import com.foursys.fourstore.model.Sale;
import com.foursys.fourstore.model.SaleItem;
import com.foursys.fourstore.model.User;
import com.foursys.fourstore.repository.ProductRepository;
import com.foursys.fourstore.repository.SaleItemRepository;
import com.foursys.fourstore.repository.SaleRepository;
import com.foursys.fourstore.repository.UserRepository;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SaleItemRepository saleItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<SaleDTO> findAll() {
        return saleRepository.findAll().stream().map(sale -> new SaleDTO()).toList();
    }

    public SaleDTO findById(Long id) {
        return new SaleDTO();
    }

    public List<SaleDTO> findByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
           return new EntityNotFoundException("Não existe um usuário com o id informado");
        });

        return saleRepository.findByUser(user).stream().map(sale -> new SaleDTO()).toList();
        
    }

    public SaleDTO save(Sale sale) {

        sale.setUser(userRepository.findById(sale.getId()).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um usuário com o id informado");
        }));

        if(sale.getItems() == null) throw new UnreportedEssentialFieldException("Produtos não informados");
        List<SaleItem> items = new ArrayList<>();
        Double totalPrice = 0.0;
        for(SaleItem saletItem : sale.getItems()) {
            Product product = productRepository.findById(sale.getId()).orElseThrow(() -> {
                return new EntityNotFoundException("Não existe um produto com o id informado");
            });

            Product saleItem = null;
			if(saleItem.getQuantity() > product.getQuantity()) throw new InvalidValueException("Quantidade superior à disponível em estoque");

            totalPrice += product.getSalePrice() * saleItem.getQuantity();
            items.add(new SaleItem(product, saleItem.getQuantity()));
        }
        sale.setItems(items);
        sale.setTotalPrice(totalPrice);

        if(sale.getPaymentMethod() == null) throw new UnreportedEssentialFieldException("Método de pagamento não informado");
        sale.setPaymentMethod(sale.getPaymentMethod());

        sale.setDateTime(LocalDateTime.now());

        Sale savedSale = saleRepository.save(sale);

        for(SaleItem item : sale.getItems()) {
            item.setSale(savedSale);
            saleItemRepository.save(item);
        }

        return modelMapper.map(savedSale, SaleDTO.class);
    }

    public void deleteById(Long id) {
        this.findById(id);
        saleRepository.deleteById(id);
    }
}
