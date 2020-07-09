package com.example.myapplication.ui.login;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.ui.ClearEditView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ClearEditView clearEditView = findViewById(R.id.password);
//        clearEditView.getEditView().setText("12332");

    }
}