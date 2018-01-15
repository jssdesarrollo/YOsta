package com.example.joe10.yosta;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static String[] fechas;
    public static String[] titulos;
    public static String[] textos;
    public  Menu myMenu;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putStringArray("fechasGuardadas",fechas);
        savedInstanceState.putStringArray("titulosGuardados", titulos);
        savedInstanceState.putStringArray("textosGuardados", textos);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            fechas = savedInstanceState.getStringArray("fechasGuardadas");
            titulos = savedInstanceState.getStringArray("titulosGuardados");
            textos = savedInstanceState.getStringArray("textosGuardados");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"ostayudigar@gmail.com"});
                startActivity(Intent.createChooser(emailIntent, "Envía tus dudas o sugerencias"));
                Snackbar.make(view, "Contacto: ostayudigar@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            getSupportActionBar().setTitle("");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        myMenu = menu;
        getMenuInflater().inflate(R.menu.main, myMenu);
        myMenu.setGroupVisible(0,false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Actualizando...");
            AsyncTaskProgress Tarea = new AsyncTaskProgress(progress, this);
            Tarea.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    // con este método controlamos la acción con cada botón pulsado
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        int id = item.getItemId();
        FragmentComunicados fragmento = new FragmentComunicados();

        //para el botón de comunicados la actividad debe ser diferente al resto, porque FragmentComunicados es de tipo ListFragment
        // y no un Fragment como en los demás.
        if (id == R.id.nav_comunicados) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.lay, fragmento)
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle(item.getTitle());
            //Hacemos visible el botón refrescar
            myMenu.setGroupVisible(0,true);

        } else if (id == R.id.nav_calcariñena) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_calcariñena));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        }else if (id == R.id.nav_calzaragoza) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_calzaragoza));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        }else if (id == R.id.nav_calfinde) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_calfinde));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        }else if (id == R.id.nav_tablassalariales) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_tablassalariales));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        } else if (id == R.id.nav_pacto) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_pacto));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        } else if (id == R.id.nav_permisos) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_permisos));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        }
        else if (id == R.id.nav_enfermedad) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_enfermedad));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        }
        else if (id == R.id.nav_accidente) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_accidentes));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        }
        else if (id == R.id.nav_telefonos) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_telefonos));
            fragment.setArguments(arguments);
            fragmentTransaction = true;
        }
        else if (id == R.id.nav_rutasbus) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_rutasbus));
            fragment.setArguments(arguments);
            fragmentTransaction = true;
        }
        else if (id == R.id.nav_sobrenosotros) {
            fragment = new FragmentPDF();
            Bundle arguments = new Bundle();
            arguments.putString("cad", getString(R.string.ruta_sobrenosotros));
            fragment.setArguments(arguments);
            fragmentTransaction = true;

        }
        else if (id==R.id.nav_facebook){
            openWeb("https://www.facebook.com/yudigar.osta?fref=ts");
        }
        else if (id==R.id.nav_web){
            openWeb("http://www.osta.es");
        }
        else if (id==R.id.nav_version){
            AcercaDe.create(this).show();

        }

        if(fragmentTransaction) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.lay, fragment)
                    .addToBackStack("eso")
                    .commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
            myMenu.setGroupVisible(0,false);// Si se marca una opción distinta a nav_comunicados, se quita el botón de refrescar
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //Método para comprobar la conexión a internet.
    public static boolean compruebaConexion(Context context) {
        boolean connected = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

    // Método para abrir el face de Osta
    public void openWeb(String link)
    {
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW,Uri.parse(link));
        startActivity(intent);
    }
    // Clase estática para el diálogo Acerca De
    public static class AcercaDe {

        public static AlertDialog create(Context context) {
            final TextView message = new TextView(context);
            final SpannableString s =
                    new SpannableString("www.jssdesarrollo.com");
            Linkify.addLinks(s, Linkify.WEB_URLS);
            message.setText("\n");
            message.append("      Juan Antonio Sanjuán Segura");
            message.append("\n");
            message.append("      ");
            message.append(s);
            message.setMovementMethod(LinkMovementMethod.getInstance());

            return new AlertDialog.Builder(context)
                    .setTitle("YOsta 1.0")
                    .setCancelable(true)
                    .setIcon(R.drawable.masqnunca)
                    .setPositiveButton("Ok", null)
                    .setView(message)
                    .create();
        }
    }

}


