package com.example.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katalogbuku.model.Buku;

import java.util.List;

public class TambahBukuActivity extends AppCompatActivity {
    ListView lvDaftarBuku;
    TextView txNoData;
    DaftarBukuAdavter adapter;
    List<Buku> daftarBuku;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku);
        inisialisasiView();
        loadDataBuku();
        setupListview();
    }
    private void inisialisasiView() {
        lvDaftarBuku = findViewById(R.id.lv_buku);
        txNoData = findViewById(R.id.tx_nodata);
    }
    private void setupListview() {
        adapter = new DaftarBukuAdavter(this, daftarBuku);
        lvDaftarBuku.setAdapter(adapter);
    }
    private void loadDataBuku() {
        daftarBuku = SharedPreferenceUtility.getAllBuku(this);

    }
    private void refreshListView() {
        adapter.clear();
        loadDataBuku();
        adapter.addAll(daftarBuku);
    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }
}