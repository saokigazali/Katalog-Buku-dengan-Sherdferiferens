package com.example.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class AkunActivity extends AppCompatActivity {
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        SharedPreferenceUtility session = new SharedPreferenceUtility(getApplicationContext());
        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblAlamat = (TextView) findViewById(R.id.lblAlamat);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

        btnLogout = (Button) findViewById(R.id.btnLogout);

        Toast.makeText(getApplicationContext(),
                "Status login : " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();

        if(session.checkLogin())
            finish();
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SharedPreferenceUtility.KEY_NAME);
        String alamat = user.get(SharedPreferenceUtility.KEY_ALAMAT);
        String email = user.get(SharedPreferenceUtility.KEY_EMAIL);

        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        lblAlamat.setText(Html.fromHtml("Alamat: <b>" + alamat + "</b>"));
        lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                session.logoutUser();
            }
        });
    }


}