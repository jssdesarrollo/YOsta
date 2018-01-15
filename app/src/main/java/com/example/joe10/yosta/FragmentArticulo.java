package com.example.joe10.yosta;
import android.os.Bundle;
import android.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by joe10 on 11/06/2016.
 */
public class FragmentArticulo extends Fragment {
    public static String ARG_ID_ENTRADA_TITULO ="";
    public static String ARG_ID_ENTRADA_ARTICULO = "";

    public FragmentArticulo() {

        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_articulo, container, false);
        Bundle bundle = getArguments();
        ARG_ID_ENTRADA_TITULO = bundle.getString("tit");
        ARG_ID_ENTRADA_ARTICULO = bundle.getString("cad");
        TextView titulo = (TextView) v.findViewById(R.id.titulo);
        titulo.setText(ARG_ID_ENTRADA_TITULO);
        TextView texto = (TextView) v.findViewById(R.id.articulo);
        texto.setText(ARG_ID_ENTRADA_ARTICULO);
        texto.setMovementMethod(new ScrollingMovementMethod());

        // Inflate the layout for this fragment
        return v;
    }

}