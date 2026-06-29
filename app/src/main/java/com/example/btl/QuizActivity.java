package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.data.QuestionBank;
import com.example.btl.data.UserManager;
import com.example.btl.model.HistoryItem;
import com.example.btl.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_TOPIC = "extra_topic";

    // UI refs
    private TextView tvScore, tvTimer, tvQuestionNumber, tvTopicLabel, tvQuestion;
    private TextView tvAnswerA, tvAnswerB, tvAnswerC, tvAnswerD;
    private RadioButton rbA, rbB, rbC, rbD;
    private LinearLayout layoutA, layoutB, layoutC, layoutD;
    private Button btn5050, btnX2, btnPause, btnConfirm;

    // Data
    private List<Question> questions;
    private List<HistoryItem> historyItems = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;
    private int correctCount = 0;
    private int wrongCount = 0;
    private String topic;
    private String username;
    private int selectedAnswer = -1; // -1 = none selected
    private boolean answered = false;

    // Help states
    private boolean used5050 = false;
    private boolean usedX2 = false;
    private boolean x2Active = false;

    // Timer
    private CountDownTimer countDownTimer;
    private static final long TIMER_MS = 30_000;
    private long timeRemaining = TIMER_MS;
    private boolean timerPaused = false;

    private static final int POINTS_CORRECT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        topic    = getIntent().getStringExtra(EXTRA_TOPIC);
        username = UserManager.getLoggedInUser(this);

        initViews();

        questions = new ArrayList<>(QuestionBank.getQuestions(topic));
        Collections.shuffle(questions);
        // Take first 5 questions
        if (questions.size() > 5) questions = questions.subList(0, 5);

        loadQuestion();
    }

    private void initViews() {
        tvScore          = findViewById(R.id.tvScore);
        tvTimer          = findViewById(R.id.tvTimer);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvTopicLabel     = findViewById(R.id.tvTopicLabel);
        tvQuestion       = findViewById(R.id.tvQuestion);

        tvAnswerA = findViewById(R.id.tvAnswerA);
        tvAnswerB = findViewById(R.id.tvAnswerB);
        tvAnswerC = findViewById(R.id.tvAnswerC);
        tvAnswerD = findViewById(R.id.tvAnswerD);

        rbA = findViewById(R.id.rbA);
        rbB = findViewById(R.id.rbB);
        rbC = findViewById(R.id.rbC);
        rbD = findViewById(R.id.rbD);

        layoutA = findViewById(R.id.layoutAnswerA);
        layoutB = findViewById(R.id.layoutAnswerB);
        layoutC = findViewById(R.id.layoutAnswerC);
        layoutD = findViewById(R.id.layoutAnswerD);

        btn5050   = findViewById(R.id.btn5050);
        btnX2     = findViewById(R.id.btnX2);
        btnPause  = findViewById(R.id.btnPause);
        btnConfirm = findViewById(R.id.btnConfirm);

        // Answer click listeners
        layoutA.setOnClickListener(v -> selectAnswer(0));
        layoutB.setOnClickListener(v -> selectAnswer(1));
        layoutC.setOnClickListener(v -> selectAnswer(2));
        layoutD.setOnClickListener(v -> selectAnswer(3));

        rbA.setOnClickListener(v -> selectAnswer(0));
        rbB.setOnClickListener(v -> selectAnswer(1));
        rbC.setOnClickListener(v -> selectAnswer(2));
        rbD.setOnClickListener(v -> selectAnswer(3));

        btnConfirm.setOnClickListener(v -> onConfirmClicked());
        btn5050.setOnClickListener(v   -> use5050());
        btnX2.setOnClickListener(v     -> useX2());
        btnPause.setOnClickListener(v  -> togglePause());
    }

    private void loadQuestion() {
        if (currentIndex >= questions.size()) {
            finishGame();
            return;
        }

        answered = false;
        selectedAnswer = -1;
        x2Active = false;

        Question q = questions.get(currentIndex);

        // Reset UI
        resetAnswerStyles();
        clearRadioButtons();
        showAllAnswers();

        tvQuestionNumber.setText((currentIndex + 1) + "/" + questions.size());
        tvTopicLabel.setText(topic);
        tvQuestion.setText(q.getQuestionText());
        tvScore.setText(String.valueOf(score));

        tvAnswerA.setText("A. " + q.getOptionA());
        tvAnswerB.setText("B. " + q.getOptionB());
        tvAnswerC.setText("C. " + q.getOptionC());
        tvAnswerD.setText("D. " + q.getOptionD());

        btnConfirm.setText("XÁC NHẬN");
        btnConfirm.setEnabled(true);

        startTimer();
    }

    private void selectAnswer(int index) {
        if (answered) return;
        selectedAnswer = index;

        // Clear radio buttons
        rbA.setChecked(false);
        rbB.setChecked(false);
        rbC.setChecked(false);
        rbD.setChecked(false);

        // Set selected
        switch (index) {
            case 0: rbA.setChecked(true); break;
            case 1: rbB.setChecked(true); break;
            case 2: rbC.setChecked(true); break;
            case 3: rbD.setChecked(true); break;
        }
    }

    private void onConfirmClicked() {
        if (!answered) {
            // First press: check answer
            if (selectedAnswer == -1) {
                Toast.makeText(this, "Vui lòng chọn một đáp án!", Toast.LENGTH_SHORT).show();
                return;
            }
            stopTimer();
            checkAnswer();
            btnConfirm.setText("CÂU TIẾP");
        } else {
            // Second press: next question
            currentIndex++;
            loadQuestion();
        }
    }

    private void checkAnswer() {
        answered = true;
        Question q = questions.get(currentIndex);
        int correct = q.getCorrectAnswer();
        boolean isCorrect = (selectedAnswer == correct);

        // Color answers
        colorAnswers(correct, selectedAnswer);

        // Show correct label in question box
        String correctLabel = q.getCorrectAnswerLabel();
        tvQuestion.setText("Đáp án là " + correctLabel);

        // Update score
        if (isCorrect) {
            correctCount++;
            int pts = x2Active ? POINTS_CORRECT * 2 : POINTS_CORRECT;
            score += pts;
            tvScore.setText(String.valueOf(score));
        } else {
            wrongCount++;
        }

        // Save history
        String selectedText = (selectedAnswer >= 0)
                ? getLabelForIndex(selectedAnswer) + ". " + q.getOptionByIndex(selectedAnswer)
                : "Không chọn";
        historyItems.add(new HistoryItem(
                q.getQuestionText(),
                selectedText,
                q.getCorrectAnswerText(),
                isCorrect,
                topic));
    }

    private void colorAnswers(int correct, int selected) {
        LinearLayout[] layouts = {layoutA, layoutB, layoutC, layoutD};
        for (int i = 0; i < 4; i++) {
            if (i == correct) {
                layouts[i].setBackground(getDrawable(R.drawable.bg_answer_correct));
                getAnswerTextView(i).setTextColor(getColor(R.color.white));
            } else if (i == selected && selected != correct) {
                layouts[i].setBackground(getDrawable(R.drawable.bg_answer_wrong));
                getAnswerTextView(i).setTextColor(getColor(R.color.colorRed));
            }
        }
    }

    private TextView getAnswerTextView(int index) {
        switch (index) {
            case 0: return tvAnswerA;
            case 1: return tvAnswerB;
            case 2: return tvAnswerC;
            case 3: return tvAnswerD;
            default: return tvAnswerA;
        }
    }

    private String getLabelForIndex(int index) {
        switch (index) {
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            default: return "";
        }
    }

    // ── Help: 50/50 ───────────────────────────────────────────────────────────
    private void use5050() {
        if (used5050 || answered) {
            Toast.makeText(this, used5050 ? "Đã dùng trợ giúp 50/50!" : "", Toast.LENGTH_SHORT).show();
            return;
        }
        used5050 = true;
        btn5050.setBackgroundResource(R.drawable.bg_help_button_used);

        Question q = questions.get(currentIndex);
        int correct = q.getCorrectAnswer();

        // Hide two wrong answers (keep correct + one random wrong visible)
        List<Integer> wrong = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i != correct) wrong.add(i);
        }
        Collections.shuffle(wrong);
        // Hide first two wrong options
        hideAnswer(wrong.get(0));
        hideAnswer(wrong.get(1));

        Toast.makeText(this, "Loại bỏ 2 đáp án sai!", Toast.LENGTH_SHORT).show();
    }

    private void hideAnswer(int index) {
        LinearLayout[] layouts = {layoutA, layoutB, layoutC, layoutD};
        layouts[index].setVisibility(View.INVISIBLE);
    }

    // ── Help: X2 ──────────────────────────────────────────────────────────────
    private void useX2() {
        if (usedX2 || answered) {
            Toast.makeText(this, usedX2 ? "Đã dùng trợ giúp X2!" : "", Toast.LENGTH_SHORT).show();
            return;
        }
        usedX2 = true;
        x2Active = true;
        btnX2.setBackgroundResource(R.drawable.bg_help_button_used);
        Toast.makeText(this, "X2 điểm kích hoạt cho câu này! ✨", Toast.LENGTH_SHORT).show();
    }

    // ── Help: PAUSE ───────────────────────────────────────────────────────────
    private void togglePause() {
        if (!timerPaused) {
            stopTimer();
            timerPaused = true;
            btnPause.setText("TIẾP TỤC");
            Toast.makeText(this, "Đồng hồ đã dừng", Toast.LENGTH_SHORT).show();
        } else {
            timerPaused = false;
            btnPause.setText("PAUSE");
            resumeTimer();
        }
    }

    // ── Timer ────────────────────────────────────────────────────────────────
    private void startTimer() {
        stopTimer();
        timeRemaining = TIMER_MS;
        timerPaused = false;
        btnPause.setText("PAUSE");
        startCountDown(timeRemaining);
    }

    private void resumeTimer() {
        startCountDown(timeRemaining);
    }

    private void startCountDown(long millis) {
        countDownTimer = new CountDownTimer(millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                long seconds = millisUntilFinished / 1000;
                tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d",
                        seconds / 60, seconds % 60));
                // Red warning when ≤ 10 s
                if (seconds <= 10) {
                    tvTimer.setTextColor(getColor(R.color.colorRed));
                } else {
                    tvTimer.setTextColor(getColor(R.color.colorGreen));
                }
            }

            @Override
            public void onFinish() {
                tvTimer.setText("00:00");
                tvTimer.setTextColor(getColor(R.color.colorRed));
                if (!answered) {
                    // Auto-wrong when time runs out
                    Toast.makeText(QuizActivity.this, "⏰ Hết giờ!", Toast.LENGTH_SHORT).show();
                    answered = true;
                    wrongCount++;
                    Question q = questions.get(currentIndex);
                    colorAnswers(q.getCorrectAnswer(), -1);
                    tvQuestion.setText("Đáp án là " + q.getCorrectAnswerLabel());

                    historyItems.add(new HistoryItem(
                            q.getQuestionText(),
                            "Không chọn (hết giờ)",
                            q.getCorrectAnswerText(),
                            false,
                            topic));

                    btnConfirm.setText("CÂU TIẾP");
                }
            }
        }.start();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    // ── Reset helpers ────────────────────────────────────────────────────────
    private void resetAnswerStyles() {
        int bg = R.drawable.bg_answer_button;
        layoutA.setBackgroundResource(bg);
        layoutB.setBackgroundResource(bg);
        layoutC.setBackgroundResource(bg);
        layoutD.setBackgroundResource(bg);
        tvAnswerA.setTextColor(getColor(R.color.white));
        tvAnswerB.setTextColor(getColor(R.color.white));
        tvAnswerC.setTextColor(getColor(R.color.white));
        tvAnswerD.setTextColor(getColor(R.color.white));
    }

    private void showAllAnswers() {
        layoutA.setVisibility(View.VISIBLE);
        layoutB.setVisibility(View.VISIBLE);
        layoutC.setVisibility(View.VISIBLE);
        layoutD.setVisibility(View.VISIBLE);
    }

    private void clearRadioButtons() {
        rbA.setChecked(false);
        rbB.setChecked(false);
        rbC.setChecked(false);
        rbD.setChecked(false);
    }

    // ── Finish game ───────────────────────────────────────────────────────────
    private void finishGame() {
        stopTimer();

        // Save coins
        UserManager.addCoins(this, username, score);

        // Save history
        UserManager.saveHistory(this, username, historyItems);

        // Show congratulations dialog
        showCongratulationsDialog();
    }

    private void showCongratulationsDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_congratulation, null);

        TextView tvDialogScore   = dialogView.findViewById(R.id.tvDialogScore);
        TextView tvDialogTotal   = dialogView.findViewById(R.id.tvDialogTotal);
        TextView tvDialogCorrect = dialogView.findViewById(R.id.tvDialogCorrect);
        TextView tvDialogWrong   = dialogView.findViewById(R.id.tvDialogWrong);
        Button btnViewHistory    = dialogView.findViewById(R.id.btnViewHistory);
        Button btnPlayAgain      = dialogView.findViewById(R.id.btnPlayAgain);
        Button btnHome           = dialogView.findViewById(R.id.btnHome);

        tvDialogScore.setText("Điểm số: " + score);
        tvDialogTotal.setText("Tổng số câu hỏi: " + questions.size());
        tvDialogCorrect.setText("Số câu trả lời đúng: " + correctCount);
        tvDialogWrong.setText("Số câu trả lời sai: " + wrongCount);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        btnViewHistory.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(this, HistoryActivity.class));
        });

        btnPlayAgain.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(this, TopicActivity.class));
            finish();
        });

        btnHome.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}
