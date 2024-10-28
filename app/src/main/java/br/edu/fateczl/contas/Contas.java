package br.edu.fateczl.contas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Contas extends AppCompatActivity {

    private TextView tvCli;
    private TextView tvNum;
    private TextView tvVar;
    private EditText etDep;
    private EditText etSac;
    private Button btnDep;
    private Button btnSac;
    private TextView tvSaldo;
    private TextView tvRetorno;
    private Button btnVoltar;
    private Button btnRend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvCli = findViewById(R.id.tvCli);
        tvNum = findViewById(R.id.tvNum);
        tvVar = findViewById(R.id.tvVar);
        etDep = findViewById(R.id.etDep);
        etSac = findViewById(R.id.etSac);
        btnDep = findViewById(R.id.btnDep);
        btnSac = findViewById(R.id.btnSac);
        tvSaldo = findViewById(R.id.tvSaldo);
        tvRetorno = findViewById(R.id.tvRetorno);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(op -> voltar());

        btnRend = findViewById(R.id.btnRend);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String tipo_conta = bundle.getString("tipo_conta");
        String cliente = bundle.getString("cliente");
        int num_conta = bundle.getInt("num_conta");

        tvCli.setText(getText(R.string.tv_cliente) + " " + cliente);
        tvNum.setText(getText(R.string.tv_conta) + " " + num_conta);

        ContaBancaria conta;
        if(tipo_conta.equals(getText(R.string.rbtn_poupanca))){
            conta = new ContaPoupanca(cliente, num_conta);
            tvVar.setText(getText(R.string.tv_dia) + " " + ((ContaPoupanca) conta).getDiaDoRendimento());

            btnRend.setOnClickListener(op -> calcularRendimento((ContaPoupanca) conta));
        } else {
            conta = new ContaEspecial(cliente, num_conta);
            tvVar.setText(getText(R.string.tv_limite) + " " + ((ContaEspecial) conta).getLimite());
            btnRend.setVisibility(TextView.GONE);
        }

        tvSaldo.setText(getText(R.string.tv_saldo) + " " + conta.getSaldo());

        btnDep.setOnClickListener(op -> deposito(conta));
        btnSac.setOnClickListener(op -> saque(conta));
    }

    private void calcularRendimento(ContaPoupanca conta) {
        conta.calcularNovoSaldo(5);
        String retorno = getText(R.string.tv_saldo) + " " + conta.getSaldo();
        tvSaldo.setText(retorno);
        tvRetorno.setText(getText(R.string.tv_rend));
    }

    private void deposito(ContaBancaria conta){
        conta.depositar(Float.parseFloat(etDep.getText().toString()));
        String retorno = getText(R.string.tv_saldo) + " " + conta.getSaldo();
        tvSaldo.setText(retorno);
        etDep.setText("");
    }

    private void saque(ContaBancaria conta){
        float valor = Float.parseFloat(etSac.getText().toString());
        if(conta.sacar(valor)){
            tvRetorno.setText(getText(R.string.tv_sucesso));
        } else {
            tvRetorno.setText(getText(R.string.tv_falha));
        }
        String retorno = getText(R.string.tv_saldo) + " " + conta.getSaldo();
        tvSaldo.setText(retorno);
        etSac.setText("");
    }

    private void voltar(){
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }

}