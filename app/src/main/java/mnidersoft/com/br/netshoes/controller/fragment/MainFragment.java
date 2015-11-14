package mnidersoft.com.br.netshoes.controller.fragment;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.greenrobot.event.EventBus;
import mnidersoft.com.br.netshoes.Constant;
import mnidersoft.com.br.netshoes.R;
import mnidersoft.com.br.netshoes.adapter.ProductAdapter;
import mnidersoft.com.br.netshoes.controller.event.ErrorEvent;
import mnidersoft.com.br.netshoes.controller.event.request.RequestProductsListEvent;
import mnidersoft.com.br.netshoes.controller.event.response.ResponseProductsListEvent;
import mnidersoft.com.br.netshoes.model.productsList.Product;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    private String nextURL;
    private boolean loading;

    @ViewById
    public RecyclerView photosRecyclerView;

    private ProductAdapter adapter;

    private StaggeredGridLayoutManager layoutManager;

    public MainFragment() {
        this.loading = true;
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.layoutManager.setSpanCount(Constant.LANDSCAPE_MAX_PRODUCTS_PER_LINE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.layoutManager.setSpanCount(Constant.PORTRAIT_MAX_PRODUCTS_PER_LINE);
        }
    }

    @AfterViews
    public void afterViews() {
        int maxProductsPerLine = Constant.LANDSCAPE_MAX_PRODUCTS_PER_LINE;
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            maxProductsPerLine = Constant.PORTRAIT_MAX_PRODUCTS_PER_LINE;
        }

        this.layoutManager = new StaggeredGridLayoutManager(maxProductsPerLine, StaggeredGridLayoutManager.VERTICAL);
        this.layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        this.photosRecyclerView.setLayoutManager(layoutManager);
        this.photosRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.adapter = new ProductAdapter(this.getActivity());
        this.photosRecyclerView.setAdapter(this.adapter);

        this.addOnScrollListener();
    }

    private void addOnScrollListener() {
        this.photosRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int visibleItemCount, totalItemCount;
            private int[] pastVisiblesItems;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                this.visibleItemCount = photosRecyclerView.getLayoutManager().getChildCount();
                this.totalItemCount = photosRecyclerView.getLayoutManager().getItemCount();
                this.pastVisiblesItems = ((StaggeredGridLayoutManager) photosRecyclerView.getLayoutManager()).findFirstVisibleItemPositions(null);

                if (loading) {
                    if ((this.visibleItemCount + this.pastVisiblesItems[0]) >= this.totalItemCount) {
                        loading = false;
                        EventBus.getDefault().post(new RequestProductsListEvent(getActivity(), nextURL));
                    }
                }
            }
        });
    }

    public void onEvent(final ErrorEvent event) {
        this.loading = true;
    }

    public void onEvent(final ResponseProductsListEvent event) {
        this.nextURL = event.getProductsListContent().getValue().getUrl();
        this.fillAdapter(event.getProductsListContent().getValue().getProducts());
        this.loading = true;
    }

    @UiThread
    protected void fillAdapter(List<Product> productList) {
        this.adapter.addAll(productList);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}