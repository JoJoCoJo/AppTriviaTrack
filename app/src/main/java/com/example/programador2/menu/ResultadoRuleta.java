package com.example.programador2.menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hpsamus on 27/08/2016.
 */
public class ResultadoRuleta extends AppCompatActivity{
    private ArrayList listaNumero;
    public  ArrayList lista2;
    public  int recarga=0;
    public   int nums=0;
    public String nummateria;
    public Button btnseguir;
    public TextView lblResul;
    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.frmresultadoruleta);


        Bundle bundle = getIntent().getExtras();
        nummateria = bundle.getString("num");
        lista2= (ArrayList<Integer>)bundle.getSerializable("array2");
lblResul=(TextView)findViewById(R.id.lblNomMateria);
        btnseguir=(Button)findViewById(R.id.btnSeguir);


        if(nummateria.trim().equals("0")){
          lblResul.setText("Ciencias");
        }

        if(nummateria.trim().equals("1")){
            lblResul.setText("Matematicas");
        }

        if(nummateria.trim().equals("2")){
            lblResul.setText("Ciencias Naturales");
        }

        if(nummateria.trim().equals("3")){
            lblResul.setText("Formacion Civica y etica");
        }

        if(nummateria.trim().equals("4")){
            lblResul.setText("Español");
        }

        if(nummateria.trim().equals("5")){
            lblResul.setText("Geografia");
                 }
        if(nummateria.trim().equals("6")){
            lblResul.setText("Historia");
             }

        if(nummateria.trim().equals("7")){
            lblResul.setText("Ingles");
        }







        btnseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ds = new Intent(ResultadoRuleta.this,Preguntas.class);
                ds.putExtra("num",nummateria.toString());
                Bundle bundleObject = new Bundle();
                bundleObject.putSerializable("array2",lista2);

                // ds.putIntegerArrayListExtra("array2",lis5);
                ds.putExtras(bundleObject);
                startActivity(ds);

            }
        });
    }
}
