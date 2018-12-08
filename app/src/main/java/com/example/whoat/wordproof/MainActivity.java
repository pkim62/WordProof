package com.example.whoat.wordproof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = dictionaryEntries();
    }
    public void requestApiOnClick(View v) {
        MyApiRequest myApiRequest = new MyApiRequest(this);
        myApiRequest.execute(url);
    }
    private String dictionaryEntries() {
        final String language = "en";
        final String word = "Eye";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/search/" + language + "?q=" + word_id;
    }
}
