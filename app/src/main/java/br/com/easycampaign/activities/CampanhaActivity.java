package br.com.easycampaign.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.easycampaign.R;
import br.com.easycampaign.model.Campanha;

public class CampanhaActivity extends AppCompatActivity {


    TextView txtNmCampanha, txtDataInicioCampanha, txtDataFimCampanha;
    ImageButton btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanha);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Iniciatializing the layout elements
        txtNmCampanha = findViewById(R.id.txtNmCampanha);
        txtDataFimCampanha = findViewById(R.id.txtDataFimCampanha);
        txtDataInicioCampanha = findViewById(R.id.txtDataInicioCampanha);
        btnUpdate = findViewById(R.id.btnAtualizarCampanha);
        btnDelete = findViewById(R.id.btnRemoverCampanha);

        Intent intent = getIntent();

        final String id = intent.getStringExtra(CampanhasActivity.CAMPANHA_ID);
        final String nome = intent.getStringExtra(CampanhasActivity.CAMPANHA_NOME);
        final String dataInicio = intent.getStringExtra(CampanhasActivity.CAMPANHA_DATA_INICIO);
        final String dataFim = intent.getStringExtra(CampanhasActivity.CAMPANHA_DATA_FINAL);
        final String produto = intent.getStringExtra(CampanhasActivity.CAMPANHA_PRODUTOS);

        txtNmCampanha.setText(nome);
        txtDataInicioCampanha.setText(dataInicio);
        txtDataFimCampanha.setText(dataFim);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAtualizarDialog(id, nome, dataInicio, dataFim, produto);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removerCampanha(id);
            }
        });
    }

    private void removerCampanha(String id) {
        DatabaseReference databaseCampanha;

        databaseCampanha = FirebaseDatabase.getInstance().getReference("contas")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("campanhas").child(id);

        databaseCampanha.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(CampanhaActivity.this, "Campanha removida com sucesso,", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showAtualizarDialog(String campanhaId, String campanhaNm, String campanhaDtInicio, String campanhaDtFim, String campanhaProduto){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.atualizar_dialog, null);

        dialogBuilder.setView(dialogView);

        final TextView txtNome = dialogView.findViewById(R.id.txtAttNmCamp);
        final EditText edtNmCampanha = dialogView.findViewById(R.id.edtAttNmCampanha);
        final String id = campanhaId;
        final String dtInicio = campanhaDtInicio;
        final String dtFim = campanhaDtFim;
        final String produto = campanhaProduto;

        final Button btnAtualizar = dialogView.findViewById(R.id.btnAtualizar);
        //dialogBuilder.setTitle("Atualizando " + campanhaNm);
        txtNome.setText("Atualizando " + campanhaNm);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String attNome = edtNmCampanha.getText().toString().trim();
                String attId = id;
                String attDtInicio = dtInicio;
                String attDtFim = dtFim;
                String attrProduto = produto;

                if(TextUtils.isEmpty(attNome)){
                    edtNmCampanha.setError("Por favor, digite um nome.");
                }

                atualizarCampanha(attId, attNome, attDtInicio, attDtFim, attrProduto);
                alertDialog.dismiss();
            }
        });

    }


    private boolean atualizarCampanha(String id, String nome, String dataInicio, String dataFim, String produto){
        DatabaseReference databaseCampanha = FirebaseDatabase.getInstance().getReference("contas")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("campanhas")
                .child(id);

        Campanha campanha = new Campanha(id, nome, dataInicio, dataFim, produto);

        databaseCampanha.setValue(campanha).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(CampanhaActivity.this, "Campanha atualizada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }


    // Making the back button work
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
