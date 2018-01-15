package com.example.joe10.yosta;

/**
 * Created by joe10 on 26/06/2016.
 * Con esta clases programamos las tareas asíncronas que realiza la aplicación. Son aquellas que requieren conexión remota, a través
 * de la clase ObtenerData. Disparamos la tarea en el OnStart de la actividad principal, para consultar los datos que necesitamos
 * del servidor nada más iniciarse la aplicación.
 */
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    String[] titulos;
    String[] textos;
    String[] fechas;

    @Override
    protected Void doInBackground(Void... arg0) {
        ObtenerData lecturaDeDatos = new ObtenerData();
        textos = lecturaDeDatos.obtenerArrayTextos();
        /*for (int i=0; i<textos.length; i++)
            Log.e("Texto"+i,textos[i]);
        Log.e("Prueba ejecucion","OK");
        Log.e("Longitud array textos:"," "+textos.length);*/
        MainActivity.textos = textos;

        fechas = lecturaDeDatos.obtenerArrayFechas();
        /*for (int i=0; i<fechas.length; i++)
            Log.e("Fecha",fechas[i]);
        Log.e("Prueba ejecucion","OK");
        Log.e("Longitud array fechas:"," "+fechas.length);*/
        MainActivity.fechas = fechas;

        titulos = lecturaDeDatos.obtenerArrayTitulos();
        /*for (int i=0; i<titulos.length; i++)
            Log.e("Titulo",titulos[i]);
            Log.e("Prueba ejecucion","OK");
            Log.e("Longitud array titulos:"," "+titulos.length);*/
        MainActivity.titulos = titulos;
        SplashActivity.preparado= true;
        return null;

    }


}
