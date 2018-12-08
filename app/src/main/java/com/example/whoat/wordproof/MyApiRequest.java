package com.example.whoat.wordproof;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MyApiRequest extends AsyncTask<String,Integer,String> {
    final String app_id = "0eb99713";
    final String app_key = "d399079a0bbdadee2d55734d0fcb9fba";
    String newUrl;
    Context c;

    MyApiRequest(Context context) {
        //thi
        c = context;
    }
@Override
    protected String doInBackground(String...params) {
    newUrl = params[0];
    try {
        URL url = new URL(newUrl);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setRequestProperty("app_id",app_id);
        urlConnection.setRequestProperty("app_key",app_key);

        // read the output from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }

        return stringBuilder.toString();

    }
    catch (Exception e) {
        e.printStackTrace();
        return e.toString();
    }
}
@Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
    }
}
