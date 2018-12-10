package com.example.whoat.wordproof;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyApiRequest extends AsyncTask<String, Integer, String> {
    Context c;
    EditText e1;
    TextView t2;
    MyApiRequest(Context co, TextView t) {
        c = co;
        t2 = t;
    }
    @Override
    protected String doInBackground(String... params) {

        
        final String app_id =  "0eb99713";
        final String app_key = "d399079a0bbdadee2d55734d0fcb9fba";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }

            return stringBuilder.toString();

        }
        catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        String def;
        try {
            JSONObject js = new JSONObject(result);
            JSONArray results = js.getJSONArray("results");
            JSONObject lentries = results.getJSONObject(0);

            JSONArray la = lentries.getJSONArray("lexicalEntries");
            JSONObject entries = la.getJSONObject(0);

            JSONArray e = entries.getJSONArray("entries");
            JSONObject senses = e.getJSONObject(0);

            JSONArray s = senses.getJSONArray("senses");
            JSONObject d = s.getJSONObject(0);

            JSONArray de = d.getJSONArray("definitions");
            def = de.getString(0);

            t2.setText(def);
            Toast.makeText(c,def,Toast.LENGTH_SHORT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
