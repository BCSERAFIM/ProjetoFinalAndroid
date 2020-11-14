package com.bcserafim.projetoandroid.activity;

import android.content.Intent;
import android.os.Bundle;

import com.bcserafim.projetoandroid.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://192.168.15.11:8080/WebServiceAndroid/webresources/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()){
           case R.id.action_produto:
               startActivity(new Intent(this, ProdutoActivity.class ));
               return true;
           case R.id.action_usuario:
               startActivity(new Intent(this, UsuarioActivity.class ));
               return true;
               default:;

       }

        return super.onOptionsItemSelected(item);

            }




}