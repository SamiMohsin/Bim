package com.graphicalinfo.bim.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

import com.graphicalinfo.bim.R;

/**
 * Created by ayoob on 25/07/17.
 */

public class DisplayStats {
    public DisplayMetrics displayMetrics;
    public int feedItemWidth, feedItemHeight;
    public int maxItemWidthHeight;

    public DisplayStats(Activity activity) {
        this.displayMetrics = new DisplayMetrics();
        feedItemWidth = 0;
        feedItemHeight = activity.getResources().getDimensionPixelSize(R.dimen.feed_item_height);
        maxItemWidthHeight = 0;
    }

    public void refreshStats(Activity activity) {
        if(displayMetrics != null && activity != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(GeneralUtils.displayStats.displayMetrics);
            feedItemWidth = displayMetrics.widthPixels;
            maxItemWidthHeight = Math.max(feedItemWidth, feedItemHeight);
        }
    }
}
