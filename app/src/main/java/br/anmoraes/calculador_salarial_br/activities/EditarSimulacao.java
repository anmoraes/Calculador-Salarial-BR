package br.anmoraes.calculador_salarial_br.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.anmoraes.calculador_salarial_br.R;
import br.anmoraes.calculador_salarial_br.dbHelper.ConexaoSQLite;
import br.anmoraes.calculador_salarial_br.model.Simulacao;

public class EditarSimulacao extends AppCompatActivity {

    private ConexaoSQLite db;
    EditText nomeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_simulacao);

        Intent listar = getIntent();

        final int id = listar.getIntExtra("ID", 0);
        db = new ConexaoSQLite(this);
        final Simulacao simulacao = db.getSimulacao(id);

       nomeEdit = findViewById(R.id.nomeEdit);
       nomeEdit.setText(simulacao.getNomeCadastro());

        Button alterar = findViewById(R.id.btAlterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                simulacao.setNomeCadastro(nomeEdit.getText().toString());
                db.update(simulacao);

                Toast.makeText(getApplicationContext(), "Nome da Simulação Alterado", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(EditarSimulacao.this, ListarSimulacao.class);
                startActivity(intent);
                finish();
            }
        });

        Button remover = findViewById(R.id.btRemover);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(simulacao);
                Toast.makeText(getApplicationContext(), "Simulação Removida", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(EditarSimulacao.this, ListarSimulacao.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void voltarToLista(View view) {

        Intent intent = new Intent(EditarSimulacao.this, ListarSimulacao.class);
        startActivity(intent);
        finish();
    }

}