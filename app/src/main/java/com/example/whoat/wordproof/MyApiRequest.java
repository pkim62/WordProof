package com.example.whoat.wordproof;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyApiRequest extends AsyncTask<String, Integer, String> {
    TextView t2;
    EditText e2;
    MyApiRequest(TextView t, EditText e) {
        t2 = t;
        e2 = e;
    }
    // Needed api example to help me do this from Oxford Api, very confusing to make without help
    //Specific way Oxford Api wants the request
    // (https://developer.oxforddictionaries.com/documentation#!/Dictionary32entries/get_entries_source_lang_word_id)
    @Override
    protected String doInBackground(String... params) {
        final String myAppId =  "0eb99713";
        final String myAppKey = "d399079a0bbdadee2d55734d0fcb9fba";
        try {
            URL myURL = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) myURL.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",myAppId);
            urlConnection.setRequestProperty("app_key",myAppKey);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }
            return stringBuilder.toString();
        }
        catch (Exception e) {
            String a = e2.getText().toString() + " is not a real word, Please try again!";
            t2.setText(a);
            e.printStackTrace();
            return e.toString();
        }
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String realWord = e2.getText().toString() + " is a Real Word and the Definition is: ";
        String def;
        try {
            JSONObject js = new JSONObject(s);
            JSONArray results = js.getJSONArray("results");
            JSONObject lentries = results.getJSONObject(0);

            JSONArray la = lentries.getJSONArray("lexicalEntries");
            JSONObject entries = la.getJSONObject(0);

            JSONArray e = entries.getJSONArray("entries");
            JSONObject senses = e.getJSONObject(0);

            JSONArray se = senses.getJSONArray("senses");
            JSONObject d = se.getJSONObject(0);

            JSONArray de = d.getJSONArray("definitions");
            def = de.getString(0);

            t2.setText(realWord + def);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
