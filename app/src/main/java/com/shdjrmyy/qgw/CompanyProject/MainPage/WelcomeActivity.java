package com.shdjrmyy.qgw.CompanyProject.MainPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.LoginPage.LoginActivity;

public class WelcomeActivity extends Activity{

    ImageView img;
    Handler han = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        img=findViewById(R.id.qgw_img);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516182295899&di=77e22c3ffdf6998338ec79b8090585f6&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201412%2F20%2F20141220165157_YjC88.jpeg")
                .into(img);
        han.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                finish();
            }
        },1000);
    }
}
