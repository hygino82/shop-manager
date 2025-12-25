import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../services/product-service';
import {Category, MinProductPage} from '../../product-types';

@Component({
  selector: 'app-product-list',
  imports: [],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductList implements OnInit {
  constructor(private readonly productService: ProductService) {
  }

  ngOnInit(): void {
    this.getProducts();
  }

  name: string = '';
  brand: string = '';
  category: Category | null = null;

  productPage?: MinProductPage;

  getProducts(): void {
    this.productService.getProducts(this.name, this.brand, this.category).subscribe({
      next: (response: MinProductPage) => {
        this.productPage = response;
        console.log(this.productPage?.content);
      },
      error: (error: any) => {
        console.error(error);
      }
    });
  }
}
