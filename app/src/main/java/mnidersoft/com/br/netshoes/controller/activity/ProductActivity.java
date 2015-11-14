package mnidersoft.com.br.netshoes.controller.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import mnidersoft.com.br.netshoes.Constant;
import mnidersoft.com.br.netshoes.R;
import mnidersoft.com.br.netshoes.adapter.GalleryPagerAdapter;
import mnidersoft.com.br.netshoes.controller.event.ErrorEvent;
import mnidersoft.com.br.netshoes.controller.event.EventManager;
import mnidersoft.com.br.netshoes.controller.event.request.RequestProductInfoEvent;
import mnidersoft.com.br.netshoes.controller.event.response.ResponseProductInfoEvent;
import mnidersoft.com.br.netshoes.model.productinfo.Gallery;
import mnidersoft.com.br.netshoes.model.productinfo.ProductInfoContent;
import mnidersoft.com.br.netshoes.util.GenericUtil;

@EActivity(R.layout.activity_product)
@OptionsMenu(R.menu.menu_product)
public class ProductActivity extends AppCompatActivity {

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected RelativeLayout mainContent;

    @ViewById
    protected RelativeLayout priceLayout;

    @ViewById
    protected ViewPager viewPager;

    @ViewById
    protected LinearLayout dotsLayout;

    @ViewById
    protected FloatingActionButton fab;

    @ViewById
    protected ProgressBar progressBar;

    @ViewById
    protected TextView nameTextView;

    @ViewById
    protected TextView descriptionTextView;

    @ViewById
    protected TextView originalPriceTextView;

    @ViewById
    protected TextView actualPriceTextView;

    @ViewById
    protected TextView percentTextView;

    @OptionsMenuItem
    protected MenuItem menuShare;

    private DisplayMetrics metrics;
    private Display display;

    private ProductInfoContent productInfoContent;

    private GalleryPagerAdapter mGalleryPagerAdapter;

    private ArrayList<ImageView> dots;

    public ProductActivity() {
        EventManager.getInstance().init();
    }

    @AfterViews
    protected void afterViews() {
        this.metrics = new DisplayMetrics();
        this.display = this.getWindowManager().getDefaultDisplay();

        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle("");

        String productUrl = this.getIntent().getStringExtra(Constant.PRODUCT_URL);

        EventBus.getDefault().post(new RequestProductInfoEvent(this, productUrl));
    }

    @OptionsItem(R.id.menuShare)
    protected void onMenuShare() {
        this.shareProductUrl();
    }

    @OptionsItem(android.R.id.home)
    protected void onHome() {
        this.finish();
    }

    public void addDots() {
        this.dots = new ArrayList<>();
        for(int i = 0; i < this.mGalleryPagerAdapter.getCount(); i++) {
            ImageView dot = new ImageView(this);
            dot.setImageDrawable(getResources().getDrawable(R.drawable.pager_dot_not_selected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(15, 60, 15, 60);
            this.dotsLayout.addView(dot, params);

            this.dots.add(dot);
        }

        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        this.selectDot(0);
    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for(int i = 0; i < this.mGalleryPagerAdapter.getCount(); i++) {
            int drawableId = (i==idx) ? R.drawable.pager_dot_selected : R.drawable.pager_dot_not_selected;
            Drawable drawable = res.getDrawable(drawableId);
            this.dots.get(i).setImageDrawable(drawable);
        }
    }

    public void onEvent(ResponseProductInfoEvent event) {
        this.productInfoContent = event.getProductInfoContent();

        this.initComponents();

        this.showProgressBar(false);
    }

    @UiThread
    protected void initComponents() {
        Gallery imageGallery = null;
        for(Gallery gallery : this.productInfoContent.getValue().getGallery()) {
            if(gallery.getType() == 1) {
                imageGallery = gallery;
            }
        }
        this.mGalleryPagerAdapter = new GalleryPagerAdapter(this.getSupportFragmentManager(), imageGallery);

        this.updateViewPagerLayoutParams();

        this.viewPager.setAdapter(this.mGalleryPagerAdapter);

        this.showViews();

        this.nameTextView.setText(this.productInfoContent.getValue().getName());
        this.descriptionTextView.setText(this.productInfoContent.getValue().getDescription());

        this.percentTextView.setText(this.productInfoContent.getValue().getBadge().getValue());

        String originalPrice = this.productInfoContent.getValue().getPrice().getOriginalPrice();
        String actualPrice = this.productInfoContent.getValue().getPrice().getActualPrice();

        if(!actualPrice.equals(originalPrice)) {
            this.originalPriceTextView.setText(originalPrice);
            this.originalPriceTextView.setPaintFlags(this.originalPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        this.actualPriceTextView.setText(actualPrice);

        this.addDots();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        this.updateViewPagerLayoutParams();
    }

    private void updateViewPagerLayoutParams() {
        this.display.getMetrics(this.metrics);
        Log.e("ABC", "this.metrics.widthPixels = " + this.metrics.widthPixels);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(this.metrics.widthPixels, this.metrics.widthPixels);
        this.viewPager.setLayoutParams(params);
    }

    public void onEvent(ErrorEvent event) {
        this.showProgressBar(false);
        this.showToast(event.getMessage());
    }

    protected void showViews() {
        this.priceLayout.setVisibility(View.VISIBLE);
        this.mainContent.setVisibility(View.VISIBLE);
        this.fab.setVisibility(View.VISIBLE);
    }

    @UiThread
    public void showProgressBar(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;
        this.progressBar.setVisibility(visibility);
    }

    @UiThread
    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void shareProductUrl() {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");

        String productFullUrl = Constant.ROOT_URL + this.getIntent().getStringExtra(Constant.PRODUCT_URL);

        intent.putExtra(Intent.EXTRA_TEXT, productFullUrl);

        this.startActivity(Intent.createChooser(intent, this.getString(R.string.shareOffer)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        GenericUtil.registerOnEventBus(this);
    }

    @Override
    protected void onPause() {
        GenericUtil.unregisterOnEventBus(this);

        super.onPause();
    }
}