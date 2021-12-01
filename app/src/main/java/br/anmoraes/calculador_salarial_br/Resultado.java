package br.anmoraes.calculador_salarial_br;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.DecimalFormat;

public class Resultado extends AppCompatActivity {
    TextView txtSalarioBruto,
             txtINSS,
             txtIRPF,
             txtPensaoAlimenticia,
             txtOutrosDescontos,
             txtBaseIRPF,
             txtFGTS,
             txtSalarioLiquido;

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

        if (parametros != null){

            //Salário Bruto
            double salarioBruto = parametros.getDouble("salario_bruto");
            String salarioB = decimal.format(salarioBruto);
            txtSalarioBruto.setText(salarioB);

            //Desconto de INSS
            double descontoINSS = parametros.getDouble("desconto_INSS");
            String descInss = decimal.format(descontoINSS);
            txtINSS.setText(descInss);

            //Desconto de IRPF
            double descontoIRPF = parametros.getDouble("desconto_IRPF");
            String descIrpf = decimal.format(descontoIRPF);
            txtIRPF.setText(descIrpf);

            //Desconto de pensão
            double descontoPensao = parametros.getDouble("pensao");
            String descPensao = decimal.format(descontoPensao);
            txtPensaoAlimenticia.setText(descPensao);

            //Outros Descontos
            double outrosDescontos = parametros.getDouble("outros_descontos");
            String outrosDesc = decimal.format(outrosDescontos);
            txtOutrosDescontos.setText(outrosDesc);

            //Salario Base para o calculo de IRPF
            double baseIRPF = parametros.getDouble("salario_baseIRPF");
            String salarioBaseIRPF = decimal.format(baseIRPF);
            txtBaseIRPF.setText(salarioBaseIRPF);

            //Valor de FGTS a ser depositado
            double fgtsValor = parametros.getDouble("calcular_FGTS");
            String valorFgts = decimal.format(fgtsValor);
            txtFGTS.setText(valorFgts);

            //Salário Líquido Final
            double salarioLiquido = parametros.getDouble("salario_liquido");

            String salarioLiquidofinal = decimal.format(salarioLiquido);
            txtSalarioLiquido.setText("R$ " + salarioLiquidofinal);

        }
    }

    public void voltar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}