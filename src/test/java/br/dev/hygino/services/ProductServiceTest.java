package br.dev.hygino.services;

import br.dev.hygino.factories.ProductFactory;
import br.dev.hygino.models.Category;
import br.dev.hygino.models.Product;
import br.dev.hygino.repositories.ProductRepository;
import br.dev.hygino.services.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private long validId, invalidId;
    private AutoCloseable closeable;
    private Pageable pageable;

    @BeforeEach
    void setup() {
        // Inicializa manualmente os mocks para evitar o NullPointerException no
        // repository
        closeable = MockitoAnnotations.openMocks(this);

        validId = 1L;
        invalidId = 1000L;
        pageable = PageRequest.of(0, 10);

        final List<Product> productList = ProductFactory.createProductList();
        final List<Product> beerList = ProductFactory.createBeerProductList();
        final Product validProduct = ProductFactory.createProductEntity();

        final var beerPage = new PageImpl<>(
                beerList,
                pageable,
                beerList.size()
        );

        final Page<Product> productPage = new PageImpl<>(
                productList,
                pageable,
                productList.size()
        );

        // Configuração dos comportamentos do Mock
        when(productRepository.findById(validId)).thenReturn(Optional.of(validProduct));
        when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

        when(productRepository.findProducts("", "", Category.BEBIDAS_ALCOOLICAS, pageable))
                .thenReturn(beerPage);

        when(productRepository.findProducts("", "", null, pageable))
                .thenReturn(productPage);
    }

    @AfterEach
    void tearDown() throws Exception {
        // Libera os recursos dos mocks
        closeable.close();
    }

    @Test
    @DisplayName("Deve retornar um ResponseProductDetailsDto quando o Id for válido")
    void findProductByIdShouldReturnResponseProductDetailsDtoWhenIdIsValid() {
        // Act (Ação)
        final var result = productService.findProductById(validId);

        // Assert (Verificação)
        assertEquals(validId, result.id());
    }

    @Test
    @DisplayName("Deve lançar ProductNotFoundException quando o Id for inválido")
    void findProductByIdShouldThrowProductNotFoundExceptionWhenIdIsInvalid() {
        final ProductNotFoundException result = assertThrows(ProductNotFoundException.class,
                () -> productService.findProductById(invalidId));
        assertEquals("Product not found", result.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma página contendo apenas bebidas alcoólicas")
    void findBeerProductsShouldReturnPageWith3Elements() {
        final var result = productService.findProducts("", "", Category.BEBIDAS_ALCOOLICAS, pageable);
        assertNotNull(result);
        assertEquals(3, result.getContent().size());
    }

    @Test
    void findProductsShouldReturnPageWith5Elements() {
        final var result = productService.findProducts("", "", null, pageable);
        assertNotNull(result);
        assertEquals(5, result.getContent().size());
    }
}