package com.saunik.gitsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saunik.gitsample.pojo.GitItems;

/**
 * @author Saunik Singh on 27/8/18.
 * Cars24 Services Private Limited.
 */
public class GitDescActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        ImageView imageView = findViewById(R.id.image_id);
        TextView titleText = findViewById(R.id.title);
        TextView desc = findViewById(R.id.desc);
        TextView url = findViewById(R.id.url);
        TextView clone = findViewById(R.id.clone_url);
        GitItems gitItems = getIntent().getParcelableExtra("desc");
        if (gitItems != null) {
            if (gitItems.owner != null) {
                Glide.with(this).setDefaultRequestOptions(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.mipmap.ic_launcher))
                        .load(gitItems.owner.avatar_url).into(imageView);
                url.setText(gitItems.owner.url);
            }
            titleText.setText(gitItems.name);
            desc.setText(gitItems.description);
            clone.setText(gitItems.clone_url);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
