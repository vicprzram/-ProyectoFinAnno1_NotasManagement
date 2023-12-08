package com.example.evaluaciont1_vl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.evaluaciont1_vl.datos.Utilities;

public class PantallaSeleccionAsignatura extends AppCompatActivity implements View.OnClickListener {

    private EditText etAsignaturaPestanna;
    private Button btnAceptar, btnCancelar;
    private static final String CLAVE_ASIGNATURA_SELECCIONADA = "asignatura_seleccionada";

    private int ROTATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_seleccion_asignatura);

        ROTATION = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();

        btnAceptar = findViewById(R.id.btnAceptarAsignatura);
        btnCancelar = findViewById(R.id.btnCancelarAsignatura);
        etAsignaturaPestanna = findViewById(R.id.etAsignaturaPestanna);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        LinearLayout ll = findViewById(R.id.llAsignatura);
        LinearLayout lP = findViewById(R.id.llAsignaturaPrimeros);
        LinearLayout lS = findViewById(R.id.llAsignaturaSegundos);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.weight = 1;

        switch (this.ROTATION){
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                for(String s : Utilities.getAsignaturas()){
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
                break;
            case Surface.ROTATION_90:
            default:
                for(int i = 0; i < 4; i++){
                    Button button = new Button(this);
                    button.setLayoutParams(lp);
                    button.setText(Utilities.getAsignaturas()[i]);
                    button.setPadding(0,0,0,0);
                    button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    button.setTextSize(12);
                    button.setTextColor(getColor(R.color.black));
                    button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
                    button.setOnClickListener(this);
                    lP.addView(button);
                }

                for(int i = 6; i >= 4; i--){
                    Button button = new Button(this);
                    button.setLayoutParams(lp);
                    button.setText(Utilities.getAsignaturas()[i]);
                    button.setPadding(0,0,0,0);
                    button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    button.setTextSize(12);
                    button.setTextColor(getColor(R.color.black));
                    button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
                    button.setOnClickListener(this);
                    lS.addView(button);
                }
        }

        if (savedInstanceState != null) {
            etAsignaturaPestanna.setText(savedInstanceState.getString(CLAVE_ASIGNATURA_SELECCIONADA));
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CLAVE_ASIGNATURA_SELECCIONADA, etAsignaturaPestanna.getText().toString());

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAceptarAsignatura) {
            if (etAsignaturaPestanna.getText().toString().isEmpty()) {
                new Toast(this).makeText(this, getString(R.string.erro_no_data), Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent();
                i.putExtra("asignatura", etAsignaturaPestanna.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        }else if(v.getId() == R.id.btnCancelarAsignatura){
            setResult(RESULT_CANCELED);
            finish();
        }else{
            for(String s : Utilities.getAsignaturas()){
                if(((Button)v).getText().equals(s)){
                    etAsignaturaPestanna.setText(s);
                }
            }
        }
    }
}