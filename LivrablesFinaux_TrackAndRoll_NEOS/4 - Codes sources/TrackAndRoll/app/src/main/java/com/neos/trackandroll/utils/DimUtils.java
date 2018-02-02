package com.neos.trackandroll.utils;

import android.content.Context;

public class DimUtils {

    /**
     * Method that transform a dip into pixel
     * @param ctx : the context
     * @param dips : the dips
     * @return the pixel value
     */
    public static int dipToPixel(Context ctx, float dips) {
        return (int) (dips * ctx.getResources().getDisplayMetrics().density + 0.5f);
    }
}
