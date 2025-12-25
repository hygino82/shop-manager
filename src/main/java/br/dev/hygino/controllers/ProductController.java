package br.dev.hygino.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.RequestProductDto;
import br.dev.hygino.dto.RequestProdutPriceUpdateDto;
import br.dev.hygino.dto.ResponseProductDetailsDto;
import br.dev.hygino.dto.ResponseProductMinDto;
import br.dev.hygino.models.Category;
import br.dev.hygino.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ResponseProductMinDto>> findProducts(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String brand,
            @RequestParam(required = false, defaultValue = "") Category category,
            Pageable pageable) {

        Page<ResponseProductMinDto> page = productService.findProducts(name, brand, category, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDetailsDto> findProductById(@PathVariable Long id) {
        ResponseProductDetailsDto dto = productService.findProductById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ResponseProductDetailsDto> saveProduct(@RequestBody RequestProductDto dto) {
        ResponseProductDetailsDto savedDto = productService.saveProduct(dto);
        return ResponseEntity.status(201).body(savedDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDetailsDto> updateProduct(@PathVariable Long id,
            @RequestBody RequestProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @PatchMapping()
    public ResponseEntity<ResponseProductDetailsDto> updateProductPrice(
            @RequestBody @Valid RequestProdutPriceUpdateDto request) {
        return ResponseEntity.ok(productService.updateProductPrice(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
