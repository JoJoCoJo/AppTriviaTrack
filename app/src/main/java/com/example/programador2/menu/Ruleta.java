package com.example.programador2.menu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Programador2 on 22/06/2016.
 */
public class Ruleta extends AppCompatActivity implements View.OnClickListener
{

    Button btnGirar;
    ImageView imgruleta;
    MediaPlayer mp;
    int posicion1 =-0;
   public Integer numeleccion;
 public   ArrayList<Integer> lis5 ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmruleta);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        posicion1 =-0;
*/
        lis5= new ArrayList<Integer>();
        btnGirar=(Button ) findViewById(R.id.btnGirar);
        imgruleta =(ImageView)findViewById(R.id.imgruleta);
        mp = MediaPlayer.create(this, R.raw.ruleta);

        btnGirar.setOnClickListener(this);

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
        super.onPause();
        mp.pause();
    }

    @Override
    public void finishAfterTransition() {
        super.finishAfterTransition();
        Toast.makeText(getApplicationContext(), "TERMINO2", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
        Toast.makeText(getApplicationContext(), "TERMINO1", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnGirar:
                imgruleta.setRotation(0);

                Animation giro;
                Animation giroveloz;
                numeleccion =0;
              //  lis5 = new ArrayList();
                Random rando=new Random();

                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                numeleccion = rando.nextInt((7 - 1) + 1) + 1;

                giro = AnimationUtils.loadAnimation(this, R.animator.girar);
                //    giro.reset();

                giroveloz = AnimationUtils.loadAnimation(this, R.animator.girar);
                giroveloz.reset();

                if(numeleccion ==0){
                    giro = AnimationUtils.loadAnimation(this, R.animator.girar);
                    //    giro.reset();
                    posicion1=-360;
                    Toast.makeText(getApplicationContext(), "Ciencias", Toast.LENGTH_SHORT).show();
                }

                if(numeleccion==1){
                    giro = AnimationUtils.loadAnimation(this, R.animator.girarmatematmatica);
                    //   giro.reset();
                    Toast.makeText(getApplicationContext(), "Matematicas", Toast.LENGTH_SHORT).show();
                    posicion1=-270;
                }

                if(numeleccion==2){
                    giro = AnimationUtils.loadAnimation(this, R.animator.girarciencias);
                    // giro.reset();
                    Toast.makeText(getApplicationContext(), "Ciencias Naturales", Toast.LENGTH_SHORT).show();
                    posicion1=-360;
                }

                if(numeleccion==3){
                    giro = AnimationUtils.loadAnimation(this, R.animator.girarcivicaetica);
                    //giro.reset();
                    Toast.makeText(getApplicationContext(), "Formacion Civica y etica", Toast.LENGTH_SHORT).show();
                    posicion1=-315;
                }

                if(numeleccion==4){
                    giro = AnimationUtils.loadAnimation(this, R.animator.girarespaniol);
                    //giro.reset();
                    Toast.makeText(getApplicationContext(), "Español", Toast.LENGTH_SHORT).show();
                    posicion1=-220;
                }

                if(numeleccion==5){
                    giro = AnimationUtils.loadAnimation(this, R.animator.girargeografia);
                    posicion1=-65;
                    //giro.reset();
                    Toast.makeText(getApplicationContext(), "Geografia", Toast.LENGTH_SHORT).show();
                }
                if(numeleccion==6){
                    giro = AnimationUtils.loadAnimation(this, R.animator.girarhistoria);
                    //giro.reset();
                    Toast.makeText(getApplicationContext(), "Historia", Toast.LENGTH_SHORT).show();
                    posicion1=-125;
                }

                if(numeleccion==7){
                    giro = AnimationUtils.loadAnimation(this, R.animator.giraringles);
                    // giro.reset();
                    Toast.makeText(getApplicationContext(), "Ingles", Toast.LENGTH_SHORT).show();
                    posicion1=-175;
                }


                imgruleta.startAnimation(giroveloz);
                giroveloz.cancel();
                imgruleta.startAnimation(giro);


                giro.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // Toast.makeText(getApplicationContext(), "empesoo", Toast.LENGTH_SHORT).show();
                        mp.setLooping(true);
                        mp.start();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //    Toast.makeText(getApplicationContext(), "termino", Toast.LENGTH_SHORT).show();
                        imgruleta.setRotation(posicion1);
                        mp.setLooping(false);
                        mp.stop();


                        Intent ds = new Intent(Ruleta.this,ResultadoRuleta.class);
                        ds.putExtra("num",numeleccion.toString());
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable("array2",lis5);

                       // ds.putIntegerArrayListExtra("array2",lis5);
                        ds.putExtras(bundleObject);
                        startActivity(ds);



                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                giroveloz.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Toast.makeText(getApplicationContext(), "empezo1", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(getApplicationContext(), "termino1", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });




                break;

            default:
                break;


        }
    }




}
