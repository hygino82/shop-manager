package br.dev.hygino.dto;

import br.dev.hygino.models.Product;

public record ResponseProductMinDto(
        Long productId,
        String name,
        Double price) {

    public ResponseProductMinDto(Product product) {
        this(
                product.getProductId(),
                product.getName(),
                product.getPrice()
            );
    }
}