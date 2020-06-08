package com.example.loginauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.makeText;

public class SignUp extends AppCompatActivity {

    private Button btnRegister;

    private EditText txtFull_name,  txtEmail, pass, c_pass;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        btnRegister = findViewById(R.id.btn_signUp);


        txtEmail = findViewById(R.id.editTextEmail);
        txtFull_name = findViewById(R.id.editTextFullName);

        pass = findViewById(R.id.editTextPassword);
        c_pass = findViewById(R.id.editTextConfirmPassword);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_signUp).setOnClickListener(new View.OnClickListener() {


            private void registerUser(){
                final String gender =" ";

                final String fullName = txtFull_name.getText().toString().trim();
                final String email = txtEmail.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String confirm_pass = c_pass.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    User user = new User(
                                            fullName,

                                            email

                                    );

                                    Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {

                                                Intent intent = new Intent(SignUp.this,Open.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                                Toast.makeText(SignUp.this, "You have been Registered Successfully!", Toast.LENGTH_SHORT).show();

                                            } else {
                                                makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                                } else {
                                    makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }

                            }
                        });

            }
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_signUp:
                        registerUser();
                        break;

                    default:
                        break;
                }
            }
        });




    }
}