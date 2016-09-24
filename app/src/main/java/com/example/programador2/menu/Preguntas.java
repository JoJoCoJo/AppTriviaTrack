package com.example.programador2.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.util.ArrayList;
import org.w3c.dom.Text;





import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * Created by Programador2 on 20/06/2016.
 */


public class Preguntas extends AppCompatActivity {

    public String gen;

    public int Idpasar;

    public AlertDialog.Builder alertDialog;

    private Runnable r;
    public static Activity lista;
    private Handler h = new Handler();
    public String comprobar ="";
    public String quienes="";
    public int oid=0;
    public  String opregunta="";
    public String orespuestav="";
    public String orespuestaf1="";
    public String orespuestaf2="";
    public String orespuestaf3="";
    public TextView txtpregunta;
    public Button btnrespuesta1;
    public Button btnrespuesta2;
    public Button btnrespuesta3;
    public Button btnrespuesta4;
    public ImageButton btnvolver;
    int numeleccion;
   public  ArrayList lista2;
    public  int recarga=0;
public   int nums=0;
    public String nummateria;

    private int valorInicial;
    private int valorFinal;
    private ArrayList listaNumero;

    String o;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

        setContentView(R.layout.frmpreguntas);
   txtpregunta=(TextView)findViewById(R.id.lblpregunta);
        btnrespuesta1=(Button)findViewById(R.id.btnrespuesta1);
        btnrespuesta2=(Button)findViewById(R.id.btnrespuesta2);
        btnrespuesta3=(Button)findViewById(R.id.btnrespuesta3);
        btnrespuesta4=(Button)findViewById(R.id.btnrespuesta4);
   btnvolver=(ImageButton)findViewById(R.id.btnvolvergirar);

            Bundle bundle = getIntent().getExtras();



            nummateria = bundle.getString("num");
            lista2= (ArrayList<Integer>)bundle.getSerializable("array2");




            //ArrayList<CustomClass> classObject = (ArrayList<CustomClass>) bundleObject.getSerializable("key");


            TareaWSConsulta ta = new TareaWSConsulta();
            ta.execute();
            btnvolver.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Preguntas.this,Ruleta.class);
                    startActivity(in);
                }
            });


btnrespuesta1.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {

        lista2.add(nums);
        if (btnrespuesta1.getText().toString()==orespuestav.toString().trim())
        {
            Intent in2 =new Intent(Preguntas.this,Resultado.class);
            in2.putExtra("num", nums);
            in2.putExtra("cal", "c");
            in2.putExtra("nummateria",nummateria);
            in2.putExtra("correc", orespuestav.toString().trim());
            Bundle bundleObject = new Bundle();
            bundleObject.putSerializable("array",lista2);
            in2.putExtras(bundleObject);
            startActivity(in2 );

        }
        else {

            Intent in2 =new Intent(Preguntas.this,Resultado.class);
            in2.putExtra("num", nums);
            in2.putExtra("cal", "i");
            in2.putExtra("nummateria",nummateria);
            in2.putExtra("correc", orespuestav.toString().trim());
            Bundle bundleObject = new Bundle();
            bundleObject.putSerializable("array",lista2);
            in2.putExtras(bundleObject);
            startActivity(in2 );
        }
    }
});
            btnrespuesta2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    lista2.add(nums);
                    if (btnrespuesta2.getText().toString()==orespuestav.toString().trim())
                    {
                        Intent in2 =new Intent(Preguntas.this,Resultado.class);
                        in2.putExtra("num", nums);
                        in2.putExtra("cal", "c");
                        in2.putExtra("nummateria",nummateria);
                        in2.putExtra("correc", orespuestav.toString().trim());
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable("array",lista2);
                        in2.putExtras(bundleObject);
                        startActivity(in2 );

                    }
                    else {

                        Intent in2 =new Intent(Preguntas.this,Resultado.class);
                        in2.putExtra("num", nums);
                        in2.putExtra("cal", "i");
                        in2.putExtra("nummateria",nummateria);
                        in2.putExtra("correc", orespuestav.toString().trim());
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable("array",lista2);
                        in2.putExtras(bundleObject);
                        startActivity(in2 );
                    }
                }
            });

            btnrespuesta3.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    lista2.add(nums);
                    if (btnrespuesta3.getText().toString()==orespuestav.toString().trim())
                    {
                        Intent in2 =new Intent(Preguntas.this,Resultado.class);
                        in2.putExtra("num", nums);
                        in2.putExtra("cal", "c");
                        in2.putExtra("nummateria",nummateria);
                        in2.putExtra("correc", orespuestav.toString().trim());
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable("array",lista2);
                        in2.putExtras(bundleObject);
                        startActivity(in2 );

                    }
                    else {

                        Intent in2 =new Intent(Preguntas.this,Resultado.class);
                        in2.putExtra("num", nums);
                        in2.putExtra("nummateria",nummateria);
                        in2.putExtra("cal", "i");
                        in2.putExtra("correc", orespuestav.toString().trim());
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable("array",lista2);
                        in2.putExtras(bundleObject);
                        startActivity(in2 );
                             }
                }
            });
