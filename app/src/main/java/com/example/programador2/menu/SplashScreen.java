package com.example.programador2.menu;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class SplashScreen  extends Activity{

    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mp = MediaPlayer.create(this, R.raw.pianisimo);


        mp.setLooping(true);
        mp.start();


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,Login.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mp.isPlaying())
        {

            mp.stop();
            mp.release();
        }
    }



    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mp.pause();
        finish();
    }

}