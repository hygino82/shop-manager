import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Category, MinProductPage} from '../product-types';
import {BASE_URL} from '../utils';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {
  }

  getProducts(
    name: string,
    brand: string,
    category: Category | null
  ): Observable<MinProductPage> {

    let params = new HttpParams();
    if (name) params = params.set('name', name);
    if (brand) params = params.set('brand', brand);
    if (category) params = params.set('category', category);

    return this.http
      .get<any>(`${BASE_URL}/product`, {params})
      .pipe(
        map(page => ({
          content: page.content,
          totalElements: page.totalElements,
          totalPages: page.totalPages,
          pageNumber: page.number,   // ✅ mapeamento correto
          pageSize: page.size,       // ✅ mapeamento correto
          first: page.first,
          last: page.last
        }))
      );
  }
}
