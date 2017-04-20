package com.example.gabee1000.myapplication.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gabee1000.myapplication.Listeners.OnTaskCompleted;

/**
 * Created by gabee1000 on 2017. 04. 20..
 */

public class DatabaseLoaderTask extends AsyncTask<Integer, Integer, Boolean> {
    private final int MAX_INT = 42;
    private final OnTaskCompleted listener;
    private Context context;
    private ProgressDialog progressDialog;

    public DatabaseLoaderTask(Context context, OnTaskCompleted listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Adatbázis betöltése");
        progressDialog.setMessage("Töltés...");
        progressDialog.setMax(100);
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        boolean success = false;

        for (int i = 0; i <= MAX_INT; i++) {
            publishProgress((int) (((float) i / MAX_INT) * 100));
            try {
                Thread.sleep(500 / (i + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        success = true;
        return success;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        listener.onTaskCompleted("Sikeres betöltés!");
        progressDialog.dismiss();
    }


}
