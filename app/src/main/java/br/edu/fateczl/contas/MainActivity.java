package br.edu.fateczl.contas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etCli;
    private EditText etNum;
    private RadioButton rbPou;
    private RadioButton rbEsp;
    private Button btnAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCli = findViewById(R.id.etCli);
        etNum = findViewById(R.id.etNum);

        rbPou = findViewById(R.id.rbPou);
        rbPou.setChecked(true);

        rbEsp = findViewById(R.id.rbEsp);

        btnAcessar = findViewById(R.id.btnAcessar);
        btnAcessar.setOnClickListener(op -> acessar());
    }

    private void acessar() {
        String cliente = etCli.getText().toString();
        String tipo_conta;
        int num_conta = Integer.parseInt((etNum.getText().toString()));

        if(this.rbPou.isChecked()){
            tipo_conta = getText(R.string.rbtn_poupanca).toString();
        } else {
            tipo_conta = getText(R.string.rbtn_especial).toString();
        }

        Bundle bundle = new Bundle();
        bundle.putString("cliente", cliente);
        bundle.putString("tipo_conta", tipo_conta);
        bundle.putInt("num_conta", num_conta);

        mudar(bundle);
    }

    private void mudar(Bundle bundle){
        Intent i = new Intent(this, Contas.class);
        i.putExtras(bundle);
        this.startActivity(i);
        this.finish();
    }
}