export type MinProductDto = {
  productId: number;
  name: string;
  price: number;
  category?: Category;
}

export type Page = {
  totalElements: number,
  totalPages: number;
  pageNumber: number;
  pageSize: number;
}

export interface MinProductPage {
  content: MinProductDto[];
  totalElements: number;
  totalPages: number;
  pageNumber: number;
  pageSize: number;
  first: boolean;
  last: boolean;
}


export type Category = 'AGUAS' | 'BEBIDAS_ALCOOLICAS' | 'DOCES' | 'REFRIGERANTES' | 'SALGADOS' | 'SUCOS' | 'TABACO';
