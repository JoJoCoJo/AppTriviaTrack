package com.example.programador2.menu;

/**
 * Created by Programador2 on 07/05/2016.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import java.util.ArrayList;


public class Fragment_seccion2 extends Fragment {

    private Runnable r;
    public ListView  lstClientes;
    public String gen;
    private Handler h = new Handler();
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
    String Id_Usuario="";
    String Id_Partida="";
    EditText et;
    int textlength = 0;
    private ArrayList<String> array_sort = new ArrayList<String>();
    ArrayAdapter<String> adaptador;
    private String listview_array[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN" };
    private String datos[];
    private String datos2[];
  //  public String[] datos;
    //public String tipousuario="";
  public String msje="";
    public boolean  ms;
    public String re="";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  "Inflamos" el archivo XML correspondiente a esta sección.
        View view2 = inflater.inflate(R.layout.fragment_seccion2, container, false);


        try {
            lstClientes=(ListView)view2.findViewById(R.id.listretoss);
            textcliente  =(TextView) view2.findViewById(R.id.lblretos);
            et = (EditText) view2.findViewById(R.id.editText01);
            Id_Usuario= ((MyApplication) this.getActivity().getApplication()).getSomeVariable();




            r = new Runnable() {
                @Override
                public void run() {

                    h.postDelayed(this, 2000);


                    if(et.getText().length()==0) {
                       TareaWSConsulta3 tarea3 = new TareaWSConsulta3();
                        tarea3.execute();
                    }
                    else
                    {

                    }

                }


            };
            h.post(r);






        } catch (Exception e) {

            alertDialog3 = new AlertDialog.Builder(getActivity());
            alertDialog3.setTitle("Confirmar ");
            alertDialog3.setMessage(" "+e.toString());
            alertDialog3.show();
        }
        return view2;
    }


    private class TareaWSConsulta3 extends AsyncTask<String,Integer,Boolean> {

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
            request.addProperty("Sol","p");

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
            datos = new String[listaClientes.length];
            datos2 = new String[listaClientes.length];
            if (result==true)
            {
                ArrayList<persona> arrayPersonas = new ArrayList<>();

                for(int i=0; i< listaClientes.length; i++)
                //datos[i]= Integer.toString(listaClientes[i].id).toString();
                // (listaClientes[i].id);
                {
                    //if(listaClientes[i].Solicitud.toString().trim().equals("p")) {
                        datos[i] = (listaClientes[i].retador.toString());

                  //  }
                }
                for(int i=0; i< listaClientes.length; i++)
                //datos[i]= Integer.toString(listaClientes[i].id).toString();
                // (listaClientes[i].id);
                {
                    //if(listaClientes[i].Solicitud.toString().trim().equals("p")) {
                    datos2[i] = (Integer.toString(listaClientes[i].IdPartida).toString() + "-" + listaClientes[i].retador.toString());
                    arrayPersonas.add(new persona(R.drawable.ic_todo, listaClientes[i].retador.toString(),28));
                    //  }
                }

                 adaptador = new ArrayAdapter<String>( getActivity(),android.R.layout.simple_list_item_1, datos);


               /* arrayPersonas.add(new persona(R.drawable.ic_todo,"Jesús Pallares",21));
                arrayPersonas.add(new persona(R.drawable.ic_todo,"Miguel Campos",28));
                arrayPersonas.add(new persona(R.drawable.ic_todo,"Fernando López",31));
                arrayPersonas.add(new persona(R.drawable.ic_todo,"Francisco Tirado",30));
                arrayPersonas.add(new persona(R.drawable.ic_todo,"Ángel Naranjo",39));
                arrayPersonas.add(new persona(R.drawable.ic_todo,"Luismi López",29));*/

                //Se instancia la clase adaptador personalizado
                ClienteAdapter adaptador2 = new ClienteAdapter( getActivity(),arrayPersonas);





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

                    String[] textElements =datos2[posisicon].trim().split("-");
                    Id_Partida=textElements[0];
                    alertDialog3 = new AlertDialog.Builder( getActivity());
                    alertDialog3.setTitle("Confirmar ");
                    alertDialog3.setMessage("Desear aceptar reto?");

                    alertDialog3.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                            TareaWSConsulta7 ta = new TareaWSConsulta7();
                            ta.execute();

                    //        Intent in =new Intent(getActivity(),Ruleta.class);
                      //      startActivity(in);


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
    private class TareaWSConsulta7 extends AsyncTask<String,Integer,Boolean> {


        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            variables var = new variables();
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "PartidaUpdate";
            final String SOAP_ACTION = "http://tempuri.org/PartidaUpdate";
            //final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            //   final SoapObject oUser = request.addProperty("oUser",usuario );
            request.addProperty("id_usuario",Id_Usuario );
            //request.addProperty("oPasword",nombre);
            request.addProperty("solicitud", "a");

            request.addProperty("Id_Partida",Id_Partida);




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

            if (result==true)
            {


            }
            else
            {
                alertDialog3 = new AlertDialog.Builder( getActivity());
                alertDialog3.setTitle("Confirmar atecion");
                alertDialog3.setMessage("No se encontro el servidor");
                alertDialog3.show();
            }


                }




    }
}
