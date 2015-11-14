package mnidersoft.com.br.netshoes;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import de.greenrobot.event.EventBus;
import mnidersoft.com.br.netshoes.controller.activity.MainActivity_;
import mnidersoft.com.br.netshoes.controller.activity.ProductActivity_;
import mnidersoft.com.br.netshoes.controller.fragment.MainFragment_;
import mnidersoft.com.br.netshoes.controller.fragment.ProductFragment_;

public class ProductActivityTest extends ActivityInstrumentationTestCase2<ProductActivity_> {

    public ProductActivityTest() {
        super (ProductActivity_.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityInitialTouchMode(false);
    }

    @Test
    public void testSimpleConditions() {
        ProductActivity_ activity = this.getActivity();

        assertTrue(EventBus.getDefault().isRegistered(activity));

        ViewPager viewPager = (ViewPager)activity.findViewById(R.id.viewPager);

        assertNotNull(viewPager);

        PagerAdapter pagerAdapter = viewPager.getAdapter();

        assertNotNull(pagerAdapter);

        assertTrue(pagerAdapter.getCount() > 0);

        FloatingActionButton fab = (FloatingActionButton) activity.findViewById(R.id.fab);

        assertNotNull(fab);
    }
}