package mnidersoft.com.br.netshoes.controller.event;

import android.content.Context;

import de.greenrobot.event.EventBus;
import mnidersoft.com.br.netshoes.R;
import mnidersoft.com.br.netshoes.controller.component.CustomComponent;
import mnidersoft.com.br.netshoes.controller.component.DaggerCustomComponent;
import mnidersoft.com.br.netshoes.controller.event.request.RequestProductInfoEvent;
import mnidersoft.com.br.netshoes.controller.event.request.RequestProductsListEvent;
import mnidersoft.com.br.netshoes.controller.event.response.ResponseProductInfoEvent;
import mnidersoft.com.br.netshoes.controller.event.response.ResponseProductsListEvent;
import mnidersoft.com.br.netshoes.controller.module.CustomModule;
import mnidersoft.com.br.netshoes.model.productinfo.ProductInfoContent;
import mnidersoft.com.br.netshoes.model.productsList.ProductsListContent;
import mnidersoft.com.br.netshoes.service.RestClient;
import mnidersoft.com.br.netshoes.util.GenericUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EventManager {

    private static EventManager instance;

    private RestClient restClient;

    public Context context;

    private EventManager() {}

    public static EventManager getInstance() {
        if(instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void init() {
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        if(this.restClient == null) {
            CustomComponent flickrComponent = DaggerCustomComponent.builder()
                    .customModule(new CustomModule())
                    .build();

            this.restClient = flickrComponent.provideRestClient();
        }
    }

    public void onEvent(final RequestProductsListEvent event) {
        this.context = event.getContext();

        if(!GenericUtil.isOnline(this.context)) {
            EventBus.getDefault().post(new ErrorEvent(this.context.getString(R.string.offline)));
            return;
        }

        this.restClient.getProducts(event.getParameters(), new Callback<ProductsListContent>() {
            @Override
            public void success(ProductsListContent jSONContent, Response response) {
                EventBus.getDefault().post(new ResponseProductsListEvent(jSONContent));
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                EventBus.getDefault().post(new ErrorEvent(error.getMessage()));
            }
        });
    }

    public void onEvent(final RequestProductInfoEvent event) {
        this.context = event.getContext();

        if(!GenericUtil.isOnline(this.context)) {
            EventBus.getDefault().post(new ErrorEvent(this.context.getString(R.string.offline)));
            return;
        }

        this.restClient.getProductInfo(event.getPath(), new Callback<ProductInfoContent>() {
            @Override
            public void success(ProductInfoContent productInfoContent, Response response) {
                EventBus.getDefault().post(new ResponseProductInfoEvent(productInfoContent));
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                EventBus.getDefault().post(new ErrorEvent(error.getMessage()));
            }
        });
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}