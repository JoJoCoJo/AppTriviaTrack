package com.example.programador2.menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by hpsamus on 07/08/2016.
 */
public class Fragment_seccion4 extends Fragment {
    private Runnable r;
    public ListView lstClientes;
    public String gen;
    private Handler h = new Handler();
    //private Contador cont = new Contador();
    //public static Activity activityasesores;
    String Id_Usuario="";
    EditText et;
    public int Idpasar;
    public AlertDialog.Builder alertDialog3;
    int textlength = 0;
    private ArrayList<String> array_sort = new ArrayList<String>();
    ArrayAdapter<String> adaptador;
    private String listview_array[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN" };
    private String datos[];
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view4 = inflater.inflate(R.layout.fragment_seccion4, container, false);
        try {


            lstClientes=(ListView)view4.findViewById(R.id.listpartidas);

            et = (EditText) view4.findViewById(R.id.editText02);
            Id_Usuario= ((MyApplication) this.getActivity().getApplication()).getSomeVariable();

            //  MainActivity ma = new MainActivity();
            //  ma.getMyData();


            String Nombre;

            variables va = new variables();
            //  Nombre=va.getUsu();
            // Nombre = ((MyApplication) this.getActivity().getApplication()).getSomeVariable();
            String s = ((MyApplication) this.getActivity().getApplication()).getSomeVariable();



            r = new Runnable() {
                @Override
                public void run() {

                    h.postDelayed(this, 2000);


                    if(et.getText().length()==0) {
                      TareaWSConsulta4 tarea3 = new TareaWSConsulta4();
                        tarea3.execute();
                    }
                    else
                    {

                    }

                }


            };
            h.post(r);

            //   lblpuntuaje.setText(puntuaje);
        } catch (Exception e) {
            alertDialog3 = new AlertDialog.Builder( getActivity());
            alertDialog3.setTitle("Confirmar atecion");
            alertDialog3.setMessage("error 4 "+e.toString());
            alertDialog3.show();

        }
        //  "Inflamos" el archivo XML correspondiente a esta sección.
        return view4;
    }

    private class TareaWSConsulta4 extends AsyncTask<String,Integer,Boolean> {

        private vapartidas[] listaClientes;


        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            variables var = new variables();
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "PartidaGetId";
            final String SOAP_ACTION = "http://tempuri.org/PartidaGetId";
            //final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("oId",Id_Usuario);
            request.addProperty("Sol","a");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            try
            {
                transporte.call(SOAP_ACTION, envelope);
                SoapObject resSoap =(SoapObject)envelope.getResponse();
                listaClientes = new vapartidas[resSoap.getPropertyCount()];

                for (int i = 0; i < listaClientes.length; i++)
                // for (int i = 0; i < 3; i++)
                {
                    SoapObject ic = (SoapObject)resSoap.getProperty(i);
                    vapartidas cli = new vapartidas();

                    cli.IdPartida = Integer.parseInt(ic.getProperty(0).toString());
                    cli.Solicitud= ic.getProperty(8).toString();
                    cli.retador = ic.getProperty(3).toString();
//if(cli.Solicitud.equals("p")) {
                    listaClientes[i] = cli;
//}
                }
            }
            catch (Exception e)
            {
                resul = false;
            }

            return resul;
        }

        protected void onPostExecute(Boolean result) {

            if (result==true)
            {

                datos = new String[listaClientes.length];
                for(int i=0; i< listaClientes.length; i++)
                //datos[i]= Integer.toString(listaClientes[i].id).toString();
                // (listaClientes[i].id);
                {
                    //if(listaClientes[i].Solicitud.toString().trim().equals("p")) {
                    datos[i] = (Integer.toString(listaClientes[i].IdPartida).toString() + "-" + listaClientes[i].retador.toString());

                    //  }
                }

                adaptador = new ArrayAdapter<String>( getActivity(),android.R.layout.simple_list_item_1, datos);


                lstClientes.setAdapter(adaptador);
            }
            else
            {
                alertDialog3 = new AlertDialog.Builder( getActivity());
                alertDialog3.setTitle("Confirmar atecion");
                alertDialog3.setMessage("No se encontro el servidor");
                alertDialog3.show();
            }
            lstClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, final int posisicon,
                                        long id) {
                    // TODO Auto-generated method stub
                    final ListAdapter la = (ListAdapter) arg0.getAdapter();
                    Toast.makeText(
                            arg1.getContext()
                            ,"cliente :"+la.getItem(posisicon).toString()
                            ,Toast.LENGTH_LONG)
                            .show();

                    alertDialog3 = new AlertDialog.Builder( getActivity());
                    alertDialog3.setTitle("Confirmar ");
                    alertDialog3.setMessage("Desear aceptar reto?");

                    alertDialog3.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                            Intent in =new Intent(getActivity(),Ruleta.class);
                            startActivity(in);


                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog3.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

                    alertDialog3.show();
                }
            });

            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    textlength = et.getText().length();
                    array_sort.clear();

                    for (int i = 0; i < datos.length; i++) {
                        if (textlength <= datos[i].length()) {
                            if (et.getText().toString().equalsIgnoreCase((String)datos[i].subSequence(0, textlength))) {
                                array_sort.add(datos[i]);
                            }
                        }
                    }

                    lstClientes.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array_sort));

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

}
