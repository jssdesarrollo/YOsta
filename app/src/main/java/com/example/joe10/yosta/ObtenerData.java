package com.example.joe10.yosta;
import android.content.res.Resources;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;
/*
Con esta clase obtenemos del servidor los datos necesarios para el apartado de comunicados. El fichero data.dat se compone de líneas
con tres campos separados por comas, que comprenden la fecha en formato aaammdd, el título del comunicado y el nombre del fichero.
Los dos primeros los devolvemos en el array contents2 conjuntamente y por ahora no los separamos, con el método
 obtenerArrayTituloFecha. El tercero lo extraemos en el método ObtenerArrayTextos, ya que lo utilizamos en la ruta del fichero
 de comunicados a leer (coinciden).
 */
public class ObtenerData{
    String[] contents;
    String[] contents2;
    //String rutaComunicados = Resources.getSystem().getString(R.string.ruta_comunicados);
    //String rutaData = Resources.getSystem().getString(R.string.ruta_data);
     int a = 0;

    public ObtenerData() {
        try {
            URLConnection conn = new URL("http://jssdesarrollo.com/osta/data.dat").openConnection();
            InputStream in = conn.getInputStream();
            contents = convertirStreamArray(in);
            for (int x=0; contents[x]!=null;x++)
                a=x+1;
            contents2 = new String[a];
            for (int y=0; contents[y]!=null;y++)
                this.contents2[y]=contents[y];

        } catch (MalformedURLException e) {
            Log.v("MALFORMED URL EXCEPTION","contents");
        } catch (IOException e) {
            Log.e(e.getMessage(), "error");
        }

    }
    public String[] obtenerArrayTextos(){
        String[] textos = new String[contents2.length];
        for (int i=0; i<contents2.length; i++){
            StringTokenizer st = new StringTokenizer(contents2[i],",");
            st.nextToken();st.nextToken();
            String nombreFichero = st.nextToken();
            if (nombreFichero.startsWith("pdf")==true){
                Log.e("fichero pdf:",nombreFichero);
                textos[i] = nombreFichero;

            } else{
                try {
                    URL url = new URL("http://jssdesarrollo.com/osta/comunicados/" + nombreFichero + ".dat");
                    Log.e("Url" + i, url.toString());
                    URLConnection conn = url.openConnection();
                    InputStream is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String linea = null;
                    while ((linea = reader.readLine()) != null)
                        sb.append(linea + "\n");
                    textos[i] = sb.toString();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }

        return textos;
    }

    public String[] obtenerArrayFechas(){
        String[] fechas = new String[contents2.length];

        for (int i=0; i<contents2.length; i++){
            StringTokenizer st = new StringTokenizer(contents2[i],",");
            fechas[i]= st.nextToken();
        }
        return fechas;
    }

    public String[] obtenerArrayTitulos(){
        String[] titulos = new String[contents2.length];

        for (int i=0; i<contents2.length; i++){
            StringTokenizer st = new StringTokenizer(contents2[i],",");
            st.nextToken();
            titulos[i]= st.nextToken();
        }
        return titulos;
    }




    private static String[] convertirStreamArray(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        //StringBuilder sb = new StringBuilder();
        String [] arrayContenidos = new String[100];
        int i=0;

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                arrayContenidos[i]=line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayContenidos;
    }


}
