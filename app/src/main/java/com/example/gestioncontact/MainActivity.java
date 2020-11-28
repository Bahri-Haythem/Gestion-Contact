package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edName,edPass;
    private Button validButton;
    private Button exitButton;
    private TextView authTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authTextView = findViewById(R.id.authTextView);
        edName = findViewById(R.id.editTextTextPersonName);
        edPass = findViewById(R.id.editTextTextPassword);
        validButton = findViewById(R.id.validButton);
        exitButton = findViewById(R.id.ExitButton);

        exitButton.setOnClickListener(v -> finish());
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = edName.getText().toString();
                String mdp = edPass.getText().toString();

                if(nom.equals("test")&&mdp.equals("000")){
                    Intent i = new Intent(MainActivity.this, AcceuilActivity.class);
                    i.putExtra("USER",nom);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}