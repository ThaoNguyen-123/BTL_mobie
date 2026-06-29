package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.data.UserManager;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etConfirmPassword;
    private TextView tvError, tvGoLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername        = findViewById(R.id.etUsername);
        etPassword        = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvError           = findViewById(R.id.tvError);
        tvGoLogin         = findViewById(R.id.tvGoLogin);
        btnRegister       = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> attemptRegister());

        tvGoLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void attemptRegister() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirm  = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            showError("Vui lòng nhập tên đăng nhập");
            return;
        }
        if (username.length() < 3) {
            showError("Tên đăng nhập phải có ít nhất 3 ký tự");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showError("Vui lòng nhập mật khẩu");
            return;
        }
        if (password.length() < 4) {
            showError("Mật khẩu phải có ít nhất 4 ký tự");
            return;
        }
        if (!password.equals(confirm)) {
            showError("Mật khẩu xác nhận không khớp");
            return;
        }

        if (UserManager.register(this, username, password)) {
            // Auto-login after register
            UserManager.login(this, username, password);
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            showError("Tên đăng nhập đã tồn tại, vui lòng chọn tên khác");
        }
    }

    private void showError(String msg) {
        tvError.setText(msg);
        tvError.setVisibility(View.VISIBLE);
    }
}
