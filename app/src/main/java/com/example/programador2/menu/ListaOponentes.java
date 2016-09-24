package com.example.programador2.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
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

/**
 * Created by Programador2 on 22/06/2016.
 */
public class ListaOponentes extends Activity {


    public String gen;

    public int Idpasar;
    public ListView lstClientes;
    public AlertDialog.Builder alertDialog;
    public  TextView text;
    private Runnable r;
    public static Activity lista;
    private Handler h = new Handler();
    public String comprobar ="";
    public String quienes="";
    public String Id_Usuario;
    String o;
    public String msje="";
    public boolean  ms;
    public String re="";
    public String UsuarioRetado="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmoponentes);

        Id_Usuario=((MyApplication)this.getApplication()).getSomeVariable();

        lstClientes =(ListView )findViewById(R.id.listoponentes );
        text= (TextView )findViewById(R.id.lblnomase);
        ImageButton ima = (ImageButton)findViewById(R.id.btnvolverdetalle);

        text.setText(o);
        TareaWSConsulta tarea1 = new TareaWSConsulta();
        tarea1.execute();

        ima.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                 //   h.removeCallbacks(r);
                    Intent o = new Intent(ListaOponentes.this, Ruleta.class);
                 /*   o.putExtra("texto",text.getText());
                    o.putExtra("volver", "45");
                    o.putExtra("listadocontador", "volver");
                    o.putExtra("detallecontador", "sd");*/
                    // finish();
                    startActivity(o);

            }
        });
    }

    private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {

        private Cliente[] listaClientes;


        protected Boolean doInBackground(String... params) {

            boolean resul = true;
            variables var = new variables();

            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "GetUsuariosAll";
            final String SOAP_ACTION = "http://tempuri.org/GetUsuariosAll";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("oUser",Id_Usuario);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            try
            {
                transporte.call(SOAP_ACTION, envelope);
                SoapObject resSoap =(SoapObject)envelope.getResponse();
                listaClientes = new Cliente[resSoap.getPropertyCount()];

                for (int i = 0; i < listaClientes.length; i++)
                {
                    SoapObject ic = (SoapObject)resSoap.getProperty(i);
                    Cliente cli = new Cliente();
                    cli.Id = Integer.parseInt(ic.getProperty(0).toString());
                    cli.Nombre = ic.getProperty(1).toString();
                    cli.Aseguradora=ic.getProperty(2).toString();
                    cli.numvidas=Integer.parseInt(ic.getProperty(3).toString());
                    listaClientes[i] = cli;
                }
            }
            catch (Exception e)
            {
                resul = false;
            }

            return resul;
        }

        protected void onPostExecute(Boolean result) {
            final String[] datos2;
            final String[] datos = new String[listaClientes.length];
            datos2 = new String[listaClientes.length];
            if (result)
            {

                for(int i=0; i<listaClientes.length; i++)
                    //datos[i]= Integer.toString(listaClientes[i].id).toString();
                    // (listaClientes[i].id);
                {
                    datos[i] = ( listaClientes[i].Nombre.toString() + "-" + listaClientes[i].Aseguradora.toString());

                }
                for(int i=0; i<listaClientes.length; i++)
                //datos[i]= Integer.toString(listaClientes[i].id).toString();
                // (listaClientes[i].id);
                {
                    datos2[i] = (Integer.toString(listaClientes[i].Id).toString() + "-" + listaClientes[i].Nombre.toString() + "-" + listaClientes[i].Aseguradora.toString());

                }
                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<String>(ListaOponentes.this,
                                android.R.layout.simple_list_item_1, datos );
                lstClientes.setAdapter(adaptador);
            }
            else
            {
            }
            lstClientes.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, final int posisicon,
                                        long id) {
                    // TODO Auto-generated method stub
                    final ListAdapter la = (ListAdapter) arg0.getAdapter();

                    datos2[posisicon].trim();
                    Toast.makeText(
                            arg1.getContext()
                            ,"cliente :"+la.getItem(posisicon).toString()+"  .. .  "

                            ,Toast.LENGTH_LONG)
                            .show();


                    UsuarioRetado=datos2[posisicon].trim();

                    String[] textElements = UsuarioRetado.split("-");
                    UsuarioRetado = textElements[0];



                    alertDialog = new AlertDialog.Builder(ListaOponentes.this);
                    alertDialog.setTitle("Confirmar ");
                    alertDialog.setMessage("Desea retar a un duelo a este usuario ?"+UsuarioRetado);

                    alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                            // Write your code here to invoke YES event
                            //   Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                          /*  Intent in = new Intent(ListaOponentes.this,Ruleta.class);
                            in.putExtra("nombre",la.getItem(posisicon).toString());
                            in.putExtra("texto",text.getText());
                            in.putExtra("quieneres", quienes);
                            h.removeCallbacks(r);
                            //  finish();
                            startActivity(in);*/

                           TareaWSConsulta5 ta = new TareaWSConsulta5();
                           ta.execute();
                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

                    alertDialog.show();
                }
            });
        }
    }

    private class TareaWSConsulta5 extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {
            //string nombre, string apellido, DateTime fecha, string Usuario, string Password
            boolean resul = true;
            variables var = new variables();
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "PartidaAddRetar";
            final String SOAP_ACTION = "http://tempuri.org/PartidaAddRetar";
            final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            // Variables var = new Variables();

            //   final SoapObject oUser = request.addProperty("oUser",usuario );
            request.addProperty("IdUsuario1",Id_Usuario );
            //request.addProperty("oPasword",nombre);
            request.addProperty("IdUsuario2", UsuarioRetado);

            request.addProperty("PuntuajeUsuario1","0");
            request.addProperty("PuntuajeUsuario2","0");

            request.addProperty("IdNivel","1");
            request.addProperty("Solicitud","p");



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                re = resultado_xml.toString();
                msje = re;

                if (re.equals(""))
                    resul = false;
            } catch (Exception e) {
                // msje=e.toString();
                resul = false;
            }

            return resul;
        }


        protected void onPostExecute(Boolean result) {

            Toast.makeText(getBaseContext(), re, Toast.LENGTH_SHORT).show();
            if (result == false) {
                Toast.makeText(getBaseContext(), "No se encontro el servidor ;) ", Toast.LENGTH_SHORT).show();
            }
            if (result == true) {
                if (re.equals("0")) {
                    Toast.makeText(getBaseContext(), "Usuario o Contraseña invalidos", Toast.LENGTH_SHORT).show();
                }
                if (!re.equals("0")) {

                    Toast.makeText(getBaseContext(), "Retado partida  "+ re,Toast.LENGTH_SHORT).show();
                  /*  Intent in = new Intent(ListaOponentes.this,Ruleta.class);
                    in.putExtra("nombre",UsuarioRetado.toString());
                    in.putExtra("texto",text.getText());
                    in.putExtra("quieneres", quienes);
                    h.removeCallbacks(r);
                    //  finish();
                    startActivity(in);*/
                    Intent in = new Intent(ListaOponentes.this,MainActivity.class);
                    String osu=((MyApplication)getApplication()).getSomeVariable();
                    in.putExtra("nombre",osu);
                    startActivity(in);


                           /* in.putExtra("tipousuario", tipousuario);
                            in.putExtra("detallecontador", "d");
                            in.putExtra("listadocontador", "d");
                            txtPassword.setText("");
                            txtUsuario.setText("");*/
                   // startActivity(in );

                /*    if (tipousuario.equals("false")) {

                            Intent in2 = new Intent (Login.this ,Contador.class);
                            Toast.makeText(getBaseContext(), "Bienvenido",Toast.LENGTH_SHORT).show();
                            in2.putExtra("texto", txtUsuario.getText().toString());
                            in2.putExtra("detallecontador", "d");
                            in2.putExtra("listadocontador", "d");
                            txtPassword.setText("");
                            txtUsuario.setText("");
                            startActivity(in2 );


                    }*/

                }
            }
        }
    }




}




