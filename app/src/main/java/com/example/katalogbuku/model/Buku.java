package com.example.katalogbuku.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Buku {
    public static final String NOVEL="NOVEL";
    public static final String PENDIDIKAN="PENDIDIKAN";
    public static final String PEMROGRAMAN="PEMROGRAMAN";
    public static final String MAJALAH="MAJALAH";
    private Date tanggal;
    private String pengarang;
    private String judul;
    private String kodeISBN;
    private String jenis;

    public Buku() {
        this.tanggal = new Date();
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    public String getPengarang() {
        return pengarang;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getJudul() {
        return judul;
    }

    public void setKodeISBN(String kodeISBN) {
        this.kodeISBN = kodeISBN;
    }
    public String getKodeISBN() {
        return kodeISBN;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public static Buku fromJSONObject(JSONObject obj) {
        Buku tr = new Buku();
        try {
            tr.setTanggal(new Date(obj.getLong("tanggal")));
            tr.setPengarang(obj.getString("pengarang"));
            tr.setJudul(obj.getString("judul"));
            tr.setJenis(obj.getString("jenis"));
            tr.setKodeISBN(obj.getString("kodeISBN"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }
    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("tanggal",this.tanggal.getTime());
            jsonObj.put("jenis",this.jenis);
            jsonObj.put("pengarang",this.pengarang);
            jsonObj.put("judul",this.judul);
            jsonObj.put("kodeISBN",this.kodeISBN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}


