package com.example.programador2.menu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {
    private Adaptador_ViewPagerPrincipal Adaptador_ViewPagerPrincipal;
  private ViewPager ViewPager;
    public  String odi="hola soy ody";
    public  String Nombre="";
    Toolbar toolbar;
    FrameLayout statusBar;
    ImageView imageViewToogle, imageViewCover, imageViewPicture;
    ToggleButton toggleButtonDrawer;
    LinearLayout linearLayoutMain, linearLayoutSecond;
    RelativeLayout relativeLayoutDrawerTexts, relativeLayoutChooseTheme, relativeLayoutSettings;
    Intent intent;
    DrawerLayout mDrawerLayout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        Nombre= bundle.getString("nombre");
       ((MyApplication) this.getApplication()).setSomeVariable(Nombre);
        String s = ((MyApplication) this.getApplication()).getSomeVariable();

       // Toast.makeText(getBaseContext(), "Bienvenido" +s,Toast.LENGTH_SHORT).show();



        //Setup Navigation Drawer
       // navigationDrawer();

        // Setup drawer accounts toggle.
      //  toogleButtonDrawer();

        // Open settings method
      //  openSettings();
        // Iniciamos la barra de herramientas.
        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarPrincipal);
        setSupportActionBar(toolbar);

        // Iniciamos la barra de tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.TabLayoutPrincipal);



    //toolbarStatusBar();
    //toogleButtonDrawer();


        // Añadimos las 3 tabs de las secciones.
        // Le damos modo "fixed" para que todas las tabs tengan el mismo tamaño. También le asignamos una gravedad centrada.
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



        // Iniciamos el viewPager.
        ViewPager = (ViewPager) findViewById(R.id.ViewPagerPrincipal);
        // Creamos el adaptador, al cual le pasamos por parámtro el gestor de Fragmentos y muy importante, el nº de tabs o secciones que hemos creado.
        Adaptador_ViewPagerPrincipal = new Adaptador_ViewPagerPrincipal(getSupportFragmentManager(),tabLayout.getTabCount());
        // Y los vinculamos.
        ViewPager.setAdapter(Adaptador_ViewPagerPrincipal);

        // Y por último, vinculamos el viewpager con el control de tabs para sincronizar ambos.
        tabLayout.setupWithViewPager(ViewPager);
        }
        catch (Exception e)
        {
            Toast.makeText(getBaseContext(), "Error "+ e , Toast.LENGTH_SHORT).show();

        }
    }
    public String getMyData( ) {

        return Nombre ;
    }
    public void toogleButtonDrawer() {
        imageViewToogle = (ImageView) findViewById(R.id.imageViewToggle);
        toggleButtonDrawer = (ToggleButton) findViewById(R.id.toggleButtonDrawer);
        linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayoutMain);
        linearLayoutSecond = (LinearLayout) findViewById(R.id.linearLayoutSecond);
        toggleButtonDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toggleButtonDrawer.isChecked()) {
                    toggleButtonDrawer.setChecked(false);
                    imageViewToogle.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_action_navigation_arrow_drop_down));
                    linearLayoutMain.setVisibility(View.VISIBLE);
                    linearLayoutSecond.setVisibility(View.GONE);
                }
                if (toggleButtonDrawer.isChecked()) {
                    toggleButtonDrawer.setChecked(true);
                    imageViewToogle.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_action_navigation_arrow_drop_up));
                    linearLayoutMain.setVisibility(View.GONE);
                    linearLayoutSecond.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void openSettings() {
        relativeLayoutSettings = (RelativeLayout) findViewById(R.id.relativeLayoutSettings);
        relativeLayoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after some time
                        mDrawerLayout.closeDrawers();
                    }
                }, 500);
            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_post clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.about_dialog);
            dialog.show();
            return true;
        }
        if (id == R.id.action_cerrar) {

            SharedPreferences mi = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mi.edit();


            editor.putString("usupre",null);
            editor.putString("passpre",null);
            editor.commit();

            Intent ds = new Intent(MainActivity.this,Login.class);
            startActivity(ds);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void toolbarStatusBar() {

        // Cast toolbar and status bar
        statusBar = (FrameLayout) findViewById(R.id.statusBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Get support to the toolbar and change its title
        setSupportActionBar(toolbar);
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.programador2.menu/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.programador2.menu/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
