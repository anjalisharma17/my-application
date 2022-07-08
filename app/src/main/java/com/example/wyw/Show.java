package com.example.wyw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Show extends AppCompatActivity {

    private ArrayList<Modal> modalArrayList;
    private DBHandler dbHandler;
    private ModalAdapter modalAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ImageView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(Show.this,login.class);
                startActivity(intent);
                finish();
            }
        });

        modalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(Show.this);

        modalArrayList = dbHandler.readData();

        modalAdapter = new ModalAdapter( Show.this,modalArrayList);
        listView = findViewById(R.id.list_view);

        listView.setAdapter(modalAdapter);
    }
}