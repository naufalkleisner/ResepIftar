package com.iftar.resep.resepiftar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;


public class Splash extends AppCompatActivity {
    private static String TAG = "String";
    private SharedPreferences namakota;

    static String PackageName;

    SplashFragment splashFragment;
    NewFragment newFragment;

    FragmentManager fm;
    FragmentTransaction ft;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //getting the package name of the project
        PackageName=getPackageName();

        //initializing fragments
        splashFragment = new SplashFragment();
        newFragment=new NewFragment();

        //adding splash screen to fragment
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.container, splashFragment, null);
        ft.commit();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                				Intent mainIntent = null;

				mainIntent = new Intent(Splash.this,
					RecipesList.class);

				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
            }

        }, SPLASH_TIME_OUT);
    }


    //initialize data fragment to load after splash screen
    public static class NewFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.new_frag, container, false);
            return view;
        }
    }

    //initialize splash fragment and populating splash fragment with animated gif image using Ion library
    public static class SplashFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.splash, container, false);
            ImageView imgView=(ImageView) view.findViewById(R.id.imageView);
            Ion.with(imgView)
                    //.error(R.drawable.default_image)
                    .animateGif(AnimateGifMode.ANIMATE)
                    .load("android.resource://" + PackageName + "/" + R.drawable.qww)
                    .withBitmapInfo();
            Log.d(TAG, "nama paket :   \n"+PackageName);

            return view;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

        }
    }
}


