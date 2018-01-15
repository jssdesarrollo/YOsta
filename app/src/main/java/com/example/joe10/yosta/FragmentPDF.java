package com.example.joe10.yosta;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by joe10 on 11/06/2016.
 */
public class FragmentPDF extends Fragment {
    public static String ARG_ID_ENTRADA_CADENA = "";

    public FragmentPDF() {

        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendario, container, false);
        Bundle bundle = getArguments();
        ARG_ID_ENTRADA_CADENA = bundle.getString("cad");
        WebView browser = (WebView) v.findViewById(R.id.webView);

        //habilitamos javascript y el zoom
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setDisplayZoomControls(false);
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl("http://docs.google.com/gview?embedded=true&url="+ARG_ID_ENTRADA_CADENA);
        Log.d("cadena: ",ARG_ID_ENTRADA_CADENA);
        // Inflate the layout for this fragment
        return v;
    }


}
