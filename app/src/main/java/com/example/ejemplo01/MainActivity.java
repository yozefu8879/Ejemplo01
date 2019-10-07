package com.example.ejemplo01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PULSADO = "pulsado";
    private static final String TEXTO_INTRODUCIDO = "texto";

    private EditText etTexto;
    private Button btnBoton;
    private TextView tvTexto;

    private boolean pulsado;
    private String textoIntroducido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTexto = (EditText) findViewById(R.id.etTexto);
        btnBoton = (Button) findViewById(R.id.button);
        tvTexto = (TextView) findViewById(R.id.tvTexto);

        restaurarCampos(savedInstanceState);

        btnBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = etTexto.getText().toString();
                if (!texto.isEmpty()) {
                    textoIntroducido = etTexto.getText().toString();
                    pulsado = true;
                    mostrarTextView();
                }
            }
        });

        if (pulsado) {
            mostrarTextView();
        }
    }

    public void mostrarTextView(){
        tvTexto.setText(textoIntroducido);
        tvTexto.setVisibility(View.VISIBLE);
    }

    private void restaurarCampos(Bundle savedInstanceState){

        // Si hay algo en el bundle, es que se ha guardado algo y lo recuperaremos
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(PULSADO, false)){
                this.pulsado = savedInstanceState.getBoolean(PULSADO);
                this.textoIntroducido =
                        savedInstanceState.getString(TEXTO_INTRODUCIDO, "");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Metemos en el bundle lo que queremos conservar
        if (pulsado){
            outState.putBoolean(PULSADO, pulsado);
            outState.putString(TEXTO_INTRODUCIDO, textoIntroducido);
        }
    }
}