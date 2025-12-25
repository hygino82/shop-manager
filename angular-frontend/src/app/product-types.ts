export type ResponseProductMinDto = {
  id: number;
  name: string;
  price: number;
}

export type Page = {
  totalElements: number,
  totalPages: number;
  pageNumber: number;
  pageSize: number;
}

export type MinProductPage = {
  content: ResponseProductMinDto[];
  page: Page;
}

export type Category = 'AGUAS' | 'BEBIDAS_ALCOOLICAS' | 'DOCES' | 'REFRIGERANTES' | 'SALGADOS' | 'SUCOS' | 'TABACO';
