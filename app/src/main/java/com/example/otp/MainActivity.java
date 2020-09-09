package com.example.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
 private EditText number;
 CountryCodePicker ccp;
 Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        number=findViewById(R.id.number);
        button=findViewById(R.id.button);
        ccp=findViewById(R.id.ccp);

        ccp.registerCarrierNumberEditText(number);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Number = number.getText().toString().trim();
                if (Number.isEmpty()|| Number.length()<10){
                    number.setError("Number is Requird");
                    number.requestFocus();
                    return;
                }

                Intent intent =new Intent(getApplicationContext(),verify_phone.class);
                intent.putExtra("phonenumber",ccp.getFullNumberWithPlus().replace("",""));
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent=new Intent(getApplicationContext(),profile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }
}