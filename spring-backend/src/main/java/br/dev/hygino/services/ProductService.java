package br.dev.hygino.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestProductDto;
import br.dev.hygino.dto.RequestProdutPriceUpdateDto;
import br.dev.hygino.dto.ResponseProductDetailsDto;
import br.dev.hygino.dto.ResponseProductMinDto;
import br.dev.hygino.models.Category;
import br.dev.hygino.models.Product;
import br.dev.hygino.repositories.ProductRepository;
import br.dev.hygino.services.exceptions.ProductNotFoundException;
import br.dev.hygino.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ResponseProductMinDto> findProducts(String name, String brand, Category category, Pageable pageable) {
        final Page<Product> products = productRepository.findProducts(name, brand, category, pageable);

        return products.map(ResponseProductMinDto::new);
    }

    @Transactional(readOnly = true)
    public ResponseProductDetailsDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return new ResponseProductDetailsDto(product);
    }

    @Transactional
    public ResponseProductDetailsDto saveProduct(RequestProductDto dto) {
        Product product = new Product();
        dtoToEntity(dto, product);
        product = productRepository.save(product);

        return new ResponseProductDetailsDto(product);
    }

    @Transactional
    public ResponseProductDetailsDto updateProduct(Long id, RequestProductDto dto) {
        try {
            Product product = productRepository.getReferenceById(id);

            dtoToEntity(dto, product);
            product.setUpdatedAt(LocalDateTime.now());
            product = productRepository.save(product);

            return new ResponseProductDetailsDto(product);
        } catch (EntityNotFoundException | ObjectRetrievalFailureException e) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    @Transactional
    public ResponseProductDetailsDto updateProductPrice(RequestProdutPriceUpdateDto dto) {
        try {
            Product product = productRepository.getReferenceById(dto.productId());

            product.setPrice(dto.price());
            product.setUpdatedAt(LocalDateTime.now());
            product = productRepository.save(product);

            return new ResponseProductDetailsDto(product);
        } catch (EntityNotFoundException | ObjectRetrievalFailureException e) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    private void dtoToEntity(RequestProductDto dto, Product product) {
        product.setName(dto.name());
        product.setBrand(dto.brand());
        product.setBarCode(dto.barCode());
        product.setPrice(dto.price());
        product.setCategory(dto.category());
    }

    @Transactional(readOnly = true)
    public ResponseProductDetailsDto findProductByBarCode(String barcode) {
        Product product = productRepository.findProductByBarCode(barcode)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return new ResponseProductDetailsDto(product);
    }
}