package br.com.easycampaign.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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

import br.com.easycampaign.CampanhaAdapter;
import br.com.easycampaign.R;
import br.com.easycampaign.model.Campanha;

public class CampanhasActivity extends AppCompatActivity {

    public static final String CAMPANHA_NOME = "campanhanome";
    public static final String CAMPANHA_ID = "campanhaid";
    public static final String CAMPANHA_DATA_INICIO = "campanhaDataInicio";
    public static final String CAMPANHA_DATA_FINAL = "campanhaDataFinal";
    public static final String CAMPANHA_PRODUTOS = "campanhaProdutos";

    ListView listViewCampanha;
    List<Campanha> campanhaList;

    //Firebase
    DatabaseReference databaseCampanha;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanhas);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Variables inicialization
        listViewCampanha = findViewById(R.id.listViewCampanha);
        campanhaList = new ArrayList<>();

        //Firebase instances
        firebaseAuth = FirebaseAuth.getInstance();
        databaseCampanha = FirebaseDatabase.getInstance().getReference("contas").child(firebaseAuth.getCurrentUser().getUid()).child("campanhas");

        listViewCampanha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Campanha campanha = campanhaList.get(i);
                Intent intent = new Intent(getApplicationContext(), CampanhaActivity.class);
                intent.putExtra(CAMPANHA_ID, campanha.getCampanhaId());
                intent.putExtra(CAMPANHA_NOME, campanha.getCampanhaNome());
                intent.putExtra(CAMPANHA_DATA_INICIO, campanha.getCampanhaDataInicio());
                intent.putExtra(CAMPANHA_DATA_FINAL, campanha.getCampanhaDataFim());
                intent.putExtra(CAMPANHA_PRODUTOS, campanha.getCampanhaProduto());
                startActivity(intent);
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

    @Override
    protected void onStart() {
        super.onStart();

        databaseCampanha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                campanhaList.clear();
                for (DataSnapshot campanhaSnapshot : dataSnapshot.getChildren()){
                    Campanha campanha = campanhaSnapshot.getValue(Campanha.class);
                    campanhaList.add(campanha);
                }

                CampanhaAdapter adapter = new CampanhaAdapter(CampanhasActivity.this, campanhaList);
                listViewCampanha.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CampanhasActivity.this, "Erro ao recuperar os dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
