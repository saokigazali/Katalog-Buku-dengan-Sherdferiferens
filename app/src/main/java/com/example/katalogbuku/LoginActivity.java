package com.example.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtNama,txtAlamat,txtEmail;
    SharedPreferenceUtility session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SharedPreferenceUtility(getApplicationContext());
        txtNama =(EditText) findViewById(R.id.txtNama);
        txtAlamat =(EditText) findViewById(R.id.txtAlamat);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        Toast.makeText(getApplicationContext(),
                "Status login : " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String email = txtEmail.getText().toString();
                String nama = txtNama.getText().toString();
                String alamat = txtAlamat.getText().toString();


                session.createUserLoginSession(" "+nama, ""+email, ""+alamat);
                Intent i = new Intent(getApplicationContext(), AkunActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}