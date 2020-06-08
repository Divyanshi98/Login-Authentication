package com.example.loginauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    EditText txtEmail, txtPassword;
    Button btnSignIn;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtPassword = findViewById(R.id.editTextPassword);
        txtEmail = findViewById(R.id.editTextEmail);
        btnSignIn = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    private void UserLogin() {
        String email= txtEmail.getText().toString().trim();
        String password =txtPassword.getText().toString().trim();

        if(email.isEmpty()){
            txtEmail.setError("Email is required");
            txtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtEmail.setError("Please Enter a valid Email");
            txtEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            txtPassword.setError("Password is required");
            txtPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            txtPassword.setError("Minimum Length should be 6");
            txtPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override

                                           public void onComplete(@NonNull Task<AuthResult> task) {

                                               if(task.isSuccessful()){
                                                   Intent intent = new Intent(SignIn.this, Open.class);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                   startActivity(intent);
                                                   Toast.makeText(SignIn.this, "Welcome", Toast.LENGTH_SHORT).show();
                                               }
                                               else{
                                                   Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       }
                );

    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                UserLogin();
                break;

            default:
                break;
        }
    }

}
