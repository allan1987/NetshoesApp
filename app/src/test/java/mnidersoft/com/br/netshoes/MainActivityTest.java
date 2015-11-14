package mnidersoft.com.br.netshoes;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import de.greenrobot.event.EventBus;
import mnidersoft.com.br.netshoes.controller.activity.MainActivity_;
import mnidersoft.com.br.netshoes.controller.fragment.MainFragment_;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity_> {

    public MainActivityTest() {
        super (MainActivity_.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityInitialTouchMode(false);
    }

    @Test
    public void testSimpleConditions() {
        MainActivity_ activity = this.getActivity();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        assertTrue(EventBus.getDefault().isRegistered(activity));

        assertNotNull(fragmentManager.getFragments());
        assertTrue(fragmentManager.getFragments().size() == 1);
        assertTrue(fragmentManager.getFragments().get(0) instanceof MainFragment_);

        MainFragment_ fragment = (MainFragment_) fragmentManager.getFragments().get(0);
        assertNotNull(fragment);

        RecyclerView photosRecyclerView = (RecyclerView)fragment.findViewById(R.id.photosRecyclerView);
        assertNotNull(photosRecyclerView);

        assertTrue(photosRecyclerView.getChildCount() > 0);
    }
}