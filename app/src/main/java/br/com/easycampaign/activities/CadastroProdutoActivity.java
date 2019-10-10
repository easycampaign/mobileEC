package br.com.easycampaign.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.easycampaign.R;
import br.com.easycampaign.model.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    //Firebase
    DatabaseReference databaseProduto;
    FirebaseAuth firebaseAuth;

    TextView txtNmCampanha;
    EditText edtNmProduto, edtDescProduto, edtQtdProduto;
    Button btnCadastro;
    ListView listViewProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();
        databaseProduto = FirebaseDatabase.getInstance().getReference("contas")
                .child(userId)
                .child("produtos");

        //txtNmCampanha = findViewById(R.id.txtNmCampanha);
        edtNmProduto = findViewById(R.id.edtNomeProduto);
        edtDescProduto = findViewById(R.id.edtDescProduto);
        edtQtdProduto = findViewById(R.id.edtQtdProduto);
        btnCadastro = findViewById(R.id.btnCadastrarProduto);
        listViewProdutos = findViewById(R.id.listViewCampanha);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarProduto();
            }
        });

        // Intent intent = getIntent();

        //String id = intent.getStringExtra(CampanhasActivity.CAMPANHA_ID);
        //String nome = intent.getStringExtra(CampanhasActivity.CAMPANHA_NOME);

        //txtNmCampanha.setText(nome);
    }

    private void adicionarProduto() {
        String produtoId = databaseProduto.push().getKey();


        String nome = edtNmProduto.getText().toString();
        String desc = edtDescProduto.getText().toString();
        String qtdProduto = edtQtdProduto.getText().toString();

        Produto produto = new Produto(produtoId, nome, desc, qtdProduto);

        databaseProduto.child(produtoId).setValue(produto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(CadastroProdutoActivity.this, "Produto cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                edtNmProduto.getText().clear();
                edtDescProduto.getText().clear();
                edtQtdProduto.getText().clear();
            }
        });

  }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
