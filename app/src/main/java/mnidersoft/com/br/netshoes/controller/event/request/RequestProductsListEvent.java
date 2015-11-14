package mnidersoft.com.br.netshoes.controller.event.request;

import android.content.Context;

public class RequestProductsListEvent {

    private final Context context;
    private final String parameters;

    public RequestProductsListEvent(Context context, String parameters) {
        this.context = context;
        this.parameters = parameters;
    }

    public Context getContext() {
        return this.context;
    }

    public String getParameters() {
        return this.parameters;
    }
}