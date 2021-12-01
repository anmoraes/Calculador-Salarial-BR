package br.anmoraes.calculador_salarial_br;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText editSalarioBruto,
             editDependentes,
             editPensaoAlimenticia,
             editOutrosDescontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editSalarioBruto        = findViewById(R.id.editSalarioBruto);
        editDependentes         = findViewById(R.id.editDependentes);
        editPensaoAlimenticia   = findViewById(R.id.editPensaoAlimenticia);
        editOutrosDescontos     = findViewById(R.id.editOutrosDescontos);
    }

    //Salario Bruto inserido pelo usuário
    public double salarioBruto() {
        if (editSalarioBruto.getText().toString().isEmpty()){
            return (0);
        }
        else {
            double salario = Double.parseDouble(editSalarioBruto.getText().toString());
            return (salario);
        }
    }

    //Dependentes inserido pelo usuário
    public double dependentes() {
        if(editDependentes.getText().toString().isEmpty()){
            return (0);
        }
        else {
            int qtdDependentes = Integer.parseInt(editDependentes.getText().toString());

            if (qtdDependentes > 0) {
                double descDependente = qtdDependentes * 189.59;
                return (descDependente);
            } else {
                return (0);
            }
        }
    }

    //Pensão Alimentícia inserido pelo usuário
    public double pensao() {
        if(editPensaoAlimenticia.getText().toString().isEmpty()){
            return (0);
        }
        else {
            double pensaoAlimenticia = Double.parseDouble(editPensaoAlimenticia.getText().toString());
            return (pensaoAlimenticia);
        }
    }

    //Variável Outros Descontos
    public double outrosDescontos(){
        if (editOutrosDescontos.getText().toString().isEmpty()){
            return (0);
        }
        else {
            double outrosDesc = Double.parseDouble(editOutrosDescontos.getText().toString());
            return (outrosDesc);
        }
    }

    // Calcular INSS a ser descontado do salário Bruto
    public double descontoINSS() {
        INSS desconto = new INSS();
        desconto.setSalarioBruto(salarioBruto());

        double descInss = desconto.getDescontoINSS();
        return (descInss);
    }

    //Calcular Salário Base para IRPF
    public double salarioBaseIRPF() {
        double salarioBase = salarioBruto() - descontoINSS() - dependentes() - pensao();
        if (salarioBase < 0){
            return (0);
        }
        else {
            return (salarioBase);
        }
    }

    //Calcular desconto de IRPF
    public double descontoIRPF(){
        IRPF desconto = new IRPF();
        desconto.setBaseSalario(salarioBaseIRPF());

        double descontoFinal  =  desconto.getDestontoIRPF();
        return (descontoFinal) ;
    }

    //Calcular Salario Líquido
    public double salarioLiquido(){

        double salarioLiq = salarioBruto() - descontoINSS() - descontoIRPF() - pensao() - outrosDescontos();
        return (salarioLiq);
    }

    //Calcular FGTS a ser depositado
    public double calcularFGTS(){
        double fgts = salarioBruto() * 0.08;
        return (fgts);
    }

    // Disparador do cálculo
    public void btCalcular(View view) {

        //Intent Enviadora
        Intent intentEnviadora = new Intent(MainActivity.this, Resultado.class);

        Bundle parametros = new Bundle();
        parametros.putDouble("salario_bruto", salarioBruto());
        parametros.putDouble("desconto_INSS", descontoINSS());
        parametros.putDouble("desconto_IRPF", descontoIRPF());
        parametros.putDouble("pensao", pensao());
        parametros.putDouble("outros_descontos", outrosDescontos());
        parametros.putDouble("salario_baseIRPF", salarioBaseIRPF());
        parametros.putDouble("calcular_FGTS", calcularFGTS());
        parametros.putDouble("salario_liquido", salarioLiquido());
        intentEnviadora.putExtras(parametros);

        startActivity(intentEnviadora);
        finish();
    }

    //Abre a tela de Política de Privacidade
    public void btPolitica(View view){

        Intent intentPolitica = new Intent(this, Politica.class);
        startActivity(intentPolitica);
        finish();
    }

   //Abre a tela dos Termos de Uso
    public void btTermos(View view){

        Intent intentTermos = new Intent(this, Termos.class);
        startActivity(intentTermos);
        finish();
    }

}