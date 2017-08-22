package android.cnfol.com.scaleupimgactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cnfol.android.scaleupimg.ImageInfo;
import com.cnfol.android.scaleupimg.ImagePreviewActivity;
import com.cnfol.android.scaleupimg.ImagePreviewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String url = "https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=87015dd2546034a83defb0d3aa7a2231/4b90f603738da977273652f8b851f8198718e3cc.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView iv = (ImageView) findViewById(R.id.iv_);
        Glide.with(this).load(url)//
                .placeholder(R.drawable.ic_default_color)//
                .error(R.drawable.ic_default_color)//
                .diskCacheStrategy(DiskCacheStrategy.ALL)//
                .into(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ImageInfo> infos = new ArrayList<>();
                ImageInfo imageInfo = new ImageInfo();

                imageInfo.bigImageUrl = url;
                imageInfo.imageViewHeight = iv.getHeight();
                imageInfo.imageViewWidth = iv.getWidth();
                int[] points = new int[2];
                iv.getLocationInWindow(points);
                imageInfo.imageViewX = points[0];
                imageInfo.imageViewY = points[1] - getStatusHeight(MainActivity.this);
                infos.add(imageInfo);
                //intent.putExtra(PARAM.IMAGE_LIST, strings);
                //intent.putExtra(PARAM.IMAGE_INDEX, pos);
                //   startActivity(intent);


                ImagePreviewActivity.enterActivity(MainActivity.this, infos, 0, new ImagePreviewAdapter.ImageLoader() {
                    @Override
                    public void onDisplayImage(Context context, ImageView imageView, String url) {
                        Glide.with(context).load(url)//
                                .placeholder(R.drawable.ic_default_color)//
                                .error(R.drawable.ic_default_color)//
                                .diskCacheStrategy(DiskCacheStrategy.ALL)//
                                .into(imageView);
                    }

                    @Override
                    public Bitmap getCacheImage(String url) {
                        return null;
                    }
                });
            }
        });
    }
    /**
     * 获得状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
