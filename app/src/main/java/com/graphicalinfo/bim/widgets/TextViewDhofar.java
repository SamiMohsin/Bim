package com.graphicalinfo.bim.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.graphicalinfo.bim.utils.FontCache;

/**
 * Created by ayoob on 25/07/17.
 */

public class TextViewDhofar extends android.support.v7.widget.AppCompatTextView {

    public TextViewDhofar(Context context) {
        super(context);
        init(context);
    }

    public TextViewDhofar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextViewDhofar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface typeface = FontCache.getTypeface(context, "fonts/LobsterTwo-Regular.ttf");
        setTypeface(typeface);
    }
}
