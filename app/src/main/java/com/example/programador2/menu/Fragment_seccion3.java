package com.example.programador2.menu;

/**
 * Created by Programador2 on 07/05/2016.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.widget.Toast;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_seccion3 extends Fragment {

     public ListView  lstClientes;
    public String gen;
    //private Contador cont = new Contador();
    //public static Activity activityasesores;
    public int Idpasar;
    public AlertDialog.Builder alertDialog3;
    public TextView textcliente;
    public String comprobar = "";
    public String user = "";
    String textElements;
    String dos;
    String listadoconta;
    //public String tipousuario="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view3 = inflater.inflate(R.layout.fragment_seccion3, container, false);

        //final Bundle bundle = getIntent().getExtras();
        //tipousuario=bundle.getString("tipousuario");
        try {
            lstClientes=(ListView)view3.findViewById(R.id.lstasesores);
            textcliente  =(TextView) view3.findViewById(R.id.lblcliente);


            TareaWSConsulta3 tarea3 = new TareaWSConsulta3();
            tarea3.execute();

        } catch (Exception e) {

            alertDialog3 = new AlertDialog.Builder(getActivity());
            alertDialog3.setTitle("Confirmar atecion3");
            alertDialog3.setMessage("3 "+e.toString());
            alertDialog3.show();
        }

        //  "Inflamos" el archivo XML correspondiente a esta sección.
        return view3;
    }


    private class TareaWSConsulta3 extends AsyncTask<String,Integer,Boolean> {

        private Cliente[] listaClientes;


        protected Boolean doInBackground(String... params) {

            boolean resul = true;

           variables var = new variables();
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "GetUsuariosAll";
            final String SOAP_ACTION = "http://tempuri.org/GetUsuariosPuntuajeAltos";
            //final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


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
               // for (int i = 0; i < 3; i++)
                {
                    SoapObject ic = (SoapObject)resSoap.getProperty(i);
                    Cliente cli = new Cliente();
                    cli.Id = Integer.parseInt(ic.getProperty(0).toString());;
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

            if (result==true)
            {
                final String[] datos = new String[listaClientes.length];
                for(int i=0; i< listaClientes.length; i++)
                    //datos[i]= Integer.toString(listaClientes[i].id).toString();
                    // (listaClientes[i].id);
                {
                    datos[i] =  listaClientes[i].Aseguradora.toString()+  "-" + (Integer.toString(listaClientes[i].Id).toString());

                }

                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<String>( getActivity(),
                                android.R.layout.simple_list_item_1, datos );
                lstClientes.setAdapter(adaptador);
            }
            else
            {
                alertDialog3 = new AlertDialog.Builder( getActivity());
                alertDialog3.setTitle("Confirmar atecion");
                alertDialog3.setMessage("No se encontro el servidor");
                alertDialog3.show();
            }
            lstClientes.setOnItemClickListener(new OnItemClickListener() {

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
                    alertDialog3.setTitle("Confirmar atecion");
                    alertDialog3.setMessage("Desear verificar esta la lista del asesor");

                    alertDialog3.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {




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
        }
    }
}





