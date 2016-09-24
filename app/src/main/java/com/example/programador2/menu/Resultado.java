package com.example.programador2.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hpsamus on 30/06/2016.
 */
public class Resultado extends ActionBarActivity {

    public ImageButton btnresultado;
    public TextView lblresultado;
    public TextView lblresult;
    public String calificacion = "";
    public String respuestaCorrecta = "";
    public int numero = 0;
    public String numeromateria="";
    public GifView cuader;
    ArrayList<Integer> list3 = new ArrayList<Integer>();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmresultado);

        btnresultado = (ImageButton) findViewById(R.id.imgbtnresult);
        lblresultado = (TextView) findViewById(R.id.lblresultado);
        //lblresult = (TextView) findViewById(R.id.lblinfo);
        cuader = (GifView) findViewById(R.id.gifview);

        String stringInFO = " ";
      //  stringInFO += "duracion:" + cuader.getM + "\n";
        //stringInFO += "w x h:"+cuader.getMoviewidth()+ " x " + cuader.getMovieheing() + "\n";
        //lblresult.setText(stringInFO);

        Bundle bundle = getIntent().getExtras();

        calificacion = bundle.getString("cal");
        respuestaCorrecta = bundle.getString("correc");
        numero = bundle.getInt("num");
        numeromateria = bundle.getString("nummateria");

      //  list3 = bundle.getIntegerArrayList("array");
     //   list3= (ArrayList<Integer>)bundle.getSerializable("array");

        if(calificacion.trim().equals("c")  )
        {
            lblresultado.setText("Respuesta Correcta");
        }
        else
        {
            lblresultado.setText("Fallaste la respuesta correcta es " + respuestaCorrecta );

        }


        //  http://usandojava.blogspot.mx/2012/05/generar-numeros-aleatorios-sin.html
        list3 = (ArrayList) bundle.getIntegerArrayList("array");

        btnresultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(list3.size()>=10) {
                   Intent in = new Intent(Resultado.this, MainActivity.class);
                   String osu=((MyApplication)getApplication()).getSomeVariable();
                   in.putExtra("nombre",osu);
                   startActivity(in);
               }
                else
               {
                   Intent in = new Intent(Resultado.this, Preguntas.class);
                   // in.putIntegerArrayListExtra("array2", list3);
                   Bundle bundleObject = new Bundle();
                   bundleObject.putSerializable("array2", list3);
                   in.putExtra("num",numeromateria);
                   in.putExtras(bundleObject);
                   startActivity(in);
               }



            }
        });

    }
}
