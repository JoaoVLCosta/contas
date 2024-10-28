package br.edu.fateczl.contas;

public class ContaBancaria {

    private String cliente;
    private int num_conta;
    private float saldo;

    public ContaBancaria(String cliente, int num_conta){
        setCliente(cliente);
        setNum_conta(num_conta);
        setSaldo(0);
    }

    public boolean sacar(float valor){
        float total = getSaldo() - valor;

        if(total < 0){
            return false;
        }

        setSaldo(total);

        return true;
    }

    public void depositar(float valor){
        setSaldo(getSaldo() + valor);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {
        this.num_conta = num_conta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}