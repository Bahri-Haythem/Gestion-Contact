package com.example.gestioncontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AffichageActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    static ListView lv;
    static int indice;
    static int which = 0;
    private EditText search;
    private MonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);

        lv = findViewById(R.id.lv);
        //ArrayAdapter adapter = new ArrayAdapter(AffichageActivity.this,android.R.layout.simple_list_item_1, com.example.gestioncontact.AcceuilActivity.data);
        adapter = new MonAdapter(AffichageActivity.this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        search = (EditText) findViewById(R.id.editTextSearch);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //System.out.println("before:"+s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //System.out.println("after:"+s);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indice = position;
        AlertDialog.Builder alert = new AlertDialog.Builder(AffichageActivity.this);
        alert.setTitle("attention");
        alert.setMessage("veuillez choisir une action");
        alert.setPositiveButton("supprimer",this);
        alert.setNegativeButton("modifier",this);
        alert.setNeutralButton("supp tous",this);
        alert.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        this.which = which;
        if(which==dialog.BUTTON_POSITIVE) {
            AcceuilActivity.data.remove(indice);
            lv.invalidateViews();
        }
        if(which==dialog.BUTTON_NEGATIVE) {
            Intent i = new Intent(AffichageActivity.this, ModifierActivity.class);
            String nom = AcceuilActivity.data.get(indice).nom;
            String prenom = AcceuilActivity.data.get(indice).prenom;
            String numero = AcceuilActivity.data.get(indice).numero;
            i.putExtra("nom",nom);
            i.putExtra("prenom",prenom);
            i.putExtra("numero",numero);
            startActivity(i);
        }
        if(which==dialog.BUTTON_NEUTRAL) {
            com.example.gestioncontact.AcceuilActivity.data.clear();
            lv.invalidateViews();
        }
    }
}