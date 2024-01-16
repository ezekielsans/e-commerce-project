package com.crudwithpostgresql.ECommerce.repo;

import com.crudwithpostgresql.ECommerce.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface EcommRepo extends JpaRepository<Products, Long> {
}
