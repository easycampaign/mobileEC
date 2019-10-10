package br.com.easycampaign.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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
import br.com.easycampaign.model.Produto;

public class ProdutoActivity extends AppCompatActivity {

    TextView txtNmProduto, txtDescProduto, txtQtdProduto;
    ImageButton btnUpdate, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Inicializing the layout elements
        txtNmProduto = findViewById(R.id.txtProduto);
        txtDescProduto = findViewById(R.id.txtDescProduto);
        txtQtdProduto = findViewById(R.id.txtQtdProduto);
        btnUpdate = findViewById(R.id.btnAtualizarProduto);
        btnDelete = findViewById(R.id.btnRemoverProduto);

        Intent intent = getIntent();

        final String id = intent.getStringExtra(EstoqueActivity.PRODUTO_ID);
        final String nome = intent.getStringExtra(EstoqueActivity.PRODUTO_NOME);
        final String desc = intent.getStringExtra(EstoqueActivity.PRODUTO_DESC);
        final String qtd = intent.getStringExtra(EstoqueActivity.PRODUTO_QTD);

        txtNmProduto.setText(nome);
        txtDescProduto.setText(desc);
        txtQtdProduto.setText(qtd);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAtualizarDialog(id, nome, desc, qtd);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removerProduto(id);
            }
        });

    }

    private void removerProduto(String id) {
        DatabaseReference databaseProduto;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();

        databaseProduto = FirebaseDatabase.getInstance().getReference("contas")
                .child(userId)
                .child("produtos")
                .child(id);

        databaseProduto.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ProdutoActivity.this, getString(R.string.produto_removido), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void showAtualizarDialog(String idProduto, String nomeProduto, String descProduto, String qtdProduto) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.atualizar_produto_dialog, null);

        dialogBuilder.setView(dialogView);

        final String id = idProduto;
        final TextView txtNome = dialogView.findViewById(R.id.txtAttNmProduto);
        final EditText edtNm = dialogView.findViewById(R.id.edtAttNmProduto);
        final EditText edtDesc = dialogView.findViewById(R.id.edtAttDescProduto);
        final EditText edtQtd = dialogView.findViewById(R.id.edtAttQtdProduto);

        final Button btnAtualizar = dialogView.findViewById(R.id.btnAtualizar);
        txtNome.setText("Atualizando " + nomeProduto);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String attId = id;
                String attNome = edtNm.getText().toString().trim();
                String attDesc = edtDesc.getText().toString();
                String attQtd = edtQtd.getText().toString();


                // VALIDACOES
                if(TextUtils.isEmpty(attNome)){
                    edtNm.setError("Por favor, digite um nome");
                }

                if(TextUtils.isEmpty(attDesc)){
                    edtNm.setError("Por favor, digite uma descrição.");
                }

                if(TextUtils.isEmpty(attQtd)){
                    edtNm.setError("Por favor, digite a quantidade.");
                }

                atualizarProduto(attId, attNome, attDesc, attQtd);
                alertDialog.dismiss();
            }
        });
    }

    private void atualizarProduto(String attId, String attNome, String attDesc, String attQtd) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference databaseProduto = FirebaseDatabase.getInstance().getReference("contas")
                .child(userId)
                .child("produtos")
                .child(attId);

        Produto produto = new Produto(attId, attNome, attDesc, attQtd);

        databaseProduto.setValue(produto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ProdutoActivity.this, getString(R.string.produto_atualizado), Toast.LENGTH_SHORT).show();
            }
        });
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
