package com.example.wyw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText memail,mpassword;
    Button login;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memail=findViewById(R.id.emailView);
        mpassword=findViewById(R.id.passwordView);
        login=findViewById(R.id.login);

        firebaseAuth= FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progress_bar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=memail.getText().toString();
                String password=mpassword.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    memail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mpassword.setError("Password is required");
                    return;
                }
                progressBar.setVisibility(view.VISIBLE);
                //authenticate the user
                if(memail.getText().toString().equals("anjalisharma@gmail.com"))
                {
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(login.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(login.this,Show.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(login.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(view.GONE);
                            }
                        }
                    });
                }
                else
                {
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(login.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(login.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(login.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(view.GONE);
                            }
                        }
                    });
                }
            }
        });

        TextView signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });
    }
}