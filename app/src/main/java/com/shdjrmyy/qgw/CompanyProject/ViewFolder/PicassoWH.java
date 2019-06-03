package com.shdjrmyy.qgw.CompanyProject.ViewFolder;


import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by 16001 on 2017/10/26 0026.
 */

public abstract class PicassoWH implements Transformation {

    // TODO: 2017/10/26 0026 根据屏幕宽度 适配图片

    int w=0;

    public PicassoWH(int w) {
        this.w = w;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        onNewH(getNewH(source.getHeight(),source.getWidth()),source);
        return source;
    }

    @Override
    public String key() {
        return "";
    }

    public abstract void onNewH(int h,Bitmap bitmap);


    public int getNewH(int oh,int ow){
        MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
        return new BigDecimal(oh).divide(new BigDecimal(ow),mc).multiply(new BigDecimal(w),mc).intValue();
    }
}
