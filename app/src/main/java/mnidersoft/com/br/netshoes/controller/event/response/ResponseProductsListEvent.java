package mnidersoft.com.br.netshoes.controller.event.response;

import mnidersoft.com.br.netshoes.model.productsList.ProductsListContent;

public class ResponseProductsListEvent {

    private final ProductsListContent productsListContent;

    public ResponseProductsListEvent(ProductsListContent productsListContent) {
        this.productsListContent = productsListContent;
    }

    public ProductsListContent getProductsListContent() {
        return this.productsListContent;
    }
}
