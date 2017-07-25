package com.graphicalinfo.bim.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ayoob on 25/07/17.
 */

public class FontCache {
    private static Map<String, Typeface> sCachedFonts = new HashMap<String, Typeface>();

    public static Typeface getTypeface(Context context, String assetPath) {
        if (!sCachedFonts.containsKey(assetPath)) {
            Typeface tf = Typeface.createFromAsset(context.getAssets(), assetPath);
            sCachedFonts.put(assetPath, tf);
        }

        return sCachedFonts.get(assetPath);
    }


}
