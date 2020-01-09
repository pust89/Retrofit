package com.pustovit.postapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pustovit.postapp.R;
import com.pustovit.postapp.model.User;
import com.pustovit.postapp.service.PostAppService;
import com.pustovit.postapp.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myTag";

    private EditText etEmail;
    private EditText etPassword;
    private Button btnSubmit;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSubmit = findViewById(R.id.btn_submit);
        tvResult = findViewById(R.id.tv_result);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
    }

    private void postData() {
        User user = new User();
        user.setUserEmail(etEmail.getText().toString());
        user.setPassword(etPassword.getText().toString());

        Log.i(TAG, "****************** Before id : "+user.getId());

        PostAppService postAppService = RetrofitInstance.getService();

        Call<User> call = postAppService.getResult(user);

        etEmail.setText("");
        etPassword.setText("");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User returnedUser = response.body();

                tvResult.setText(String.format("Id is %d", returnedUser.getId()));
                Log.i(TAG, "****************** After id : "+returnedUser.getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
