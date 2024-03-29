package br.com.easycampaign.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.easycampaign.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardCampanhas, cardCampanhasAtivas, cardEstoque, cardAddProduto, cardCalendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardCampanhas = (CardView) findViewById(R.id.card_todas_campanhas);
        cardCampanhasAtivas = (CardView) findViewById(R.id.card_campanhas_ativas);
        cardEstoque = (CardView) findViewById(R.id.card_estoque);
        cardAddProduto = (CardView) findViewById(R.id.card_add_produto);

        cardCampanhas.setOnClickListener(this);
        cardCampanhasAtivas.setOnClickListener(this);
        cardEstoque.setOnClickListener(this);
        cardAddProduto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.card_todas_campanhas : intent = new Intent(this, CampanhasActivity.class);startActivity(intent);break;
            case R.id.card_campanhas_ativas : intent = new Intent(this, CadastroCampanhaActivity.class);startActivity(intent);break;
            case R.id.card_estoque : intent = new Intent(this, EstoqueActivity.class);startActivity(intent);break;
            //case R.id.card_adicionar_produto: intent = new Intent(this, CadastroProdutoActivity.class);startActivity(intent);break;
            //case R.id.card_calendario : intent = new Intent(this, BaseActivity.class);startActivity(intent);break;
        }
    }
}
