package com.example.evaluaciont1_vl.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.evaluaciont1_vl.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotaFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotaFragmento extends Fragment {

    private static final String ARG_ASIGNATURA = "asignatura";
    private static final String ARG_NOTA = "nota";

    private String asignatura;
    private Double nota;

    TextView tvAsignatura, tvNota;

    public NotaFragmento() {
    }




    public static NotaFragmento newInstance(String asignatura, Double nota) {
        NotaFragmento fragment = new NotaFragmento();
        Bundle args = new Bundle();
        args.putString(ARG_ASIGNATURA, asignatura);
        args.putDouble(ARG_NOTA, nota);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            asignatura = getArguments().getString(ARG_ASIGNATURA);
            nota = getArguments().getDouble(ARG_NOTA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//Siete FrameLayout
        View v = inflater.inflate(R.layout.fragment_nota_fragmento, container, false);

        tvAsignatura = v.findViewById(R.id.tvAsignaturaFragment);
        tvNota = v.findViewById(R.id.tvNotaFragment);

        Log.e("Fragmento", asignatura + " " + nota);

        tvAsignatura.setText(asignatura);
        tvNota.setText("" + nota);

        return v;
    }
}