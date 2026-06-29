package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.data.QuestionBank;

public class TopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        Button btnScience = findViewById(R.id.btnScience);
        Button btnCulture = findViewById(R.id.btnCulture);
        Button btnArt     = findViewById(R.id.btnArt);
        Button btnSport   = findViewById(R.id.btnSport);

        btnScience.setOnClickListener(v -> startQuiz(QuestionBank.TOPIC_SCIENCE));
        btnCulture.setOnClickListener(v -> startQuiz(QuestionBank.TOPIC_CULTURE));
        btnArt.setOnClickListener(v     -> startQuiz(QuestionBank.TOPIC_ART));
        btnSport.setOnClickListener(v   -> startQuiz(QuestionBank.TOPIC_SPORT));
    }

    private void startQuiz(String topic) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.EXTRA_TOPIC, topic);
        startActivity(intent);
    }
}
