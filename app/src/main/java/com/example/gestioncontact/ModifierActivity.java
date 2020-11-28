package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifierActivity extends AppCompatActivity {

    private Button validButton;
    private EditText ednom;
    private TextView edprenom;
    private TextView edphone;
    private String nomExtra,prenomExtra,numeroExtra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        validButton = findViewById(R.id.validButton2);
        ednom = findViewById(R.id.editTextNom);
        edprenom = findViewById(R.id.editTextPrenom);
        edphone = findViewById(R.id.editTextPhone);

        if(AffichageActivity.which==-2){
            Intent in = this.getIntent();
            Bundle b = in.getExtras();
            nomExtra = b.getString("nom");
            prenomExtra = b.getString("prenom");
            numeroExtra = b.getString("numero");
            if(nomExtra!="" && prenomExtra!="" && numeroExtra!=""){
                ednom.setText(nomExtra);
                edprenom.setText(prenomExtra);
                edphone.setText(numeroExtra);
            }
        }

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = ednom.getText().toString();
                String prenom = edprenom.getText().toString();
                String phone = edphone.getText().toString();
                Contact c = new Contact(nom,prenom,phone);
                AcceuilActivity.data.remove(AffichageActivity.indice);
                AcceuilActivity.data.add(AffichageActivity.indice,c);
                AffichageActivity.lv.invalidateViews();
                finish();
            }
        });
    }

}