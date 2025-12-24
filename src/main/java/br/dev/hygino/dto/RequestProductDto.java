package br.dev.hygino.dto;

import br.dev.hygino.models.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RequestProductDto(
                @NotBlank(message = "O nome do produto é obrigatório") @Size(max = 100, min = 3, message = "O nome do produto deve ter entre 3 e 100 caracteres") String name,
                
                @NotBlank(message = "A marca do produto é obrigatória") @Size(max = 100, min = 3, message = "A marca do produto deve ter entre 3 e 100 caracteres") String brand,

                @NotNull(message = "O preço do produto é obrigatório") @Positive(message = "O preço do produto deve ser um valor positivo") Double price,

                @NotBlank(message = "O código de barras é obrigatório") @Size(min = 8, max = 13, message = "O código de barras deve ter entre 8 e 13 caracteres") String barCode,

                @NotNull(message = "A categoria do produto é obrigatória") Category category) {

}
