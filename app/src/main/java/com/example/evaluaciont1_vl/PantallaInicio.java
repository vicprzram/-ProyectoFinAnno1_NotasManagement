package com.example.evaluaciont1_vl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PantallaInicio extends AppCompatActivity implements View.OnClickListener{

    Button btnRegistro;
    Button btnConsulta;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        btnRegistro = findViewById(R.id.btnRegistrar);
        btnConsulta = findViewById(R.id.btnConsultar);
        ivLogo = findViewById(R.id.ivUE);

        btnConsulta.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if(v.getId() == R.id.btnRegistrar){
            i = new Intent(this, PantallaRegistro.class);
            startActivity(i);
        }else if(v.getId() == R.id.btnConsultar){
            i = new Intent(this, PantallaConsulta.class);
            startActivity(i);
        }

    }
}