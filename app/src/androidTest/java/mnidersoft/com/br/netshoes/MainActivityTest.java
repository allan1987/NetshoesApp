package mnidersoft.com.br.netshoes;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import junit.framework.Assert;

import mnidersoft.com.br.netshoes.controller.activity.MainActivity_;
import mnidersoft.com.br.netshoes.controller.activity.ProductActivity_;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity_> {

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity_.class);
    }

    public void setUp() throws Exception {
        this.solo = new Solo(this.getInstrumentation(), this.getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        this.solo.finishOpenedActivities();
    }

    public void test1WrongActivity() throws Exception {
        this.solo.assertCurrentActivity("wrong activity", MainActivity_.class);
    }

    public void test2GoToProductActivity() throws Exception {

        Condition condition = new Condition() {
            @Override
            public boolean isSatisfied() {
                View progressBar = solo.getView(R.id.progressBar);
                return progressBar.getVisibility() == View.GONE;
            }
        };

        this.solo.waitForCondition(condition, 20000);

        this.solo.scrollDown();

        this.solo.clickInRecyclerView(1);

        Assert.assertTrue(this.solo.waitForActivity(ProductActivity_.class, 10000));
    }

    public void test3ExitByMenu() throws Exception {
        this.solo.sendKey(Solo.MENU);

        Assert.assertTrue(this.solo.waitForText(this.solo.getString(R.string.action_exit)));
    }
}