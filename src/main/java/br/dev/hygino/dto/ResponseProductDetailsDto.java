package br.dev.hygino.dto;

import java.time.LocalDateTime;

import br.dev.hygino.models.Product;

public record ResponseProductDetailsDto(
        Long id,
        String name,
        Double price,
        String barCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
            
    public ResponseProductDetailsDto(Product product) {
        this(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getBarCode(),
                product.getCreatedAt(),
                product.getUpdatedAt());
    }
}
