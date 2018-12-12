package com.example.whoat.wordproof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    EditText e1;
    TextView t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.txtOutput);
        e1 = findViewById((R.id.input));
        t3 = findViewById(R.id.outputPoint);
    }
    public void requestApiOnClick(View v) {
        MyApiRequest myApiRequest = new MyApiRequest(this, t1, e1);
        myApiRequest.execute(dictionaryEntries());
        t3.setText(points(e1.getText().toString()) + "\n" + "Number of Letters: " + numLetters());
    }
    private String dictionaryEntries() {
        final String word = e1.getText().toString();
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/" + word_id;
    }
    private int numLetters() {
        return e1.getText().toString().length();
    }
    private String points(String s) {
        if (s == null) {
            return "0";
        }
        Integer points = 0;
        s = s.toLowerCase();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'e') {
                points += 1;
            } else if (array[i] == 'a') {
                points += 1;
            } else if (array[i] == 'i') {
                points += 1;
            } else if (array[i] == 'o') {
                points += 1;
            } else if (array[i] == 'n') {
                points += 1;
            } else if (array[i] == 'r') {
                points += 1;
            } else if (array[i] == 'l') {
                points += 1;
            } else if (array[i] == 's') {
                points += 1;
            } else if (array[i] == 'u') {
                points += 1;
            } else if (array[i] == 't') {
                points += 1;
            } else if (array[i] == 'd') {
                points += 2;
            } else if (array[i] == 'g') {
                points += 2;
            } else if (array[i] == 'b') {
                points += 3;
            } else if (array[i] == 'c') {
                points += 3;
            } else if (array[i] == 'm') {
                points += 3;
            } else if (array[i] == 'p') {
                points += 3;
            } else if (array[i] == 'f') {
                points += 4;
            } else if (array[i] == 'h') {
                points += 4;
            } else if (array[i] == 'v') {
                points += 4;
            } else if (array[i] == 'w') {
                points += 4;
            } else if (array[i] == 'y') {
                points += 4;
            } else if (array[i] == 'k') {
                points += 5;
            } else if (array[i] == 'j') {
                points += 8;
            } else if (array[i] == 'x') {
                points += 8;
            } else if (array[i] == 'q') {
                points += 10;
            } else if (array[i] == 'z') {
                points += 10;
            } else {
                i++;
            }
        }
        return e1.getText().toString() + " is worth at least: " + points.toString() + " Scrabble points!";
    }
}
