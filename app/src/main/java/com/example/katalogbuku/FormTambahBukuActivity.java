package com.example.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.katalogbuku.model.Buku;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

public class FormTambahBukuActivity extends AppCompatActivity {
    Button btnSimpan;
    TextInputLayout tilPengarang,tilJudul,tilKoedeISBN;
    EditText edtTgl;
    Spinner spJenisBuku;
    Date tahunTerbit;
    final String[] tipeBuku = {Buku.NOVEL,Buku.PENDIDIKAN,Buku.PEMROGRAMAN, Buku.MAJALAH};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tambah_buku);
        inisialisasiView();
    }
    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        edtTgl = findViewById(R.id.edt_tgl);
        edtTgl.setOnClickListener(view -> pickDate());
        tilPengarang = findViewById(R.id.til_pengarang);
        tilJudul = findViewById(R.id.til_Judul);
        tilKoedeISBN = findViewById(R.id.til_kodeisbn);
        spJenisBuku = findViewById(R.id.spn_jenis);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tipeBuku);
        spJenisBuku.setAdapter(adapter);
        spJenisBuku.setSelection(0);
    }
    private void simpan() {
        if (isDataValid()) {
            Buku tr = new Buku();
            tr.setPengarang(tilPengarang.getEditText().getText().toString());
            tr.setJudul(tilJudul.getEditText().getText().toString());
            tr.setKodeISBN(tilKoedeISBN.getEditText().getText().toString());
            tr.setJenis(spJenisBuku.getSelectedItem().toString());
            tr.setTanggal(tahunTerbit);
            SharedPreferenceUtility.addDataBuku(this,tr);
            Toast.makeText(this,"Data Buku berhasil disimpan",Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
        }
    }
    private boolean isDataValid() {
        if (tilPengarang.getEditText().getText().toString().isEmpty()
                ||tilJudul.getEditText().getText().toString().isEmpty()
                ||tilKoedeISBN.getEditText().getText().toString().isEmpty()
                || spJenisBuku.getSelectedItem().toString().isEmpty()
        ) {
            Toast.makeText(this,"Lengkapi semua isian",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void pickDate() {
        final Calendar c = Calendar.getInstance();
        int thn = c.get(Calendar.YEAR);
        int bln = c.get(Calendar.MONTH);
        int tgl = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (DatePickerDialog.OnDateSetListener) (view, yyyy, mm, dd) -> {
                    edtTgl.setText(dd + "-" + (mm + 1) + "-" + yyyy);
                    c.set(yyyy,mm,dd);
                    tahunTerbit = c.getTime();
                },
                thn, bln, tgl);
        datePickerDialog.show();
    }

}