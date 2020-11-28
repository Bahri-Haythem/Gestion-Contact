package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AjoutActivity extends AppCompatActivity {

    private EditText ednom;
    private TextView edprenom;
    private TextView edphone;
    private Button QuitButton2;
    private Button validButton2;
    String nomExtra="",prenomExtra="",numeroExtra="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        ednom = findViewById(R.id.editTextNom);
        edprenom = findViewById(R.id.editTextPrenom);
        edphone = findViewById(R.id.editTextPhone);
        QuitButton2 = findViewById(R.id.QuitButton2);
        validButton2 = findViewById(R.id.validButton2);

        validButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = ednom.getText().toString();
                String prenom = edprenom.getText().toString();
                String phone = edphone.getText().toString();
                Contact c = new Contact(nom,prenom,phone);
                com.example.gestioncontact.AcceuilActivity.data.add(c);
                ednom.setText("");
                edprenom.setText("");
                edphone.setText("");
                if(AffichageActivity.which==-2){
                    finish();
                    AffichageActivity.which=0;
                    AffichageActivity.lv.invalidateViews();
                }
            }
        });
        QuitButton2.setOnClickListener(v -> finish());
    }
}