package hackathon.com.sansad;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.FacebookSdk;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

/**
 * Created by utk994 on 23-Apr-16.
 */

public class init extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    @Override
    public void onCreate() {
        super.onCreate();


        DisplayImageOptions mOptionsSimple = new DisplayImageOptions.Builder().resetViewBeforeLoading(true)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();





        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .imageDecoder(new BaseImageDecoder(false))
                .defaultDisplayImageOptions(mOptionsSimple)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

    }

}
