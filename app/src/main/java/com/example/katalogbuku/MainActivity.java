package com.example.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton btnAkun,btnAddBook,btnMyBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
    }
    private void inisialisasiView() {
        btnAkun = findViewById(R.id.btn_akun);
        btnMyBook = findViewById(R.id.btn_my_book);
        btnAddBook = findViewById(R.id.btn_add_buku);
        btnAkun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,AkunActivity.class);
                startActivity(i);
            }
        });
        btnMyBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,TambahBukuActivity.class);
                startActivity(i);
            }
        });
        btnAddBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,FormTambahBukuActivity.class);
                startActivity(i);
            }
        });
    }}