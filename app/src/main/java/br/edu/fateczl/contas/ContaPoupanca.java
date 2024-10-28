package br.edu.fateczl.contas;

public class ContaPoupanca extends ContaBancaria {

    private int diaDoRendimento;

    public ContaPoupanca(String cliente, int num_conta){
        super(cliente, num_conta);
        setDiaDoRendimento(15);
    }

    public void calcularNovoSaldo(float taxa){
        this.setSaldo(this.getSaldo() * (1 + (taxa / 100)));
    }

    public int getDiaDoRendimento() {
        return diaDoRendimento;
    }

    public void setDiaDoRendimento(int diaDoRendimento) {
        this.diaDoRendimento = diaDoRendimento;
    }
}