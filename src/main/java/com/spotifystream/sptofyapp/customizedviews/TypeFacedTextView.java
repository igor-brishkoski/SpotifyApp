package com.spotifystream.sptofyapp.customizedviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.spotifystream.sptofyapp.R;
import com.spotifystream.sptofyapp.fundamentals.SpotifyApp;
import com.spotifystream.sptofyapp.fundamentals.SpotifyConstants;

public class TypeFacedTextView extends TextView {

    public TypeFacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) {
            TypedArray styledAttr = SpotifyApp.getContext().obtainStyledAttributes(attrs, R.styleable.TypeFacedTextView);
            String fontName = styledAttr.getString(R.styleable.TypeFacedTextView_typeface);
            styledAttr.recycle();

            if (fontName != null) {
                Typeface typeface = Typeface.createFromAsset(SpotifyApp.getContext().getAssets(), fontName);
                setTypeface(typeface);
            }
        }
    }

    public static void AddRobotoFont(TextView textView) {
        Typeface typeface = Typeface.createFromAsset(SpotifyApp.getContext().getAssets(), SpotifyConstants.ROBOTO_THIN);
        textView.setTypeface(typeface);
    }

}