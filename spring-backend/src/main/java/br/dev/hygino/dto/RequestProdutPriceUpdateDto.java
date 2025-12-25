package br.dev.hygino.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RequestProdutPriceUpdateDto(
     @NotNull   Long productId,
     @Positive   Double price) {

}
