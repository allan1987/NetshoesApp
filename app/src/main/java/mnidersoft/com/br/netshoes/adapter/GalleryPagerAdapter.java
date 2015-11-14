package mnidersoft.com.br.netshoes.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import mnidersoft.com.br.netshoes.controller.fragment.ProductFragment_;
import mnidersoft.com.br.netshoes.model.productinfo.Gallery;
import mnidersoft.com.br.netshoes.model.productinfo.Item;

public class GalleryPagerAdapter extends FragmentPagerAdapter {

    private Gallery gallery;

    public GalleryPagerAdapter(FragmentManager fm, Gallery gallery) {
        super(fm);
        this.gallery = gallery;
    }

    @Override
    public Fragment getItem(int position) {
        Item item = this.gallery.getItems().get(position);
        return ProductFragment_.builder()
                .largeImageUrl(item.getLarge())
                .zoomImageUrl(item.getZoom())
                .build();
    }

    @Override
    public int getCount() {
        int count = (this.gallery == null) ? 0 : this.gallery.getItems().size();
        return count;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}