package br.anmoraes.calculador_salarial_br.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.anmoraes.calculador_salarial_br.R;
import br.anmoraes.calculador_salarial_br.adapter.SimulacaoAdapter;
import br.anmoraes.calculador_salarial_br.dbHelper.ConexaoSQLite;
import br.anmoraes.calculador_salarial_br.model.Simulacao;

public class ListarSimulacao extends AppCompatActivity {

    private ConexaoSQLite db;
    ArrayList<Simulacao> listaSimulacoes;

    private ListView lstSimulacoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_simulacao);

        db = new ConexaoSQLite(this);

        lstSimulacoes = findViewById(R.id.lstSimulacoes);

    }


    @Override
    protected void onStart() {
        super.onStart();
        ListView lista = findViewById(R.id.lstSimulacoes);
        listaSimulacoes = db.getAllSimulacoes();
        SimulacaoAdapter adapter = new SimulacaoAdapter(this,listaSimulacoes);
        lista.setAdapter(adapter);



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListarSimulacao.this, EditarSimulacao.class);
                intent.putExtra("ID", listaSimulacoes.get(position).getId());
                startActivity(intent);
                }
            });

    }

    //Voltar para tela inicial
    public void voltar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}






