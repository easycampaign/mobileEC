package br.com.easycampaign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    Animation atg, bgOne, bgTwo;
    ImageView imgSplash;
    TextView txtSplash, txtSubSplash;
    Button btnSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        imgSplash = findViewById(R.id.imgSplash);
        txtSplash = findViewById(R.id.txtSplash);
        txtSubSplash = findViewById(R.id.txtSubSplash);
        btnSplash = findViewById(R.id.btnStart);


        // importando a animacao
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        bgOne = AnimationUtils.loadAnimation(this, R.anim.btg_txt_one);
        bgTwo = AnimationUtils.loadAnimation(this, R.anim.btg_txt_two);

        // iniciando a animacao
        imgSplash.startAnimation(atg);
        txtSplash.startAnimation(bgOne);
        txtSubSplash.startAnimation(bgOne);
        btnSplash.startAnimation(bgTwo);


        // Evento ao clicar no botao da splash screen
        btnSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(StartActivity.this, LoginActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(it);
            }
        });


        // importando as fontes
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(), "fonts/MRegular.ttf");

        // customizando as fontes
        txtSplash.setTypeface(MRegular);
        txtSubSplash.setTypeface(MLight);
        btnSplash.setTypeface(MMedium);
    }
}
