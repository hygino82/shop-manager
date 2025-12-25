package br.dev.hygino.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.dev.hygino.models.Category;
import br.dev.hygino.models.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
            SELECT p FROM Product p
            WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
            AND (:brand IS NULL OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :brand, '%')))
            AND (:category IS NULL OR p.category = :category)
            """)
    Page<Product> findProducts(@Param("name") String name,
                               @Param("brand") String brand,
                               @Param("category") Category category,
                               Pageable pageable);

    @Query("SELECT obj FROM Product obj WHERE obj.barCode = :barcode")
    Optional<Product> findProductByBarCode(String barcode);
}
