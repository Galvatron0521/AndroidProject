package com.shdjrmyy.qgw.CompanyProject.ViewFolder;

import android.graphics.Color;

import com.dd.morphingbutton.MorphingButton;
import com.shdjrmyy.qgw.CompanyProject.R;

/**
 * Created by 16001 on 2017/10/25 0025.
 */

public class ButtonState {

    public static final int du = 500;

    public static void onSquare(final MorphingButton btnMorph) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(du)
                .cornerRadius(5)
                .width(300)
                .height(50)
                .color(Color.parseColor("#1296db"))
                .colorPressed(Color.parseColor("#339EFE"))
                .text("登入");
        btnMorph.morph(square);
    }
    public static void onSuccess(final MorphingButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(du)
                .cornerRadius(100)
                .width(180)
                .height(180)
                .color(Color.parseColor("#99CC00"))
                .colorPressed(Color.parseColor("#99CC00"))
                .icon(R.drawable.ok)
                ;
        btnMorph.morph(circle);
    }


    public static void onError(final MorphingButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(du)
                .cornerRadius(100)
                .width(180)
                .height(180)
                .color(Color.parseColor("#FF4444"))
                .colorPressed(Color.parseColor("#FF4444"))
                .icon(R.drawable.no)
                ;
        btnMorph.morph(circle);

    }



}
