package com.example.joe10.yosta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by joe10 on 30/06/2016.
 */

public class SplashActivity extends Activity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 3000; // 3 segundos
    public static boolean preparado = false;

    protected void onStart() {
        super.onStart();
        if (!MainActivity.compruebaConexion(this)) {
            Toast.makeText(getBaseContext(), "No hay conexión a internet ", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Tenemos una plantilla llamada splash.xml donde mostraremos la información que queramos (logotipo, etc.)
        setContentView(R.layout.splash);
        do {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    MyAsyncTask tarea = new MyAsyncTask();
                    tarea.execute();
                    try {
                        tarea.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    finish();
                }

                ;
            }, DURACION_SPLASH);
        } while (preparado = false);
    }

}
