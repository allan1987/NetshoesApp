package mnidersoft.com.br.netshoes.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import mnidersoft.com.br.netshoes.Constant;
import mnidersoft.com.br.netshoes.R;
import mnidersoft.com.br.netshoes.controller.activity.ProductActivity_;
import mnidersoft.com.br.netshoes.model.productsList.Product;

@EViewGroup(R.layout.list_item)
public class ProductItemView extends CardView implements View.OnClickListener {

    @ViewById
    public ImageView contentImageView;

    @ViewById
    public TextView badgeTextView;

    @ViewById
    public TextView nameTextView;

    @ViewById
    public TextView originalPriceTextView;

    @ViewById
    public TextView actualPriceTextView;

    private Product product;

    public ProductItemView(Context context) {
        super(context);
        this.setOnClickListener(this);
    }

    public void bind(final Product product) {
        this.product = product;

        DrawableTypeRequest<String> dRequest = Glide.with(this.getContext()).load(Constant.HTTPS_MODE + product.getImage().getThumb());

        dRequest.placeholder(R.mipmap.ic_placeholder)
                .override(410, 410)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .animate(android.R.anim.fade_in)
                .into(this.contentImageView);

        this.contentImageView.setOnClickListener(this);

        this.badgeTextView.setText(this.product.getBadge().getValue());

        this.nameTextView.setText(this.product.getName());

        String originalPrice = this.product.getPrice().getOriginalPrice();
        String actualPrice = this.product.getPrice().getActualPrice();

        if(!actualPrice.equals(originalPrice)) {
            this.originalPriceTextView.setText(originalPrice);
            this.originalPriceTextView.setPaintFlags(this.originalPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        this.actualPriceTextView.setText(this.product.getPrice().getActualPrice());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this.getContext(), ProductActivity_.class);
        intent.putExtra(Constant.PRODUCT_URL, this.product.getUrl().substring(1));
        this.getContext().startActivity(intent);
    }
}