package br.anmoraes.calculador_salarial_br.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.DecimalFormat;

import br.anmoraes.calculador_salarial_br.R;
import br.anmoraes.calculador_salarial_br.model.Simulacao;

public class Resultado extends AppCompatActivity {
    private TextView txtSalarioBruto,
             txtINSS,
             txtIRPF,
             txtPensaoAlimenticia,
             txtOutrosDescontos,
             txtBaseIRPF,
             txtFGTS,
             txtSalarioLiquido;

    private double salarioBruto, descontoINSS, descontoIRPF,
                   descontoPensao, outrosDescontos, baseIRPF,
                   fgtsValor, salarioLiquido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        txtSalarioBruto       = findViewById(R.id.txtSalarioBruto);
        txtINSS               = findViewById(R.id.txtINSS);
        txtIRPF               = findViewById(R.id.txtIRPF);
        txtPensaoAlimenticia  = findViewById(R.id.txtPensaoAlimenticia);
        txtOutrosDescontos    = findViewById(R.id.txtOutrosDescontos);
        txtBaseIRPF           = findViewById(R.id.txtBaseIRPF);
        txtFGTS               = findViewById(R.id.txtFGTS);
        txtSalarioLiquido     = findViewById(R.id.txtSalarioLiquido);


        DecimalFormat decimal = new DecimalFormat("0.00");

        Intent intentRecebedora = getIntent();
        Bundle parametros = intentRecebedora.getExtras();

        if (parametros != null) {

            //Salário Bruto
            salarioBruto = parametros.getDouble("salario_bruto");
            String salarioB = decimal.format(salarioBruto);
            txtSalarioBruto.setText(salarioB);

            //Desconto de INSS
            descontoINSS = parametros.getDouble("desconto_INSS");
            String descInss = decimal.format(descontoINSS);
            txtINSS.setText(descInss);

            //Desconto de IRPF
            descontoIRPF = parametros.getDouble("desconto_IRPF");
            String descIrpf = decimal.format(descontoIRPF);
            txtIRPF.setText(descIrpf);

            //Desconto de pensão
            descontoPensao = parametros.getDouble("pensao");
            String descPensao = decimal.format(descontoPensao);
            txtPensaoAlimenticia.setText(descPensao);

            //Outros Descontos
            outrosDescontos = parametros.getDouble("outros_descontos");
            String outrosDesc = decimal.format(outrosDescontos);
            txtOutrosDescontos.setText(outrosDesc);

            //Salario Base para o calculo de IRPF
            baseIRPF = parametros.getDouble("salario_baseIRPF");
            String salarioBaseIRPF = decimal.format(baseIRPF);
            txtBaseIRPF.setText(salarioBaseIRPF);

            //Valor de FGTS a ser depositado
            fgtsValor = parametros.getDouble("calcular_FGTS");
            String valorFgts = decimal.format(fgtsValor);
            txtFGTS.setText(valorFgts);

            //Salário Líquido Final
            salarioLiquido = parametros.getDouble("salario_liquido");
            String salarioLiquidofinal = decimal.format(salarioLiquido);
            txtSalarioLiquido.setText("R$ " + salarioLiquidofinal);

        }
    }

   //Voltar para tela inicial
    public void voltar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void btSalvarSimulacao (View view) {

        Intent intentDB = new Intent(this, SalvarSimulacao.class);

        Bundle parametros = new Bundle();
        parametros.putDouble("salario_bruto", salarioBruto);
        parametros.putDouble("desconto_INSS", descontoINSS);
        parametros.putDouble("desconto_IRPF", descontoIRPF);
        parametros.putDouble("pensao", descontoPensao);
        parametros.putDouble("outros_descontos", outrosDescontos);
        parametros.putDouble("salario_baseIRPF", baseIRPF);
        parametros.putDouble("calcular_FGTS", fgtsValor);
        parametros.putDouble("salario_liquido", salarioLiquido);
        intentDB.putExtras(parametros);

        startActivity(intentDB);
        finish();

    }

}

