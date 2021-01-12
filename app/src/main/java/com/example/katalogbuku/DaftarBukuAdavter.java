package com.example.katalogbuku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.katalogbuku.model.Buku;

import java.util.List;

public class DaftarBukuAdavter extends ArrayAdapter<Buku> {
    Context context;
    public DaftarBukuAdavter(@NonNull Context context, @NonNull List<Buku> objects) {
        super(context, R.layout.row_tambah_buku, objects);
        this.context = context;
    }
    class ViewHolder {
        TextView txTgl;
        TextView txPengarang,txJudul,txKodeISBN,txJenis;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Buku tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_tambah_buku,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txTgl = convertView.findViewById(R.id.row_tx_tahun_terbit);
            viewHolder.txPengarang = convertView.findViewById(R.id.row_tx_pengarang_buku);
            viewHolder.txJudul = convertView.findViewById(R.id.row_tx_judul_buku);
            viewHolder.txKodeISBN = convertView.findViewById(R.id.row_tx_kode_isbn_buku);
            viewHolder.txJenis = convertView.findViewById(R.id.row_tx_jenis_buku);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txTgl.setText(GenerecUntelity.DATE_FORMAT.format(tr.getTanggal()));
        viewHolder.txPengarang.setText(tr.getPengarang());
        viewHolder.txJudul.setText(tr.getJudul());
        viewHolder.txKodeISBN.setText(tr.getKodeISBN());
        viewHolder.txJenis.setText(tr.getJenis());
        return convertView;

    }

}
