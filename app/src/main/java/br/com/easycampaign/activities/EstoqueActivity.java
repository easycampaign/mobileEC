package br.com.easycampaign.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.easycampaign.ProdutoAdapter;
import br.com.easycampaign.R;
import br.com.easycampaign.model.Produto;

public class EstoqueActivity extends AppCompatActivity {


    public static final String PRODUTO_NOME = "produtonome";
    public static final String PRODUTO_ID = "produtoid";
    public static final String PRODUTO_DESC = "produtodesc";
    public static final String PRODUTO_QTD = "produtoqtd";

    ListView listViewProduto;
    List<Produto> produtoList;

    //Firebase
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseEstoque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Variable incialization
        listViewProduto = findViewById(R.id.listViewProduto);
        produtoList = new ArrayList<>();

        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();
        databaseEstoque = FirebaseDatabase.getInstance().getReference("contas").child(userId).child("produtos");

        listViewProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produto produto = produtoList.get(i);
                Intent intent = new Intent(getApplicationContext(), ProdutoActivity.class);
                intent.putExtra(PRODUTO_ID, produto.getProdutoId());
                intent.putExtra(PRODUTO_NOME, produto.getProdutoNome());
                intent.putExtra(PRODUTO_DESC, produto.getProdutoDesc());
                intent.putExtra(PRODUTO_QTD, produto.getProdutoQtd());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseEstoque.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                produtoList.clear();
                for (DataSnapshot produtoSnapshot : dataSnapshot.getChildren()){
                    Produto produto = produtoSnapshot.getValue(Produto.class);
                    produtoList.add(produto);
                }

                ProdutoAdapter adapter = new ProdutoAdapter(EstoqueActivity.this, produtoList);
                listViewProduto.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EstoqueActivity.this, "Erro ao recuperar os dados.", Toast.LENGTH_SHORT).show();
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
