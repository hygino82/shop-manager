package br.dev.hygino.factories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.dev.hygino.dto.RequestProductDto;
import br.dev.hygino.models.Category;
import br.dev.hygino.models.Product;

public class ProductFactory {
	private ProductFactory() {
	}

	public static RequestProductDto createRequestProductDto() {
		return new RequestProductDto("Tabajariol", "Tabajara", 8.75, "789452101234", Category.BEBIDAS_ALCOOLICAS);
	}
	
	public static Product createProductEntity() {
		return new Product(
				1L, 
				"Brahma lata 350ml", 
				"Ambev", 
				5.0, 
				"123456789", 
				Category.BEBIDAS_ALCOOLICAS,
				LocalDateTime.parse("2025-12-23T10:00:00"), 
				null);
	}

	public static List<Product> createProductList() {
		
		final var p1 = new Product(
				1L, 
				"Brahma lata 350ml", 
				"Ambev", 
				5.0, 
				"123456789", 
				Category.BEBIDAS_ALCOOLICAS,
				LocalDateTime.parse("2025-12-23T10:00:00"), 
				null);

		final var p2 = new Product(
				2L, 
				"Skol lata 350ml", 
				"Ambev", 
				4.5, 
				"987654321", 
				Category.BEBIDAS_ALCOOLICAS,
				LocalDateTime.parse("2025-12-23T10:00:00"), 
				null);

		final var p3 = new Product(
				3L, 
				"Coca-Cola 2L", 
				"Coca-Cola", 
				8.0, 
				"111111111", 
				Category.REFRIGERANTES,
				LocalDateTime.parse("2025-12-23T11:00:00"), 
				null);

		final var p4 = new Product(
				4L, 
				"Fanta Laranja 1.5L", 
				"Coca-Cola", 
				6.5, 
				"222222222", 
				Category.REFRIGERANTES,
				LocalDateTime.parse("2025-12-23T12:00:00"), 
				null);

		final var p5 = new Product(
				5L, 
				"Heineken lata 350ml", 
				"Heineken", 
				7.0, 
				"333333333", 
				Category.BEBIDAS_ALCOOLICAS,
				LocalDateTime.parse("2025-12-23T13:00:00"), 
				null);

		return new ArrayList<>(List.of(p1, p2, p3, p4, p5));
	}
}
