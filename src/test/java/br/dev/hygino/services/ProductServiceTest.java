package br.dev.hygino.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.dev.hygino.factories.ProductFactory;
import br.dev.hygino.models.Product;
import br.dev.hygino.repositories.ProductRepository;
import br.dev.hygino.services.exceptions.ProductNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	private long validId, invalidId;
	private Product validProduct;
	private AutoCloseable closeable;

	@BeforeEach
	public void setup() {
		// Inicializa manualmente os mocks para evitar o NullPointerException no
		// repository
		closeable = MockitoAnnotations.openMocks(this);

		validId = 1L;
		invalidId = 1000L;
		validProduct = ProductFactory.createProductEntity();

		// Configuração dos comportamentos do Mock
		when(productRepository.findById(validId)).thenReturn(Optional.of(validProduct));
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());
	}

	@AfterEach
	public void tearDown() throws Exception {
		// Libera os recursos dos mocks
		closeable.close();
	}

	@Test
	@DisplayName("Deve retornar um ResponseProductDetailsDto quando o Id for válido")
	public void findProductByIdShouldReturnResponseProductDetailsDtoWhenIdIsValid() {
		// Act (Ação)
		final var result = productService.findProductById(validId);

		// Assert (Verificação)
		assertEquals(validId, result.id());
	}

	@Test
	@DisplayName("Deve lançar ProductNotFoundException quando o Id for inválido")
	public void findProductByIdShouldThrowProductNotFoundExceptionWhenIdIsInvalid() {
		final ProductNotFoundException result = assertThrows(ProductNotFoundException.class,
				() -> productService.findProductById(invalidId));
		assertEquals("Product not found", result.getMessage());
	}
}