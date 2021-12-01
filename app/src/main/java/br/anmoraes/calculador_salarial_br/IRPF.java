package br.anmoraes.calculador_salarial_br;

public class IRPF extends MainActivity {

    private Double baseSalario;

    public void setBaseSalario(Double baseSalario){
        this.baseSalario = baseSalario;
    }

    //Calcular desconto de IRPF sobre o Salário Base
    public double getDestontoIRPF() {

        double faixa2, faixa3, faixa4, faixa5;

        //1º faixa de cálculo de IRPF
        if (baseSalario <= 1903.98) {
            return (0);
        }

        //2º faixa de cálculo de IRPF
        if (baseSalario >= 1903.99 &&  baseSalario <= 2826.65) {
            faixa2 = (baseSalario - 1903.98) * 0.075;
            return (faixa2);
        }

        //3º faixa de cálculo de IRPF
        if (baseSalario >= 2826.66 && baseSalario <= 3751.05) {
            faixa3 = (baseSalario - 2826.65) * 0.15 + 69.2;
            return (faixa3);
        }

        //4º faixa de cálculo de IRPF
        if (baseSalario >= 3751.06 && baseSalario <= 4664.68) {
            faixa4 = (baseSalario - 3751.05) * 0.225 + 207.86;
            return (faixa4);
        }

        //5º faixa de cálculo de IRPF
        else {
            faixa5 = (baseSalario - 4664.68) * 0.275 + 413.43;
            return (faixa5);
        }

    }
}
