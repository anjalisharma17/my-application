package com.example.wyw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Hire extends AppCompatActivity {

    private EditText addressEdt, phoneEdt, requirementEdt;
    private Button hireBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);

        addressEdt = findViewById(R.id.address);
        phoneEdt = findViewById(R.id.phone);
        requirementEdt = findViewById(R.id.purpose);
        hireBtn = findViewById(R.id.hire);

        dbHandler = new DBHandler(Hire.this);

        hireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String address = addressEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                String requirement = requirementEdt.getText().toString();

                if (TextUtils.isEmpty(address) || phone.isEmpty() || requirement.isEmpty()) {
                    Toast.makeText(Hire.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewData(address,phone,requirement);

                Toast.makeText(Hire.this, "your order has been received,please wait for call", Toast.LENGTH_SHORT).show();
                addressEdt.setText("");
                phoneEdt.setText("");
                requirementEdt.setText("");
                Intent intent=new Intent(Hire.this,MainActivity.class);
                startActivity(intent);
            }
        });
        /*Button show=findViewById(R.id.showBtn);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Hire.this,Show.class);
                startActivity(intent);
            }
        });
       */
    }
}