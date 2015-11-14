package mnidersoft.com.br.netshoes.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import mnidersoft.com.br.netshoes.model.productsList.Product;
import mnidersoft.com.br.netshoes.view.ProductItemView;
import mnidersoft.com.br.netshoes.view.ProductItemView_;
import mnidersoft.com.br.netshoes.view.ViewWrapper;

public class ProductAdapter extends RecyclerViewAdapterBase<Product, ProductItemView> {

    public Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected ProductItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ProductItemView_.build(this.context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ProductItemView> viewHolder, int position) {
        ProductItemView view = viewHolder.getView();
        Product product = this.items.get(position);

        view.bind(product);
    }

    public void addAll(List<Product> productList) {
        for(Product product : productList) {
//            if(!this.idList.contains(product.getId())) {
                this.items.add(product);
//                this.idList.add(product.getId());
//            }
        }
    }
}