package br.com.easycampaign.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import br.com.easycampaign.R;


public class BaseActivity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;

    private CardView cardCampanhas, cardCampanhasAtivas, cardEstoque, cardAddProduto;
    private TextView txtNomeUsuario, txtEmailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
//        txtEmailUsuario = findViewById(R.id.txtEmailUsuario);
//        txtEmailUsuario.setText(email);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.abrir, R.string.fechar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.preto));

        navigationView = findViewById(R.id.navView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.optSair:
                        finish();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        trocaFragment(new InicialFragment());

        cardCampanhas = (CardView) findViewById(R.id.card_todas_campanhas);
        cardCampanhasAtivas = (CardView) findViewById(R.id.card_campanhas_ativas);
        cardEstoque = (CardView) findViewById(R.id.card_estoque);
        cardAddProduto = (CardView) findViewById(R.id.card_add_produto);
        //cardCalendario = (CardView) findViewById(R.id.card_calendario);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.card_todas_campanhas:
                intent = new Intent(this, CampanhasActivity.class);
                startActivity(intent);
                break;
            case R.id.card_campanhas_ativas:
                intent = new Intent(this, CadastroCampanhaActivity.class);
                startActivity(intent);
                break;
            case R.id.card_estoque:
                intent = new Intent(this, EstoqueActivity.class);
                startActivity(intent);
                break;
            case R.id.card_add_produto:
                intent = new Intent(this, CadastroProdutoActivity.class);
                startActivity(intent);
                break;
            case R.id.card_calendario:
                intent = new Intent(this, CalendarioActivity.class);
                startActivity(intent);
                break;
                //case R.id.card_calendario : intent = new Intent(this, BaseActivity.class);startActivity(intent);break;
        }
    }

    private void trocaFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
