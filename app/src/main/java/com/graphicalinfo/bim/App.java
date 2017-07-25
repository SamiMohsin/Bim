package com.graphicalinfo.bim;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.graphicalinfo.bim.utils.FileCache;
import com.graphicalinfo.bim.utils.FontCache;
import com.graphicalinfo.bim.utils.ImageCache;

import java.lang.reflect.Field;

/**
 * Created by ayoob on 25/07/17.
 */

public class App extends Application {


    private static final ImageCache imageCache = new ImageCache(null);
    private static FileCache fileCache = null;
    private static SharedPreferences sharedPreferences = null;


    public static ImageCache getImageCache() {
        return imageCache;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static FileCache getFileCache() {
        return fileCache;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initFonts();
    }

    @Override
    public void onLowMemory() {
        App.getImageCache().purge();
        super.onLowMemory();
    }


    private void initFonts() {
        try {
            final Typeface JosefinSlab = FontCache.getTypeface(this,"fonts/OpenSans-Regular.ttf");

            final Field defaultFontTypefaceField = Typeface.class.getDeclaredField("SERIF");
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, JosefinSlab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
