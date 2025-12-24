package br.dev.hygino.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.dev.hygino.models.Category;
import br.dev.hygino.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
            SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
            AND (:brand IS NULL OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :brand, '%')))
            AND (:category IS NULL OR p.category = :category)
            """)
    Page<Product> findProducts(String name, String brand, Category category, Pageable pageable);
}
