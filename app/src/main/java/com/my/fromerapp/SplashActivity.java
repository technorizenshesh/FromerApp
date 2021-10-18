package com.my.fromerapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.my.fromerapp.act.Login;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    private ImageView iv_Logo;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        finds();
    }

    private static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private void finds() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                String User_id = Preference.get(SplashActivity.this,Preference.KEY_user_id);

                if(User_id != null && !User_id.trim().equalsIgnoreCase("0")){

                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else
                {
                    Intent intent=new Intent(SplashActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }

                  /* Intent i = new Intent(SplashActivity.this, Login.class);
                    startActivity(i);
                    finish();*/
            }
        }, 3000);
    }
}