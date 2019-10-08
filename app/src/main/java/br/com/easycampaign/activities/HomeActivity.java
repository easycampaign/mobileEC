package br.com.easycampaign.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.easycampaign.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardCampanhas, cardCampanhasAtivas, cardEstoque, cardDesempenho, cardCalendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardCampanhas = (CardView) findViewById(R.id.card_todas_campanhas);
        cardCampanhasAtivas = (CardView) findViewById(R.id.card_campanhas_ativas);
        cardEstoque = (CardView) findViewById(R.id.card_estoque);
        cardDesempenho = (CardView) findViewById(R.id.card_desempenho);
        //cardCalendario = (CardView) findViewById(R.id.card_calendario);

        cardCampanhas.setOnClickListener(this);
        cardCampanhasAtivas.setOnClickListener(this);
        cardEstoque.setOnClickListener(this);
        cardDesempenho.setOnClickListener(this);
        cardCalendario.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.card_todas_campanhas : intent = new Intent(this, CampanhaActivity.class);startActivity(intent);break;
            case R.id.card_campanhas_ativas : intent = new Intent(this, CadastroCampanhaActivity.class);startActivity(intent);break;
            case R.id.card_estoque : intent = new Intent(this, EstoqueActivity.class);startActivity(intent);break;
            case R.id.card_desempenho : intent = new Intent(this, DesempenhoActivity.class);startActivity(intent);break;
            //case R.id.card_calendario : intent = new Intent(this, BaseActivity.class);startActivity(intent);break;
            default:break;
        }
    }
}
