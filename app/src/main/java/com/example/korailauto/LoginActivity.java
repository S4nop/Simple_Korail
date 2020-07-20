package com.example.korailauto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtID, txtPW;
    private View.OnClickListener loginListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();

        btnLogin.setOnClickListener(loginListener);
        Notificator notificator = new Notificator(this);
    }

    private void initView(){
        btnLogin = findViewById(R.id.btnLogin);
        txtID = findViewById(R.id.txtID);
        txtPW = findViewById(R.id.txtPw);
    }

    private void initListener(){
         loginListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(){
                    public void run(){
                        Log.d("[LOG]", "btnLogin Thread started");
                        login_and_move();
                    }
                }.start();

            }
        };
    }

    private void login_and_move(){
        LoginRequest kf = new LoginRequest();
        Map<String, String> cookie;
        //if((cookie = kf.loginKorail(txtID.getText().toString(), txtPW.getText().toString())) != null){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //intent.putExtra("hashMap", (HashMap)cookie);
        startActivity(intent);
        //}

    }
}