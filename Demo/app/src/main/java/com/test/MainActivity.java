package com.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.api.ApiConfig;
import com.test.model.Login;
import com.test.model.ResObj;
import com.test.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText userName, password;
    private Button btnLogin;
    private ApiConfig apiConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiConfig = new ApiConfig();

        userName = findViewById(R.id.userNameID);
        password = findViewById(R.id.passwordID);
        btnLogin = findViewById(R.id.loginBtn);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if (userName.getText().toString().isEmpty()){
            userName.setError("Required");
            userName.requestFocus();
            return;
        }

        if (password.getText().toString().isEmpty()){
            password.setError("Required");
            password.requestFocus();
            return;
        }

        Login login = new Login();
        login.setUsername(userName.getText().toString());
        login.setPassword(password.getText().toString());

        LoginService service = apiConfig.getRetrofit(login.getUsername(), login.getPassword()).create(LoginService.class);
        service.login(login).enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MapsActivity.class));
                }else {
                    Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
