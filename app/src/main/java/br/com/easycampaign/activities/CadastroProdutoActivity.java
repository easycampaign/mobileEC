package br.com.easycampaign.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.easycampaign.R;

public class CadastroProdutoActivity extends AppCompatActivity {


    //Firebase
    DatabaseReference databaseProduto;
    FirebaseAuth firebaseAuth;

    TextView txtNmCampanha;
    EditText edtNmProduto, edtDescProduto, edtQtdProduto;
    ListView listViewProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseProduto = FirebaseDatabase.getInstance().getReference("contas").child(firebaseAuth.getCurrentUser().getUid()).child("produtos");

        txtNmCampanha = findViewById(R.id.txtNmCampanha);
        edtNmProduto = findViewById(R.id.edtNomeProduto);
        edtDescProduto = findViewById(R.id.edtDescProduto);
        edtQtdProduto = findViewById(R.id.edtQtdProduto);
        listViewProdutos = findViewById(R.id.listViewCampanha);

        Intent intent = getIntent();


        String id = intent.getStringExtra(CampanhasActivity.CAMPANHA_ID);
        String nome = intent.getStringExtra(CampanhasActivity.CAMPANHA_NOME);

        txtNmCampanha.setText(nome);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
