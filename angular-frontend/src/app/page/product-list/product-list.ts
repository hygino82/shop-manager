import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product-service';
import {
  Category,
  MinProductPage,
} from '../../product-types';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductList implements OnInit {

  constructor(private readonly productService: ProductService, private cdr: ChangeDetectorRef) {}

  // filtros
  name: string = '';
  brand: string = '';
  category: Category | null = null;

  // estado inicializado (IMPORTANTE)
  productPage: MinProductPage = {
    content: [],
    totalElements: 0,
    totalPages: 0,
    pageNumber: 0,
    pageSize: 0,
    first: true,
    last: true,
  };

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.productService
      .getProducts(this.name, this.brand, this.category)
      .subscribe({
        next: (response: MinProductPage) => {
          console.log('RESPONSE BRUTO:', response);
          console.log('CONTENT:', response.content);
          this.productPage = response;
          console.log('content é array?', Array.isArray(this.productPage.content));
          console.log('length:', this.productPage.content.length);
          console.log('content:', this.productPage.content);
          this.cdr.detectChanges();

        },
        error: (error) => {
          console.error('Erro ao buscar produtos', error);
        },
      });
  }
}
