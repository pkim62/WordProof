package com.example.whoat.wordproof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.txtOutput);
        e1 = findViewById((R.id.input));
    }
    public void requestApiOnClick(View v) {
        MyApiRequest myApiRequest = new MyApiRequest(this, t1, e1);
        myApiRequest.execute(dictionaryEntries());
    }
    private String dictionaryEntries() {
        final String word = e1.getText().toString();
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/" + word_id;
    }
}
