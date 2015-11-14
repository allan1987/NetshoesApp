package mnidersoft.com.br.netshoes;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Build;
import android.service.chooser.ChooserTarget;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import junit.framework.Assert;

import mnidersoft.com.br.netshoes.controller.activity.MainActivity_;
import mnidersoft.com.br.netshoes.controller.activity.ProductActivity_;

public class ProductActivityTest extends ActivityInstrumentationTestCase2<ProductActivity_> {

    private final String productUrl = "produto/tenis-olympikus-spin-026-0364-793";
    private Solo solo;

    public ProductActivityTest() {
        super(ProductActivity_.class);
    }

    public void setUp() throws Exception {
        Intent i = new Intent();
        i.putExtra(Constant.PRODUCT_URL, this.productUrl);
        this.setActivityIntent(i);

        this.solo = new Solo(this.getInstrumentation(), this.getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        this.solo.finishOpenedActivities();
    }

    public void test1ProductActivity() throws Exception {
        this.solo.assertCurrentActivity("wrong activity", ProductActivity_.class);
    }

    public void test2Navigate() throws Exception {
        Condition condition = new Condition() {
            @Override
            public boolean isSatisfied() {
                View progressBar = solo.getView(R.id.progressBar);
                return progressBar.getVisibility() == View.GONE;
            }
        };

        this.solo.waitForCondition(condition, 20000);

        this.solo.scrollViewToSide(this.solo.getView(R.id.viewPager), Solo.RIGHT);

        this.solo.scrollDown();

        View fab = this.solo.getView(R.id.fab);
//        this.solo.clickOnView(fab);

        Assert.assertNotNull(fab);
    }

    public void test3ShareBtn() throws Exception {
        View menuShare = this.solo.getView(R.id.menuShare);
        this.solo.clickOnView(menuShare);
        this.solo.goBack();

        Assert.assertTrue(menuShare != null);
    }
}