package com.example.programador2.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by hpsamus on 14/08/2016.
 */
public class Cargar extends AppCompatActivity {
    public String usuario="";
    public String idusu="";
    public String pass="";
    public String msje="";
    public boolean  ms;
    public String re="false";


    public int oIdUsuario ;
    public String oNombre ;
    public String oApellido ;
    public String oFecha ;
    public String oUsuario ;
    public String oPassword ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        cargarpreferencias();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmcargar);
    }
    public void cargarpreferencias()
    {
        SharedPreferences mi = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);
        String us =(mi.getString("usupre",""));
        String pa=(mi.getString("passpre",""));
        String id=(mi.getString("idpre",""));



        if (pa!=null  ) {
            if (pa.trim()!=""  ) {
                usuario = us;
                pass = pa;
                idusu=id;
                TareaWlogeado taw = new TareaWlogeado();
                taw.execute();
            }
            else
            {
                Intent ew = new Intent(Cargar.this,SplashScreen.class);
                startActivity(ew);

            }
        }
        else
        {
            Intent ew = new Intent(Cargar.this,SplashScreen.class);
            startActivity(ew);
        }


    }

    private class TareaWlogeado extends AsyncTask<String,Integer,Boolean> {


        private  vaUsuarios listausuario[];
        protected Boolean doInBackground(String... params) {

            boolean resul = true;
            variables var = new variables();
            // http:localhost:1610/Service.asmx
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "inicio";
            final String SOAP_ACTION = "http://tempuri.org/inicio";
            final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            // Variables var = new Variables();

            final SoapObject oUser = request.addProperty("oUser",usuario );
            request.addProperty("oPasword", pass);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapObject resSoap =(SoapObject)envelope.getResponse();
                listausuario = new vaUsuarios[resSoap.getPropertyCount()];



                for(int i=0;i< listausuario.length;i++)
                {
                    vaUsuarios vausu = new vaUsuarios();
                    SoapObject ic = (SoapObject)resSoap.getProperty(i);
                    vausu.IdUsuario=Integer.parseInt(ic.getProperty(0).toString());
                    vausu.Nombre=ic.getProperty(1).toString();
                    vausu.Apellido=ic.getProperty(2).toString();
                    vausu.Fecha=ic.getProperty(3).toString();
                    vausu.Usuario =ic.getProperty(4).toString();
                    vausu.Password =ic.getProperty(5).toString();
                    listausuario[i]=vausu;
                }
                if(listausuario.length>0)
                {
                    re = "true";
                    // msje = re;
                }

                if (re.equals(""))
                    resul = false;
            } catch (Exception e) {

                resul = false;
            }

            return resul;
        }


        protected void onPostExecute(Boolean result) {
            // Toast.makeText(getBaseContext(), re, Toast.LENGTH_SHORT).show();
            if (result == false) {
                Toast.makeText(getBaseContext(), "No se encontro el servidor ;) ", Toast.LENGTH_SHORT).show();
            }
            if (result == true) {



                for(int i=0; i<listausuario.length; i++)
                //datos[i]= Integer.toString(listaClientes[i].id).toString();
                // (listaClientes[i].id);
                {
                    // oid = listaClientes[i].Id;


                    oIdUsuario = listausuario[i].IdUsuario;
                    oNombre = listausuario[i].Nombre.toString();
                    oApellido = listausuario[i].Apellido.toString();
                    oFecha = listausuario[i].Fecha.toString();
                    oUsuario = listausuario[i].Usuario.toString();
                    oPassword=listausuario[i].Password.toString();



                }
                if (re.equals("false")) {
                    Toast.makeText(getBaseContext(), "Usuario o Contraseña invalidos", Toast.LENGTH_SHORT).show();
                }
                if (re.equals("true")) { variables va = new variables();



                    // String s = ((MyApplication) this.getApplication()).getSomeVariable();


                    Toast.makeText(getBaseContext(), "Bienvenido",Toast.LENGTH_SHORT).show();
                    Intent in = new Intent (Cargar.this ,MainActivity.class);


                    in.putExtra("nombre",Integer.toString ( oIdUsuario));



                       /*     in.putExtra("detallecontador", "d");
                            in.putExtra("listadocontador", "d");
                            txtPassword.setText("");
                            txtUsuario.setText("");*/
                    startActivity(in );

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
