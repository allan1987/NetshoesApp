package mnidersoft.com.br.netshoes.controller.fragment;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import mnidersoft.com.br.netshoes.Constant;
import mnidersoft.com.br.netshoes.R;

@EFragment(R.layout.fragment_product)
public class ProductFragment extends Fragment {

    @FragmentArg
    protected String largeImageUrl = null;

    @FragmentArg
    protected String zoomImageUrl = null;

    @ViewById
    protected ImageView imageView;

    public ProductFragment() {}

    @AfterViews
    public void afterViews() {
        DrawableTypeRequest<String> dRequest = Glide.with(this.getContext()).load(Constant.HTTPS_MODE + this.largeImageUrl);

        dRequest.placeholder(R.mipmap.ic_placeholder)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .dontAnimate()
                .into(this.imageView);
    }
}