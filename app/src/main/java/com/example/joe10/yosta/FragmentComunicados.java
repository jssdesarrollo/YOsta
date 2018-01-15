package com.example.joe10.yosta;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.app.ListFragment;
import android.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * Created by joe10 on 23/06/2016.
 */

public class FragmentComunicados extends ListFragment {
    //private ListView lstOpciones;
    String[] datos1;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_comunicados, container, false);
        return v;
    }
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        datos1 = MainActivity.fechas;
        String[] columnasBD = new String[] {"_id", "textoSuperior", "textoInferior"};
        MatrixCursor cursor = new MatrixCursor(columnasBD);
        for (int i=datos1.length-1;i>=0;i--){
            cursor.addRow(new Object[]{i,MainActivity.titulos[i], MainActivity.fechas[i]});
        }
        String[] desdeEstasColumnas = {"textoSuperior", "textoInferior"};
        int[] aEstasViews = { R.id.textView_superior, R.id.textView_inferior};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.entradalista, cursor, desdeEstasColumnas, aEstasViews, 0);
        getListView();
        setListAdapter(adapter);
        }
    public void onListItemClick(ListView lista, View view, int posicion, long id){
        Fragment fragment = null;
        if (MainActivity.textos[(int)id].startsWith("pdf")){
            Fragment fragmento = new FragmentPDF();
            Bundle arguments = new Bundle();
            Log.e("ruta comunicados: ",getString(R.string.ruta_comunicados)+ MainActivity.textos[(int) id]+".pdf");
            arguments.putString("cad",getString(R.string.ruta_comunicados)+ MainActivity.textos[(int) id]+".pdf");
            fragmento.setArguments(arguments);
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.lay, fragmento);
            ft.commit();
        }
        else {
            fragment = new FragmentArticulo();
            Bundle arguments = new Bundle();
            arguments.putString("cad", MainActivity.textos[(int) id]);
            arguments.putString("tit", MainActivity.titulos[(int) id]);
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
            .addToBackStack(null)
            .replace(R.id.lay, fragment)
            .commit();
        }

    }

}





