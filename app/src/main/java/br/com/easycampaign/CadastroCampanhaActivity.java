package br.com.easycampaign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.easycampaign.model.Campanha;

public class CadastroCampanhaActivity extends AppCompatActivity {

    EditText edtNomeCampanha, edtDataInicio, edtDataFim;
    Spinner spinnerProdutos;
    Button btnCadastrarCampanha;

    DatabaseReference database;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_campanha);

        database = FirebaseDatabase.getInstance().getReference("campanhas");
        firebaseAuth = FirebaseAuth.getInstance();

        edtNomeCampanha = findViewById(R.id.edtNomeCampanha);
        edtDataInicio = findViewById(R.id.edtDataInicioCampanha);
        edtDataFim = findViewById(R.id.edtDataFimCampanha);
        btnCadastrarCampanha = findViewById(R.id.btnCadastrarCampanha);
        spinnerProdutos = findViewById(R.id.spinnerProdutos);

        btnCadastrarCampanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarCampanha();
            }
        });
    }

    private void adicionarCampanha(){
        String nome = edtNomeCampanha.getText().toString().trim();
        String dataInicio = edtDataInicio.getText().toString();
        String dataFim = edtDataFim.getText().toString();
        String produto = spinnerProdutos.getSelectedItem().toString();

        if(!TextUtils.isEmpty(nome)){
            String id = database.push().getKey();

            Campanha campanha = new Campanha(id, nome, produto, dataInicio, dataFim);

            database.child(id).setValue(campanha);
//            database.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(campanha).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    Toast.makeText(CadastroCampanhaActivity.this, "Campanha adicionada com sucesso.", Toast.LENGTH_SHORT).show();
//                    //startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                }
//            });
        }else{
            Toast.makeText(this, "Por favor, digite um nome para a campanha", Toast.LENGTH_SHORT).show();
        }
    }
}
