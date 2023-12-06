package com.example.evaluaciont1_vl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.evaluaciont1_vl.datos.Utilities;

public class PantallaSeleccionAlumno extends AppCompatActivity implements View.OnClickListener {

    private EditText etAlumnoPestanna;
    private Button btnAceptar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_seleccion_alumno);

        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        etAlumnoPestanna = findViewById(R.id.etAlumnoPestanna);

        LinearLayout ll = findViewById(R.id.llAlumnos);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.weight = 3;

        for(String s : Utilities.getAlumnos()){
            Button button = new Button(this);

            button.setLayoutParams(lp);
            button.setText(s);
            button.setPadding(0,0,0,0);
            button.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            button.setTextSize(12);
            button.setTextColor(getColor(R.color.black));
            button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            button.setOnClickListener(this);
            ll.addView(button);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAceptar) {
            if (etAlumnoPestanna.getText().toString().isEmpty()) {
                new Toast(this).makeText(this, getString(R.string.erro_no_data), Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent();
                i.putExtra("alumno", etAlumnoPestanna.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        }else if(v.getId() == R.id.btnCancelar){
            setResult(RESULT_CANCELED);
            finish();
        }else{
            for(String s : Utilities.getAlumnos()){
                if(((Button)v).getText().equals(s)){
                    etAlumnoPestanna.setText(s);
                }
            }
        }
    }


}