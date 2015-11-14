package mnidersoft.com.br.netshoes.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        this.view = itemView;
    }

    public V getView() {
        return this.view;
    }
}