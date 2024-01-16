package com.crudwithpostgresql.ECommerce.service;


import com.crudwithpostgresql.ECommerce.model.Products;
import com.crudwithpostgresql.ECommerce.repo.EcommRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//importing repo


@Service
public class EcommService {

    @Autowired
    EcommRepo ecommRepo;


    public List<Products> getAllProducts() {

        List<Products> productsList = new ArrayList<>();
        for (Products products : ecommRepo.findAll()) {
            productsList.add(products);
        }
        return productsList;
    }

    public Products getProductById(Long id) {
        return ecommRepo.findById(id).get();
    }


    public boolean saveOrUpdateProduct(Products products) {
        Products updatedProducts = ecommRepo.save(products);
        if (ecommRepo.findById(updatedProducts.getId()).get() != null) {
            return true;
        }
        return false;
    }

    public boolean deleteProduct(Long id) {
        ecommRepo.deleteById(id);
        if (ecommRepo.findById(id).get() != null) {
            return true;
        }
        return false;
    }


}
