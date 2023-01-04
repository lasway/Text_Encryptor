package com.ism.textencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText input_text;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_text = findViewById(R.id.input_text);
        Button CeaserButton = findViewById(R.id.CeaserButton);
        Button ROTbutton = findViewById(R.id.ROTbutton);
        CeaserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encrypt();
            }
        });
        ROTbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encrypt();
            }
        });

    }

    public void encrypt(){
        Intent i = new Intent(this, CeaserAlgorithms.class);
        str = input_text.getText().toString();
        i.putExtra("input", str);
        startActivity(i);
    }
    public void Encrypt(){
        Intent i = new Intent(this, ROT13Algoritthm.class);
        str = input_text.getText().toString();
        i.putExtra("input", str);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {

    }
}