package com.example.evaluaciont1_vl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.evaluaciont1_vl.datos.Utilities;

import java.util.List;

public class PantallaSeleccionAlumno extends AppCompatActivity implements View.OnClickListener {

    private static final String CALVE_ALUMNO_SELECCIONADO = "alumno_seleccionado";

    private EditText etAlumnoPestanna;
    private Button btnAceptar, btnCancelar;

    private int ROTATION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_seleccion_alumno);

        ROTATION = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();

        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        etAlumnoPestanna = findViewById(R.id.etAlumnoPestanna);




        LinearLayout ll = findViewById(R.id.llAlumnos);
        LinearLayout lP = findViewById(R.id.llPrimeros);
        LinearLayout lS = findViewById(R.id.llSegundos);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.weight = 1;


        switch (this.ROTATION){
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                for (String s : Utilities.getAlumnos()) {
                    Button button = new Button(this);

                    button.setLayoutParams(lp);
                    button.setText(s);
                    button.setPadding(0, 0, 0, 0);
                    button.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    button.setTextSize(12);
                    button.setTextColor(getColor(R.color.black));
                    button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
                    button.setOnClickListener(this);
                    ll.addView(button);
                }
                break;
            case Surface.ROTATION_90:
            default:
                for(int i = 0; i < 11; i++){
                    Button button = new Button(this);

                    button.setLayoutParams(lp);
                    button.setText(Utilities.getAlumnos()[i]);
                    button.setPadding(0, 0, 0, 0);
                    button.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    button.setTextSize(12);
                    button.setTextColor(getColor(R.color.black));
                    button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
                    button.setOnClickListener(this);
                    lP.addView(button);
                }

                for(int i = 21; i >= 11; i--){
                    Button button = new Button(this);

                    button.setLayoutParams(lp);
                    button.setText(Utilities.getAlumnos()[i]);
                    button.setPadding(0, 0, 0, 0);
                    button.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    button.setTextSize(12);
                    button.setTextColor(getColor(R.color.black));
                    button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
                    button.setOnClickListener(this);
                    lS.addView(button);
                }
        }

        if (savedInstanceState != null) {
            etAlumnoPestanna.setText(savedInstanceState.getString(CALVE_ALUMNO_SELECCIONADO));

        }


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Guardar el estado actual
        outState.putString(CALVE_ALUMNO_SELECCIONADO, etAlumnoPestanna.getText().toString());
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