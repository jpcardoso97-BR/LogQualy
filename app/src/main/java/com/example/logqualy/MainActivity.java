package com.example.logqualy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText mUser;
    private EditText mPassword;
    private Button mLoginin;
    private TextView mEsqueciSenha;
    private TextView mNovaConta;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser = findViewById(R.id.editTextLoginEmail);
        mPassword = findViewById(R.id.editTextSenhaLogin);
        mLoginin = findViewById(R.id.btnLogin);
        mNovaConta = findViewById(R.id.txtNovaSenha);
        mEsqueciSenha = findViewById(R.id.txtEsqueciSenha);
        mAuth = FirebaseAuth.getInstance();

        criarConta();

    }

    private void criarConta(){
        mNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaProdutosActivity.class);
                startActivity(intent);
            }
        });
    }
}