package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    private EditText mUser;
    private EditText mPassword;
    private Button mLoginin;
    private TextView mEsqueciSenha;
    private TextView mNovaConta;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";


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

        login();
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

    private void login(){
        mLoginin = findViewById(R.id.btnLogin);

        mLoginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mUser.getText().toString();
                String password = mPassword.getText().toString();
                 mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null){
            Intent intent = new Intent(this, ListaProdutosActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"E-mail ou senha incorreta", Toast.LENGTH_SHORT).show();
        }
    }

}