package mnidersoft.com.br.netshoes.controller.event.response;

import mnidersoft.com.br.netshoes.model.productinfo.ProductInfoContent;

public class ResponseProductInfoEvent {

    private final ProductInfoContent productInfoContent;

    public ResponseProductInfoEvent(ProductInfoContent productInfoContent) {
        this.productInfoContent = productInfoContent;
    }

    public ProductInfoContent getProductInfoContent() {
        return this.productInfoContent;
    }
}
