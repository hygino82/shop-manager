package br.dev.hygino.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_product")
@NoArgsConstructor
@Data
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 100, min = 3, message = "O nome do produto deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "A marca do produto é obrigatória")
    @Size(max = 100, min = 3, message = "A marcado produto deve ter entre 3 e 100 caracteres")
    private String brand;

    @NotNull(message = "O preço do produto é obrigatório")
    @Positive(message = "O preço do produto deve ser um valor positivo")
    private Double price;

    @NotBlank(message = "O código de barras é obrigatório")
    @Size(min = 8, max = 13, message = "O código de barras deve ter entre 8 e 13 caracteres")
    private String barCode;

    @NotNull(message = "A categoria do produto é obrigatória")
    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
