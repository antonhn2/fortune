package com.example.cookieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button lookup = (Button) findViewById(R.id.button1);
        lookup.setOnClickListener(new View.OnClickListener() {
            public void  onClick(final View v) {
                TextView name = (TextView) findViewById(R.id.text);
                name.setText("this is an example fortune");
                TextView name2 = (TextView) findViewById(R.id.text2);
                name2.setText("23 13 24 46 36");
            }
        });
    }
}
