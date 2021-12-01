package br.anmoraes.calculador_salarial_br;

public class INSS {

    private Double salarioBruto;

    public void setSalarioBruto(Double salarioBruto){

        this.salarioBruto = salarioBruto;
    }

    //Calcular desconto de IRPF sobre o Salário Bruto
    public double getDescontoINSS() {

        double faixa1, faixa2, faixa3, faixa4, faixa5;

        //1º faixa de cálculo de INSS
        if (salarioBruto <= 1100) {
            faixa1 = salarioBruto * (7.5 / 100);
            return (faixa1);
        }

        //2º faixa de cálculo de INSS
        if (salarioBruto >= 1100.01 && salarioBruto <= 2203.48) {
            faixa2 = (salarioBruto - 1100) * 0.09 + 82.5;
            return (faixa2);
        }

        //3º faixa de cálculo de INSS
        if (salarioBruto >= 2203.49 && salarioBruto <= 3305.22) {
            faixa3 = (salarioBruto - 2203.48) * 0.12 + 181.81;
            return (faixa3);
        }

        //4º faixa de cálculo de INSS
        if (salarioBruto >= 3305.23 && salarioBruto <= 6433.57) {
            faixa4 = (salarioBruto - 3305.22) * 0.14 + 314.02;
            return (faixa4);
        }

        //5º faixa de cálculo de INSS
        else {
            faixa5 = 751.99;
            return (faixa5);
        }

    }
}
