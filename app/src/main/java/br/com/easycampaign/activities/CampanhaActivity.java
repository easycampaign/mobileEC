package br.com.easycampaign.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.easycampaign.R;

public class CampanhaActivity extends AppCompatActivity {


    TextView txtNmCampanha, txtDataInicioCampanha, txtDataFimCampanha;
    ImageButton btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanha);

        //Iniciatializing the layout elements
        txtNmCampanha = findViewById(R.id.txtNmCampanha);
        txtDataFimCampanha = findViewById(R.id.txtDataFimCampanha);
        txtDataInicioCampanha = findViewById(R.id.txtDataInicioCampanha);
        btnUpdate = findViewById(R.id.btnAtualizarCampanha);
        btnDelete = findViewById(R.id.btnRemoverCampanha);

        Intent intent = getIntent();

        String id = intent.getStringExtra(CampanhasActivity.CAMPANHA_ID);
        String nome = intent.getStringExtra(CampanhasActivity.CAMPANHA_NOME);
        String dataInicio = intent.getStringExtra(CampanhasActivity.CAMPANHA_DATA_INICIO);
        String dataFim = intent.getStringExtra(CampanhasActivity.CAMPANHA_DATA_FINAL);
        String produto = intent.getStringExtra(CampanhasActivity.CAMPANHA_PRODUTOS);

        txtNmCampanha.setText(nome);
        txtDataInicioCampanha.setText(dataInicio);
        txtDataFimCampanha.setText(dataFim);
    }
}
