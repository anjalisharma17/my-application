package com.example.wyw;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    EditText mname,memail,mpassword,mphone;
    Button signup;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressBar progressBar;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mname=findViewById(R.id.nameEditText);
        memail=findViewById(R.id.emailEditText);
        mpassword=findViewById(R.id.passwordEditText);
        mphone=findViewById(R.id.phoneEditText);
        signup=findViewById(R.id.signup);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progress_bar);

        if(firebaseAuth.getCurrentUser()!=null)
        {
                Intent intent=new Intent(signup.this,MainActivity.class);
                startActivity(intent);
                finish();
        }
          signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=memail.getText().toString();
                String password=mpassword.getText().toString();
                String name=mname.getText().toString();
                String phone=mphone.getText().toString();
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
                if(TextUtils.isEmpty(name))
                {
                    mname.setError("Name can't be empty");
                    return;
                }
                if(TextUtils.isEmpty(phone))
                {
                    mphone.setError("Phone is required");
                    return;
                }
                if(password.length()<6)
                {
                    mpassword.setError("Password must be atleast  6 character");
                    return;
                }
                if(phone.length()!=10)
                {
                    mphone.setError("Enter a valid phone number");
                    return;
                }
                progressBar.setVisibility(view.VISIBLE);
                //register the user in firebase

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(signup.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            //storing all the data in firebase store
                            userID=firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=firebaseFirestore.collection("users").document(userID);
                            Map<String,Object> user=new HashMap<>();
                            user.put("name",name);
                            user.put("email",email);
                            user.put("password",password);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"on success: user profile is created for "+userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Log.d(TAG,"on failure: "+e.toString());
                                }
                            });

                            Intent intent=new Intent(signup.this,MainActivity.class);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(signup.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(view.GONE);
                        }
                    }
                });
            }
        });

        TextView login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(signup.this,login.class);
                startActivity(intent);
            }
        });
    }
}