package com.example.whoat.wordproof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wire button to do stuff
        //gets button
        Button btn = (Button) findViewById(R.id.Action);
        //lets do stuff with button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "this a magic log message!");
                Toast.makeText(getApplicationContext(), "yes or no", Toast.LENGTH_SHORT);
            }
        });
    }
}
