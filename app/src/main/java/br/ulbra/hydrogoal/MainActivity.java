package br.ulbra.hydrogoal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edPeso, edIdade, edAtividade, edConsumo;
    TextView txRes;
    Button btCalcular, btLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edAtividade = findViewById(R.id.edtAtividade);
        txRes = findViewById(R.id.txtRes);
        edPeso = findViewById(R.id.edtPeso);
        edIdade = findViewById(R.id.edtIdade);
        btCalcular = findViewById(R.id.btnCalcular);
        btLimpar = findViewById(R.id.btnLimpar);
        edConsumo = findViewById(R.id.edtConsumo);

        btCalcular.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (edAtividade.getText().toString().isEmpty() ||
                        edIdade.getText().toString().isEmpty() ||
                        edPeso.getText().toString().isEmpty() ||
                edConsumo.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "campos vazios",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                int atividade = Integer.parseInt(edAtividade.getText().toString());
                int idade = Integer.parseInt(edIdade.getText().toString());
                double peso = Double.parseDouble(edPeso.getText().toString());
                double consumo = Double.parseDouble(edConsumo.getText().toString());
                double extra;

                if (atividade == 1) {
                    extra = 0;
                } else if (atividade == 2) {
                    extra = 300;
                } else if (atividade == 3) {
                    extra = 600;
                } else {
                    Toast.makeText(MainActivity.this, "não digitado certo", Toast.LENGTH_LONG).show();
                    return;
                }

                double cT = (peso * 35) + extra;
                double r = cT - consumo;

                if (peso <= 0 || idade <= 0){
                    Toast.makeText(MainActivity.this, "Não como calcular se for zero", Toast.LENGTH_LONG).show();
                    return;
                }

                txRes.setText("RESULTADO ESTIMADO:"+"\n"
                        +"Meta diaria: " + cT + "ml" +
                        "\n" +
                        "Faltam (R): " + r + "ml" );
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edAtividade.setText(null);
                edIdade.setText(null);
                edPeso.setText(null);
                edConsumo.setText(null);
                txRes.setText(null);
            }
        });

    }
}

