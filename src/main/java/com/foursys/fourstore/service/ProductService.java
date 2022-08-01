package com.foursys.fourstore.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foursys.fourstore.dto.ProductDTO;
import com.foursys.fourstore.exceptions.EntityNotFoundException;
import com.foursys.fourstore.exceptions.InvalidValueException;
import com.foursys.fourstore.exceptions.UnreportedEssentialFieldException;
import com.foursys.fourstore.model.Product;
import com.foursys.fourstore.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ModelMapper modelMapper;
   

    public ProductDTO save(Product product) {

        if(product.getDescription() != null) product.setDescription(product.getDescription());

        if(product.getType() != null) product.setType(product.getType());

        if(product.getCategory() != null) product.setCategory(product.getCategory());

        if(product.getColor() == null) throw new UnreportedEssentialFieldException("Cor não informada");
        product.setColor(product.getColor());

        if(product.getSex() != null) product.setSex(product.getSex());

        if(product.getSize() == null) throw new UnreportedEssentialFieldException("Tamanho não informado");
        product.setSize(product.getSize());

        if(product.getFit() != null) product.setFit(product.getFit());

        if(product.getPurchasePrice() == null) throw new UnreportedEssentialFieldException("Preço de compra não informado");
        if(product.getPurchasePrice() < 0) throw new InvalidValueException("Preço de compra inválido");
        product.setPurchasePrice(product.getPurchasePrice());

        if(product.getSalePrice() == null) throw new UnreportedEssentialFieldException("Preço de venda não informado");
        if(product.getSalePrice() < 0) throw new InvalidValueException("Preço de venda inválido");
        product.setSalePrice(product.getSalePrice());

        if(product.getQuantity() == null) throw new UnreportedEssentialFieldException("Quantidade não informada");
        if(product.getQuantity() < 0) throw new InvalidValueException("Quantidade inválida");
        product.setQuantity(product.getQuantity());

        product.setSku(
                 product.getModelCode()
                + product.getColor().getColorNumber()
                + product.getSize().toString());
        
        product = productRepository.save(product);

        return modelMapper.map(product, ProductDTO.class);
    }

    //---------------------------------------------------------------------------------

    public ProductDTO findBydId(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
          return new EntityNotFoundException("Não existe um produto com o id informado");
        });
        return modelMapper.map(product, ProductDTO.class);
    }

    //---------------------------------------------------------------------------------

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(product -> new ProductDTO()).toList();
    }

    //--------------------------------------------------------------------------------

    public ProductDTO findBySku(String sku) {
        Product product = productRepository.findBySku(sku);
        if(product == null) throw new EntityNotFoundException("Não existe um produto com o sku informado");
        return modelMapper.map(product, ProductDTO.class);
    }

    //---------------------------------------------------------------------------------

    public void deleteById(Long id) {
        this.findBydId(id);
        productRepository.deleteById(id);
    }

    //---------------------------------------------------------------------------------

    public ProductDTO update(Long id, Product product) {
        product = productRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um produto com o id informado");
        });

        if(product.getDescription() != null) product.setDescription(product.getDescription());

        if(product.getType() != null) product.setType(product.getType());

        if(product.getCategory() != null) product.setCategory(product.getCategory());

        if(product.getColor() != null) product.setColor(product.getColor());

        if(product.getSex() != null) product.setSex(product.getSex());

        if(product.getSize() != null) product.setSize(product.getSize());

        if(product.getFit() != null) product.setFit(product.getFit());

        if(product.getPurchasePrice() != null) {
            if(product.getPurchasePrice() < 0) throw new InvalidValueException("Preço de compra inválido");
            product.setPurchasePrice(product.getPurchasePrice());
        }

        if(product.getSalePrice() != null) {
            if(product.getSalePrice() < 0) throw new InvalidValueException("Preço de venda inválido");
            product.setSalePrice(product.getSalePrice());
        }

        if(product.getQuantity() != null) {
            if(product.getQuantity() < 0) throw new InvalidValueException("Quantidade inválida");
            product.setQuantity(product.getQuantity());
        }

        product.setSku(
                product.getModelCode()
                + product.getColor().getColorNumber()
                + product.getSize().toString());

        product.setId(id);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    public Integer supplyProductStockById(Long id, Integer quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException("Não existe um produto com o id informado");
        });

        if(quantity < 0) throw new InvalidValueException("Quantidade inválida");

        Integer newQuantity = product.getQuantity() + quantity;
        product.setQuantity(newQuantity);
        productRepository.save(product);
        return newQuantity;
    }

    public Integer supplyProductStockBySku(String sku, Integer quantity) {
        Product product = productRepository.findBySku(sku);
        if(product == null) throw new EntityNotFoundException("Não existe um produto com o sku informado");
        if(quantity < 0) throw new InvalidValueException("Quantidade inválida");

        Integer newQuantity = product.getQuantity() + quantity;
        product.setQuantity(newQuantity);
        productRepository.save(product);
        return newQuantity;
    }
}