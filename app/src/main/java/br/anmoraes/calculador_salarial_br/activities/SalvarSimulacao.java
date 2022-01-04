package br.anmoraes.calculador_salarial_br.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.anmoraes.calculador_salarial_br.R;
import br.anmoraes.calculador_salarial_br.model.Simulacao;
import br.anmoraes.calculador_salarial_br.dbHelper.ConexaoSQLite;

public class SalvarSimulacao extends AppCompatActivity {

    private ConexaoSQLite bd;
    private Simulacao objTabResult;

    private EditText nomeCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_simulacao);
        bd = new ConexaoSQLite(this);

        nomeCadastro = findViewById(R.id.nomeCadastro);

    }

    public void btCancelar (View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void btEnviarDB (View view) {

        Intent intentRecebeDB = getIntent();
        Bundle parametros = intentRecebeDB.getExtras();

        if (parametros != null) {

            //Salário Bruto
            double salarioBruto = parametros.getDouble("salario_bruto");

            //Desconto de INSS
            double descontoINSS = parametros.getDouble("desconto_INSS");

            //Desconto de IRPF
            double descontoIRPF = parametros.getDouble("desconto_IRPF");

            //Desconto de pensão
            double descontoPensao = parametros.getDouble("pensao");

            //Outros Descontos
            double outrosDescontos = parametros.getDouble("outros_descontos");

            //Salario Base para o calculo de IRPF
            double baseIRPF = parametros.getDouble("salario_baseIRPF");

            //Valor de FGTS a ser depositado
            double fgtsValor = parametros.getDouble("calcular_FGTS");

            //Salário Líquido Final
            double salarioLiquido = parametros.getDouble("salario_liquido");

            //Nome de Identificação Cadastrada
            String nomeCadastrado = nomeCadastro.getText().toString();

            objTabResult = new Simulacao();

            objTabResult.setNomeCadastro(nomeCadastrado);
            objTabResult.setSalariobruto(salarioBruto);
            objTabResult.setValorINSS(descontoINSS);
            objTabResult.setValorIRPF(descontoIRPF);
            objTabResult.setPensaoAlimenticia(descontoPensao);
            objTabResult.setOutrosDescontos(outrosDescontos);
            objTabResult.setBaseIRPF(baseIRPF);
            objTabResult.setFgtsDespositado(fgtsValor);
            objTabResult.setSalarioLiquido(salarioLiquido);

            //Insert no Banco de Dados
            bd.insert(objTabResult);

            Toast.makeText(getApplicationContext(), nomeCadastrado+" Salvo", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
