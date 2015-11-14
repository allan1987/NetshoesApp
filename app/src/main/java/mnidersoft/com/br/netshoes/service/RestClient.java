package mnidersoft.com.br.netshoes.service;

import mnidersoft.com.br.netshoes.Constant;
import mnidersoft.com.br.netshoes.model.productinfo.ProductInfoContent;
import mnidersoft.com.br.netshoes.model.productsList.ProductsListContent;
import retrofit.Callback;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

public interface RestClient {

    @Headers({Constant.USER_AGENT_HEADER, Constant.X_REQUESTED_WITH_HEADER})
    @POST(Constant.TENIS_PATH + Constant.PARAMETERS)
    public void getProducts(@Path(value = "parameters", encode = false) String parameters, Callback<ProductsListContent> callbackHandler);

    @Headers({Constant.USER_AGENT_HEADER, Constant.X_REQUESTED_WITH_HEADER})
    @POST(Constant.PATH)
    public void getProductInfo(@Path(value = "path", encode = false) String path, Callback<ProductInfoContent> callbackHandler);
}