package com.example.semestr2_pr2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String login="";
    String password="";
    String email="";
    TextView mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mes = (TextView) findViewById(R.id.message);
    }

    public void onMyButtonClick(View view)
    {
        EditText log = (EditText) findViewById(R.id.login);
        login = log.getText().toString();
        EditText em = (EditText) findViewById(R.id.email);
        email = em.getText().toString();
        EditText ps = (EditText) findViewById(R.id.password);
        password = ps.getText().toString();
        Checkers t = new Checkers();
        ApiClass a = new ApiClass();

        if(!t.checkLogin(login))
        {
            mes.setText("Логин введён некорректно");
            return;
        }
        else{
            mes.setText("");
        }
        if(!t.validateEmail(email)){
            mes.setText("Email некорректный");
            return;
        }
        else{
            mes.setText("");
        }
        if(!t.checkPassword(password)){
            mes.setText("Пароль некорректный");
            return;
        }
        else{
            mes.setText("");
        }

        if(a.postData("https://cakeapi.trinitytuts.com/api/add",login,email,password)){
            mes.setText("Данные успешно отправлены через API!");
        }
        else{
            mes.setText("При отправке данных возникла проблема!");
        }
    }
}