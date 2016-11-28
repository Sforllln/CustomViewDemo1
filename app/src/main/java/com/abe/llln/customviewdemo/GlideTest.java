package com.abe.llln.customviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        ImageView image = (ImageView) findViewById(R.id.glide_gif);
        Glide.with(this).load(R.drawable.giftest).asGif().diskCacheStrategy(DiskCacheStrategy.NONE).into(image);
    }

}
