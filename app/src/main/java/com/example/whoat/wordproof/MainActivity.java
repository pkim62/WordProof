package com.example.whoat.wordproof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    EditText e1;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView)findViewById(R.id.txtOutput);
        e1 = (EditText)findViewById((R.id.input));
    }
    public void requestApiOnClick(View v) {
        MyApiRequest myApiRequest = new MyApiRequest(this, t1);
        myApiRequest.execute(dictionaryEntries());
    }
    private String inflections() {
        final String language = "en";
        final String word = e1.getText().toString();
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/inflections/" + language + "/" + word_id;
    }
    private String dictionaryEntries() {
        final String language = "en";
        final String word = e1.getText().toString();
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }
}
