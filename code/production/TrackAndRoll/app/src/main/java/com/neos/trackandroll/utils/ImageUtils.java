package com.neos.trackandroll.utils;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageUtils {

    /**
     * Method that sets the image layout
     * @param margin : the margin
     * @param src : the source image
     * @return : the image
     */
    public static ImageView setImageLayout(int margin, ImageView src){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(margin, margin, margin, margin);
        src.setLayoutParams(lp);
        return src;
    }

    /**
     * Method that loads the photo
     * @param path : the path of the photo
     * @param destination : the destination image
     */
    public static void loadPhoto(String path, ImageView destination) {
        Picasso.with(destination.getContext())
                .load(new File(path))
                .fit()
                .centerCrop()
                .into(destination);
    }

    /**
     * Method that says if the file is an image
     * @param filePath : the file path
     * @return boolean
     */
    public static boolean isFileImage(String filePath){
        boolean isFileImage = false;
        String[] okFileExtensions =  new String[] {"jpg", "png", "gif","jpeg"};
        try {
            File file = new File(filePath);
            if(file.exists()) {
                for (String extension : okFileExtensions) {
                    if (file.getName().toLowerCase().endsWith(extension)) {
                        LogUtils.d(LogUtils.DEBUG_TAG, "File is image");
                        isFileImage = true;
                    }
                }
            }
        }catch(Exception e){
            LogUtils.e(LogUtils.DEBUG_TAG,"ERROR Exception Image Utils => ",e);
            isFileImage = false;
        }
        return isFileImage;
    }
}
