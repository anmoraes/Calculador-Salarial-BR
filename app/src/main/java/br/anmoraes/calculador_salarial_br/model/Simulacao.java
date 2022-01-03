package br.anmoraes.calculador_salarial_br.model;

public class Simulacao {

    private int id;
    private String nomeCadastro;
    private double salariobruto;
    private double valorINSS;
    private double valorIRPF;
    private double pensaoAlimenticia;
    private double outrosDescontos;
    private double baseIRPF;
    private double fgtsDespositado;
    private double salarioLiquido;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCadastro() {
        return nomeCadastro;
    }

    public void setNomeCadastro(String nomeCadastro) {
        this.nomeCadastro = nomeCadastro;
    }

    public double getSalariobruto() {
        return salariobruto;
    }

    public void setSalariobruto(double salariobruto) {
        this.salariobruto = salariobruto;
    }

    public double getValorINSS() {
        return valorINSS;
    }

    public void setValorINSS(double valorINSS) {
        this.valorINSS = valorINSS;
    }

    public double getValorIRPF() {
        return valorIRPF;
    }

    public void setValorIRPF(double valorIRPF) {
        this.valorIRPF = valorIRPF;
    }

    public double getPensaoAlimenticia() {
        return pensaoAlimenticia;
    }

    public void setPensaoAlimenticia(double pensaoAlimenticia) {
        this.pensaoAlimenticia = pensaoAlimenticia;
    }

    public double getOutrosDescontos() {
        return outrosDescontos;
    }

    public void setOutrosDescontos(double outrosDescontos) {
        this.outrosDescontos = outrosDescontos;
    }

    public double getBaseIRPF() {
        return baseIRPF;
    }

    public void setBaseIRPF(double baseIRPF) {
        this.baseIRPF = baseIRPF;
    }

    public double getFgtsDespositado() {
        return fgtsDespositado;
    }

    public void setFgtsDespositado(double fgtsDespositado) {
        this.fgtsDespositado = fgtsDespositado;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }


}
