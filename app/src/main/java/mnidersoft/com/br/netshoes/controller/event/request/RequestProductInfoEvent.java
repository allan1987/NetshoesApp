package mnidersoft.com.br.netshoes.controller.event.request;

import android.content.Context;

public class RequestProductInfoEvent {

    private final Context context;
    private final String path;

    public RequestProductInfoEvent(Context context, String path) {
        this.context = context;
        this.path = path;
    }

    public Context getContext() {
        return this.context;
    }

    public String getPath() {
        return this.path;
    }
}