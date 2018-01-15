package com.example.joe10.yosta;

import android.app.ProgressDialog;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by joe10 on 03/07/2016.
 */

public class AsyncTaskProgress extends MyAsyncTask {
    ProgressDialog progress;
    MainActivity act;

    public AsyncTaskProgress(ProgressDialog progress, MainActivity act) {
        this.progress = progress;
        this.act = act;
    }
    @Override
    public void onPreExecute() {
        progress.show();
    }
    @Override
    protected Void doInBackground(Void... params) {
        super.doInBackground();
        return null;
    }
    @Override
    public void onPostExecute(Void aVoid) {
        progress.dismiss();
        FragmentComunicados fragmento = new FragmentComunicados();
        act.getFragmentManager().beginTransaction()
                .replace(R.id.lay, fragmento)
                .addToBackStack(null)
                .commit();
        act.getSupportActionBar().setTitle("Comunicados Osta Yudigar");
    }


}
