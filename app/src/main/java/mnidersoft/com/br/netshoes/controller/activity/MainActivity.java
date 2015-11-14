package mnidersoft.com.br.netshoes.controller.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;
import mnidersoft.com.br.netshoes.Constant;
import mnidersoft.com.br.netshoes.R;
import mnidersoft.com.br.netshoes.controller.event.ErrorEvent;
import mnidersoft.com.br.netshoes.controller.event.EventManager;
import mnidersoft.com.br.netshoes.controller.event.request.RequestProductsListEvent;
import mnidersoft.com.br.netshoes.controller.event.response.ResponseProductsListEvent;
import mnidersoft.com.br.netshoes.controller.fragment.MainFragment_;
import mnidersoft.com.br.netshoes.util.GenericUtil;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;

    protected MainFragment_ mainFragment;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected ProgressBar progressBar;

    @ViewById
    protected TextView foundTextView;

    @OptionsMenuItem
    protected MenuItem menuExit;

    @AfterInject
    protected void afterInject() {
        EventManager.getInstance().init();
    }

    @AfterViews
    protected void afterViews() {
        this.setSupportActionBar(this.toolbar);

        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);

        this.fragmentManager = this.getSupportFragmentManager();

        if (this.fragmentManager.findFragmentById(R.id.fragmentLayout) == null) {
            this.mainFragment = new MainFragment_();

            this.fragmentManager.beginTransaction()
                    .add(R.id.fragmentLayout, this.mainFragment)
                    .commit();
        }

        EventBus.getDefault().post(new RequestProductsListEvent(this, Constant.START_PARAMETERS));
    }

    @OptionsItem(R.id.menuExit)
    protected void onMenuExit() {
        this.finish();
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

    public void onEvent(RequestProductsListEvent event) {
        this.showProgressBar(true);
    }

    public void onEvent(ResponseProductsListEvent event) {
        this.showProgressBar(false);
        this.showFoundText();
    }

    public void onEvent(ErrorEvent event) {
        this.showProgressBar(false);
        this.showToast(event.getMessage());
    }

    @UiThread
    public void showProgressBar(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;
        this.progressBar.setVisibility(visibility);
    }

    @UiThread
    protected void showFoundText() {
        this.foundTextView.setVisibility(View.VISIBLE);
    }

    @UiThread
    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        EventManager.getInstance().destroy();
        super.onDestroy();
    }
}