package com.example.btl;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.data.UserManager;
import com.example.btl.model.HistoryItem;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView listHistory;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listHistory = findViewById(R.id.listHistory);
        tvEmpty     = findViewById(R.id.tvEmpty);

        TextView tvBack = findViewById(R.id.tvBack);
        tvBack.setOnClickListener(v -> finish());

        String username = UserManager.getLoggedInUser(this);
        List<HistoryItem> items = UserManager.loadHistory(this, username);

        if (items.isEmpty()) {
            listHistory.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            listHistory.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            listHistory.setAdapter(new HistoryAdapter(this, items));
        }
    }

    // ── Inner Adapter ──────────────────────────────────────────────────────
    private static class HistoryAdapter extends ArrayAdapter<HistoryItem> {

        public HistoryAdapter(Context ctx, List<HistoryItem> items) {
            super(ctx, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_history, parent, false);
            }

            HistoryItem item = getItem(position);

            TextView tvQuestion = convertView.findViewById(R.id.tvHistoryQuestion);
            TextView tvSelected = convertView.findViewById(R.id.tvHistorySelected);
            TextView tvCorrect  = convertView.findViewById(R.id.tvHistoryCorrect);
            TextView tvResult   = convertView.findViewById(R.id.tvHistoryResult);

            tvQuestion.setText(item.getQuestionText());
            tvSelected.setText("Đã chọn: " + item.getSelectedAnswer());
            tvCorrect.setText("Đáp án đúng: " + item.getCorrectAnswer());

            if (item.isCorrect()) {
                tvResult.setText("Đúng ✓");
                tvResult.setTextColor(0xFF4CAF50);
            } else {
                tvResult.setText("Sai ✗");
                tvResult.setTextColor(0xFFF44336);
            }

            // Row background alternating
            convertView.setBackgroundColor(position % 2 == 0 ? 0xFFFFFFFF : 0xFFF5F5F5);

            return convertView;
        }
    }
}
