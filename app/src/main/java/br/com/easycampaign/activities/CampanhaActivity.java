package br.com.easycampaign.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
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

public class CampanhaActivity extends AppCompatActivity {


    ListView listViewCampanha;
    List<Campanha> campanhaList;


    //Firebase
    DatabaseReference databaseCampanha;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanha);

        listViewCampanha = findViewById(R.id.listViewCampanha);
        campanhaList = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseCampanha = FirebaseDatabase.getInstance().getReference("contas").child(firebaseAuth.getCurrentUser().getUid()).child("campanhas");
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

                CampanhaAdapter adapter = new CampanhaAdapter(CampanhaActivity.this, campanhaList);
                listViewCampanha.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CampanhaActivity.this, "Erro ao recuperar os dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
