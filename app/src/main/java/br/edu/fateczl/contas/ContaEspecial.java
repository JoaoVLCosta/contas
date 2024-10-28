package br.edu.fateczl.contas;

public class ContaEspecial extends ContaBancaria {

    private float limite;

    public ContaEspecial(String cliente, int num_conta){
        super(cliente, num_conta);
        setLimite(500f);
    }

    @Override
    public boolean sacar(float valor){

        if((this.getSaldo() + limite - valor) < 0){
            return false;
        }

        this.setSaldo(this.getSaldo() - valor);

        return true;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }
}