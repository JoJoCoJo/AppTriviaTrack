package com.example.programador2.menu;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
/**
 * Created by Programador2 on 07/05/2016.
 */
public class Login extends AppCompatActivity {
    public TextView lblMensaje;
    public EditText txtUsuario;
    public EditText txtPassword;
    public  Button btnaceptar;
    public final int dialogo_alert=0;
    public String msje="";
    public boolean  ms;
    public String re="false";
    public String cerrar="";
    public String usuario="";
    public String pass="";
    public  Button btnRegistrarse;

    public int oIdUsuario ;
    public String oNombre ;
    public String oApellido ;
    public String oFecha ;
    public String oUsuario ;
    public String oPassword ;


    //  final String NAMESPACE = "http://tempuri.org/";
    //  final String URL="http://192.168.1.95/AtencionCliente/WebServices/Procesos.asmx";
    // http://192.168.1.5/WebSiteQS/WebSite2/Service.asmx
    //final String METHOD_NAME = "InicioSession";
    String tipousuario;
    // final String SOAP_ACTION = "http://tempuri.org/InicioSession";
    //  final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    //public static Activity loginA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // cargarpreferencias();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmlogin);


       String u= ((MyApplication) this.getApplication()).getVaUsuario();
       String p= ((MyApplication) this.getApplication()).getVaPassword();




        txtUsuario = (EditText)findViewById(R.id.txtUsuario );
        txtPassword = (EditText)findViewById(R.id.txtContra);
        btnaceptar = (Button )findViewById(R.id.btnAceptarLogin);
        btnRegistrarse=(Button)findViewById(R.id.btnEnviarRegistrase);


     /*
      try {

          if (u!=null) {
              usuario = u;
              pass = p;
              TareaWlogeado taw = new TareaWlogeado();
              taw.execute();
          }
      }
      catch (Exception e)
      {
          Toast.makeText(getBaseContext(), "error  "+e, Toast.LENGTH_SHORT).show();

      }
      */

        btnaceptar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               usuario= txtUsuario.getText().toString().trim();
                pass=txtPassword.getText().toString().trim();
               TareaWSConsulta tarea = new TareaWSConsulta();
                 tarea.execute();

              /*  Intent in2 = new Intent(Login.this ,MainActivity.class);
                Toast.makeText(getBaseContext(), "Bienvenido",Toast.LENGTH_SHORT).show();
                startActivity(in2 );*/
            }
        });

        btnRegistrarse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent in2 = new Intent(Login .this ,Registro.class);
               // Toast.makeText(getBaseContext(), "Bienvenido",Toast.LENGTH_SHORT).show();
                startActivity(in2 );
            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void cargarpreferencias()
    {
        SharedPreferences mi = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);
       String us =(mi.getString("usupre",""));
      String pa=(mi.getString("passpre",""));



        if (pa!=null  ) {
            if (pa.trim()!=""  ) {
                usuario = us;
                pass = pa;
                TareaWlogeado taw = new TareaWlogeado();
                taw.execute();
            }
        }


    }
    public void guardarpreferencias()
    {
        SharedPreferences mi = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = mi.edit();


        editor.putString("usupre",txtUsuario.getText().toString());
        editor.putString("passpre",txtPassword.getText().toString());
        editor.commit();
    }

    private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {


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

                    Toast.makeText(getBaseContext(),oIdUsuario+oNombre+oApellido+oFecha+oUsuario+oPassword+ "Bienvenido",Toast.LENGTH_SHORT).show();


                    ((MyApplication)getApplication()).setVaUsuario(oUsuario);
                    ((MyApplication)getApplication()).setVaPassword(oPassword);
                   // String s = ((MyApplication) this.getApplication()).getSomeVariable();

guardarpreferencias();
                        Toast.makeText(getBaseContext(), "Bienvenido",Toast.LENGTH_SHORT).show();
                         Intent in = new Intent (Login.this ,MainActivity.class);


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

                    Toast.makeText(getBaseContext(),oIdUsuario+oNombre+oApellido+oFecha+oUsuario+oPassword+ "Bienvenido",Toast.LENGTH_SHORT).show();


                    // String s = ((MyApplication) this.getApplication()).getSomeVariable();


                    Toast.makeText(getBaseContext(), "Bienvenido",Toast.LENGTH_SHORT).show();
                    Intent in = new Intent (Login.this ,MainActivity.class);


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


