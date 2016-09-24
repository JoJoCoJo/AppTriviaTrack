package com.example.programador2.menu;

/**
 * Created by Programador2 on 07/05/2016.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.support.v7.app.AlertDialog;


public class Fragment_seccion1 extends Fragment {

    public String msje="";
    private Runnable r;
    private Handler h = new Handler();
    public boolean  ms;
    public String re="";
    public AlertDialog.Builder alertDialog1;
    public TextView lblpuntuaje;
    public  String puntuaje;
    String Id_Usuario="";
    Button btniniciar;
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seccion1, container, false);
        try {

          //  MainActivity ma = new MainActivity();

          //  ma.getMyData();
            Id_Usuario= ((MyApplication) this.getActivity().getApplication()).getSomeVariable();

            String Nombre;

            variables va = new variables();
          //  Nombre=va.getUsu();
           // Nombre = ((MyApplication) this.getActivity().getApplication()).getSomeVariable();
            String s = ((MyApplication) this.getActivity().getApplication()).getSomeVariable();

            String p = ((MyApplication) this.getActivity().getApplication()).getVaUsuario();





            btniniciar =(Button)view.findViewById(R.id.btnJugar);
            lblpuntuaje=(TextView)view.findViewById(R.id.lblpuntu);

            btniniciar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent (getActivity() ,ListaOponentes.class);
                    startActivity(in );
                }
            });



           r = new Runnable() {
                @Override
                public void run() {

                    h.postDelayed(this, 2000);

                 TareaWSConsulta1 tarea3 = new TareaWSConsulta1();
                    tarea3.execute();
                }


            };
            h.post(r);

     //   lblpuntuaje.setText(puntuaje);
        } catch (Exception e) {

            alertDialog1 = new AlertDialog.Builder(getActivity());
            alertDialog1.setTitle("Confirmar atecion1");
            alertDialog1.setMessage("1 "+e.toString());
            alertDialog1.show();
        }


        return view;

        //  "Inflamos" el archivo XML correspondiente a esta sección.

    }



    private class TareaWSConsulta1 extends AsyncTask<String,Integer,Boolean> {
        private Cliente[] listaClientes;
        protected Boolean doInBackground(String... params) {
            //string nombre, string apellido, DateTime fecha, string Usuario, string Password
            boolean resul = true;
            variables var = new variables();
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "getVida";
            final String SOAP_ACTION = "http://tempuri.org/getVida";






            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Id_Usuario",Id_Usuario);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;


            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);

            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapObject resSoap =(SoapObject)envelope.getResponse();
                listaClientes = new Cliente[resSoap.getPropertyCount()];

                for (int i = 0; i < listaClientes.length; i++)
                // for (int i = 0; i < 3; i++)
                {
                    SoapObject ic = (SoapObject)resSoap.getProperty(i);
                    Cliente cli = new Cliente();
                    cli.Id = Integer.parseInt(ic.getProperty(0).toString());;
                    cli.Nombre = ic.getProperty(1).toString();
                   // cli.Aseguradora=ic.getProperty(2).toString();
                    cli.numvidas=Integer.parseInt(ic.getProperty(2).toString());
                    listaClientes[i] = cli;
                }

            } catch (Exception e) {
                // msje=e.toString();
                resul = false;
            }

            return resul;
        }


        protected void onPostExecute(Boolean result) {





            if (result == false) {
                alertDialog1 = new AlertDialog.Builder( getActivity());
                alertDialog1.setTitle("Confirmar atecion");
                alertDialog1.setMessage("No se encontro el servidor");
                alertDialog1.show();
            }
            if (result == true) {

              /*  alertDialog1 = new AlertDialog.Builder( getActivity());
                alertDialog1.setTitle("Confirmar atecion");
                alertDialog1.setMessage("entro"+listaClientes.length);
                alertDialog1.show();*/

                for(int i=0; i< listaClientes.length; i++)
                //datos[i]= Integer.toString(listaClientes[i].id).toString();
                // (listaClientes[i].id);
                {
                    puntuaje = Integer.toString(listaClientes[i].numvidas);


                }


             /*   alertDialog1 = new AlertDialog.Builder( getActivity());
                alertDialog1.setTitle("Confirmar atecion");
                alertDialog1.setMessage("opuntuaje"+puntuaje);
                alertDialog1.show();*/
                lblpuntuaje.setText(puntuaje);

            /*   alertDialog1 = new AlertDialog.Builder( getActivity());
                alertDialog1.setTitle("Confirmar atecion");
                alertDialog1.setMessage(re);
                alertDialog1.show();*/



                }
            }
        }

}
