package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.data.UserManager;

public class HomeActivity extends AppCompatActivity {

    private TextView tvCoins, tvHearts, tvWelcome, tvLogout;
    private Button btnPlayNow;
    private LinearLayout btnHistory, btnExchange;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Security: if not logged in → go to Login
        if (!UserManager.isLoggedIn(this)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        username = UserManager.getLoggedInUser(this);

        tvCoins   = findViewById(R.id.tvCoins);
        tvHearts  = findViewById(R.id.tvHearts);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvLogout  = findViewById(R.id.tvLogout);
        btnPlayNow = findViewById(R.id.btnPlayNow);
        btnHistory = findViewById(R.id.btnHistory);
        btnExchange = findViewById(R.id.btnExchange);

        updateUI();

        btnPlayNow.setOnClickListener(v ->
                startActivity(new Intent(this, TopicActivity.class)));

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));

        btnExchange.setOnClickListener(v ->
                showExchangeDialog());

        tvLogout.setOnClickListener(v -> {
            UserManager.logout(this);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        tvWelcome.setText("Xin chào, " + username + "! 👋");
        tvCoins.setText(String.valueOf(UserManager.getCoins(this, username)));
        tvHearts.setText(String.valueOf(UserManager.getHearts(this, username)));
    }

    private void showExchangeDialog() {
        int coins = UserManager.getCoins(this, username);
        new AlertDialog.Builder(this)
                .setTitle("Đổi Điểm 🎁")
                .setMessage("Điểm hiện tại của bạn: " + coins + "\n\n"
                        + "• 100 điểm = 1 lượt chơi thêm\n"
                        + "• 500 điểm = Gói trợ giúp đặc biệt\n\n"
                        + "Tính năng đang được phát triển...")
                .setPositiveButton("Đóng", null)
                .show();
    }
}
