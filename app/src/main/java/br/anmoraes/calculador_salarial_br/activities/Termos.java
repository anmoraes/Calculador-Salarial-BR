package br.anmoraes.calculador_salarial_br.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.anmoraes.calculador_salarial_br.R;
import br.anmoraes.calculador_salarial_br.activities.MainActivity;

public class Termos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos);
    }

    public void voltar (View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}