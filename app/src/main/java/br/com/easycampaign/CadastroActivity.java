package br.com.easycampaign;

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

import org.w3c.dom.Text;

public class CadastroActivity extends AppCompatActivity {

    TextView txtRegistrado;
    EditText edtEmail, edtSenha, edtConfirmacao;
    Button btnRegistro;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mFirebaseAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfirmacao = findViewById(R.id.edtConfirmacao);
        txtRegistrado = findViewById(R.id.txtRegistrado);
        btnRegistro = findViewById(R.id.btnRegistro);

        txtRegistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itLogin = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(itLogin);
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valido = true;
                String email = edtEmail.getText().toString().trim().toLowerCase();
                String senha = edtSenha.getText().toString();
                String confirmacao = edtConfirmacao.getText().toString();

                // Se o email estiver vazio
                if(TextUtils.isEmpty(email)){
                    valido = false;
                    edtEmail.setError("Por favor, digite um email.");
                    edtEmail.requestFocus();
                }else if(TextUtils.isEmpty(senha)){
                    valido=false;
                    edtSenha.setError("Por favor, digite uma senha.");
                    edtSenha.requestFocus();
                }else if(TextUtils.isEmpty(confirmacao)){
                    valido = false;
                    edtConfirmacao.setError("Por favor, confirme sua senha");
                    edtConfirmacao.requestFocus();
                }else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(senha)){
                    valido = false;
                    edtEmail.setError("Por favor, preencha os campos necessários.");
                    edtEmail.requestFocus();
                    Toast.makeText(CadastroActivity.this, "Por favor, preencha os campos necessários", Toast.LENGTH_SHORT).show();
                }

                if(valido){
                    adicionarUsuario(email, senha, confirmacao, valido);
                }else{
                    Toast.makeText(CadastroActivity.this, "Algo deu errado.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void adicionarUsuario(String email, String senha, String confirmacao, Boolean valido){
        // Validacao de confirmacao de senha
        if(senha.equals(confirmacao)){
            valido = true;
        }else{
            valido = false;
            Toast.makeText(this, "As senhas não se coincidem.", Toast.LENGTH_SHORT).show();
        }

        // Caso sucesso ao validar
        if(valido){
            mFirebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(CadastroActivity.this, "Falha ao se cadastrar.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CadastroActivity.this, "Usuario adicionado com sucesso.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CadastroActivity.this, HomeActivity.class));
                    }
                }
            });
        }
    }
}