package com.cnfol.android.scaleupimg;

import java.io.Serializable;
public class ImageInfo implements Serializable {
    public String thumbnailUrl;
    public String bigImageUrl;
    public int imageViewHeight;
    public int imageViewWidth;
    public int imageViewX;
    public int imageViewY;


    @Override
    public String toString() {
        return "ImageInfo{" +
                "imageViewY=" + imageViewY +
                ", imageViewX=" + imageViewX +
                ", imageViewWidth=" + imageViewWidth +
                ", imageViewHeight=" + imageViewHeight +
                ", bigImageUrl='" + bigImageUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
