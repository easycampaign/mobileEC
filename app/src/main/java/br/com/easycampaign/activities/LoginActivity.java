package br.com.easycampaign.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.easycampaign.R;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    EditText edtLogin, edtSenha;
    Button btnLogin;
    TextView txtRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegistro = findViewById(R.id.txtRegistro);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText(LoginActivity.this, "Bem vindo", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(LoginActivity.this, BaseActivity.class);
                    //startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this, "Erro ocorreu ao se autentificar", Toast.LENGTH_SHORT).show();
                }

            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valido = true;
                String email = edtLogin.getText().toString().trim().toLowerCase();
                String senha = edtSenha.getText().toString();

                // Se o email estiver vazio
                if(TextUtils.isEmpty(email)){
                    valido = false;
                    edtLogin.setError("Por favor, digite um email.");
                    edtLogin.requestFocus();
                }else if(TextUtils.isEmpty(senha)){
                    valido=false;
                    edtSenha.setError("Por favor, digite uma senha.");
                    edtSenha.requestFocus();
                }else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(senha)){
                    valido=false;
                    edtLogin.setError("Por favor, preencha os campos necessários.");
                    edtLogin.requestFocus();
                    Toast.makeText(LoginActivity.this, "Por favor, preencha os campos necessários", Toast.LENGTH_SHORT).show();
                }

                if(valido){
                    mFirebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Falha ao se autenticar, tente novamente.", Toast.LENGTH_SHORT).show();
                            }else{
                                mFirebaseAuth.addAuthStateListener(mAuthStateListener);
                                Intent intentHome = new Intent(LoginActivity.this, BaseActivity.class);
                                startActivity(intentHome);
                            }
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Algo deu errado.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
