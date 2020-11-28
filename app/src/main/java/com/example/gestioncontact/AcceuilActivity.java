package com.example.gestioncontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AcceuilActivity extends AppCompatActivity {

    public static ArrayList<Contact> data = new ArrayList<Contact>();
    private TextView AcceuilText;
    private Button ajout;
    private Button affichage;
    boolean permission_write = true; //false
    boolean once;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        AcceuilText = findViewById(R.id.AcceuilText);
        ajout = findViewById(R.id.ajout);
        affichage = findViewById(R.id.affichage);

        //once = true;

        Intent in = this.getIntent();
        Bundle b = in.getExtras();
        String s = b.getString("USER");
        if(s=="") s="USER";
        AcceuilText.setText("AcceuilActivity de " + s);

        ajout.setOnClickListener(v -> {
            Intent i = new Intent(AcceuilActivity.this, AjoutActivity.class);
            startActivity(i);
        });
        affichage.setOnClickListener(v -> {
            Intent i = new Intent(AcceuilActivity.this, AffichageActivity.class);
            startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            if(once){
                saveData();
                once = false;
            }

        }else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        //import
    }
    private void importData(){
        String dir = Environment.getExternalStorageDirectory().getPath();
        File f = new File(dir,"fichier.txt");
        if(f.exists()){
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String ligne = null;
                while((ligne = br.readLine())!=null) {
                    String[] t = ligne.split("#");
                    Contact c = new Contact(t[0], t[1], t[2]);
                    data.add(c);
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStop() {
        //save
        if(permission_write){
            importData();
            //AffichageActivity.lv.invalidateViews();
            //AcceuilActivity.data.clear();

        }else{
            Toast.makeText(this, "permission non autorisé", Toast.LENGTH_SHORT).show();
        }
        super.onStop();
    }

    private void saveData() {
        String dir = Environment.getExternalStorageDirectory().getPath();
        File f = new File(dir,"fichier.txt");
        try {
            FileWriter fw = new FileWriter(f,false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i=0;i<data.size();i++){
                bw.write(data.get(i).nom+"#"+data.get(i).prenom+"#"+data.get(i).numero+"\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                permission_write=true;
            }else{
                permission_write=false;
            }
        }
    }
}