btnrespuesta4.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {


        lista2.add(nums);

        if (btnrespuesta4.getText().toString()==orespuestav.toString().trim())
        {
          //  Toast.makeText(getBaseContext(), "Respuesta Correcta", Toast.LENGTH_SHORT).show();
            Intent in2 =new Intent(Preguntas.this,Resultado.class);
           in2.putExtra("num", nums);
           in2.putExtra("cal", "c");
            in2.putExtra("nummateria",nummateria);
           in2.putExtra("correc", orespuestav.toString().trim());
          //  in2.putIntegerArrayListExtra("arary",lista2);

            Bundle bundleObject = new Bundle();
            bundleObject.putSerializable("array",lista2);
            in2.putExtras(bundleObject);
            startActivity(in2 );
        }
        else {

          //  Toast.makeText(getBaseContext(), "Te equivocaste La Respuesta Correcta es "+orespuestav, Toast.LENGTH_SHORT).show();
            Intent in2 =new Intent(Preguntas.this,Resultado.class);
            in2.putExtra("num", nums);
            in2.putExtra("cal", "i");
            in2.putExtra("nummateria",nummateria);
            in2.putExtra("correc", orespuestav.toString().trim());
            Bundle bundleObject = new Bundle();
            bundleObject.putSerializable("array",lista2);
            in2.putExtras(bundleObject);
            startActivity(in2 );
        }
    }
});

        }
        catch (Exception e)
        {

            Toast.makeText(getBaseContext(), "main ;) "+e.toString()+"", Toast.LENGTH_SHORT).show();
        }
    }
    private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {

        private vaPreguntas[] listaClientes;


        protected Boolean doInBackground(String... params) {

            boolean resul = true;
            variables var = new variables();

            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "GetPreguntas";
            final String SOAP_ACTION = "http://tempuri.org/GetPreguntas";
            final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("IdNivel",1);
            request.addProperty("IdMateria",nummateria);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            try
            {
                transporte.call(SOAP_ACTION, envelope);
                SoapObject resSoap =(SoapObject)envelope.getResponse();

                listaClientes = new vaPreguntas[resSoap.getPropertyCount()];
                int numc=0;
                for (int i = 0; i < listaClientes.length; i++)
                {
                    SoapObject ic = (SoapObject)resSoap.getProperty(i);
                   vaPreguntas cli = new vaPreguntas();
                    numc=Integer.parseInt(ic.getProperty(1).toString());
                    cli.row=Integer.parseInt(ic.getProperty(0).toString());
                    cli.numcol=Integer.parseInt(ic.getProperty(1).toString());
                    cli.Id = Integer.parseInt(ic.getProperty(2).toString());
                    cli.Pregunta = ic.getProperty(3).toString();
                    cli.RespuestaV=ic.getProperty(4).toString();
                    cli.RespuestaF1=ic.getProperty(5).toString();
                    cli.RespuestaF2=ic.getProperty(6).toString();
                    cli.RespuestaF3=ic.getProperty(7).toString();
                    listaClientes[i] = cli;
                }
                NumeroAleatorios na = new NumeroAleatorios(1,numc,lista2);
                nums=na.generar();
            }
            catch (Exception e)
            {
              //  Toast.makeText(getBaseContext(), "Nouum ;) "+e.toString()+"", Toast.LENGTH_SHORT).show();
                resul = false;
            }

            return resul;
        }

        protected void onPostExecute(Boolean result) {

            if (result==true)
            {
                Toast.makeText(getBaseContext(), "Nouum ;) "+listaClientes.length+"", Toast.LENGTH_SHORT).show();
                final String[] datos = new String[listaClientes.length];
                for(int i=0; i<listaClientes.length; i++)
                    //datos[i]= Integer.toString(listaClientes[i].id).toString();
                    // (listaClientes[i].id);
                {
                   // oid = listaClientes[i].Id;
               if(nums== listaClientes[i].row) {

                   opregunta = listaClientes[i].Pregunta.toString();
                   orespuestav = listaClientes[i].RespuestaV.toString();
                   orespuestaf1 = listaClientes[i].RespuestaF1.toString();
                   orespuestaf2 = listaClientes[i].RespuestaF2.toString();
                   orespuestaf3 = listaClientes[i].RespuestaF3.toString();
               }


                }

                Random rando=new Random();

                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                numeleccion = rando.nextInt((4 - 1) + 1) + 1;

               if(numeleccion==1)
               {

                   txtpregunta.setText(opregunta);
                   btnrespuesta1.setText(orespuestav);
                   btnrespuesta2.setText(orespuestaf2);
                   btnrespuesta3.setText(orespuestaf3);
                   btnrespuesta4.setText(orespuestaf1);
               }
                if(numeleccion==2)
                {

                    txtpregunta.setText(opregunta);
                    btnrespuesta1.setText(orespuestaf2);
                    btnrespuesta2.setText(orespuestav);
                    btnrespuesta3.setText(orespuestaf3);
                    btnrespuesta4.setText(orespuestaf1);

                }
                if(numeleccion==3)
                {

                    txtpregunta.setText(opregunta);
                    btnrespuesta1.setText(orespuestaf3);
                    btnrespuesta2.setText(orespuestaf2);
                    btnrespuesta3.setText(orespuestav);
                    btnrespuesta4.setText(orespuestaf1);

                }
                if(numeleccion==4)
                {

                    txtpregunta.setText(opregunta);
                    btnrespuesta1.setText(orespuestaf1);
                    btnrespuesta2.setText(orespuestaf2);
                    btnrespuesta3.setText(orespuestaf3);
                    btnrespuesta4.setText(orespuestav);

                }



                   // datos[i]=(Integer.toString(listaClientes[i].Id).toString() +"-" + listaClientes[i].nombre.toString() +"-" + listaClientes[i].Motivo.toString());

            }
            if (result==false)
            {
                Toast.makeText(getBaseContext(), "No se encontro el servidor;) "+listaClientes.length+"", Toast.LENGTH_SHORT).show();
            }



        }
    }



}

