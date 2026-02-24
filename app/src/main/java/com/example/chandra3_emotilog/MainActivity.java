/*
* Purpose:
* This is my home screen of the app, this is what opens when you first launch the app. Users can log
* their emotions here, they have 6 choices/ buttons, happy, sad, grateful, angry, calm and excited.
* There are also buttons that take the user to the logs screen and summary screen.
*
* Design Rationale:
* To keep the implementation simple, I log emotions and timestamps as strings stored in an ArrayList.
* I initially used toasts to give feedback, however they were really lagging when it came to
* continuous button presses and hence I switched to a status TextView
*
* Outstanding Issues:
* App can be made better if data was stored in a database instead of session persistence.
*/

package com.example.chandra3_emotilog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chandra3_emotilog.LogActivity;
import com.example.chandra3_emotilog.SummaryActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> emotionLog = new ArrayList<>();
    TextView textStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textStatus = findViewById(R.id.textStatus);

    }

    public void logHappy(View view) {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat("MMM d, yyyy 'at' hh:mm:ss a");
        String timestamp = formatter.format(new java.util.Date());
        emotionLog.add("Happy on " + timestamp);
        textStatus.setText("Happy logged");
        textStatus.postDelayed(() -> textStatus.setText(""), 500);
    }

    public void logSad(View view) {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat("MMM d, yyyy 'at' hh:mm:ss a");
        String timestamp = formatter.format(new java.util.Date());
        emotionLog.add("Sad on " + timestamp);
        textStatus.setText("Sad logged");
        textStatus.postDelayed(() -> textStatus.setText(""), 500);
    }

    public void logGrateful(View view) {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat("MMM d, yyyy 'at' hh:mm:ss a");
        String timestamp = formatter.format(new java.util.Date());
        emotionLog.add("Grateful on " + timestamp);
        textStatus.setText("Grateful logged");
        textStatus.postDelayed(() -> textStatus.setText(""), 500);
    }

    public void logAngry(View view) {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat("MMM d, yyyy 'at' hh:mm:ss a");
        String timestamp = formatter.format(new java.util.Date());
        emotionLog.add("Angry on " + timestamp);
        textStatus.setText("Angry logged");
        textStatus.postDelayed(() -> textStatus.setText(""), 500);
    }

    public void logCalm(View view) {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat("MMM d, yyyy 'at' hh:mm:ss a");
        String timestamp = formatter.format(new java.util.Date());
        emotionLog.add("Calm on " + timestamp);
        textStatus.setText("Calm logged");
        textStatus.postDelayed(() -> textStatus.setText(""), 500);
    }

    public void logExcited(View view) {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat("MMM d, yyyy 'at' hh:mm:ss a");
        String timestamp = formatter.format(new java.util.Date());
        emotionLog.add("Excited on " + timestamp);
        textStatus.setText("Excited logged");
        textStatus.postDelayed(() -> textStatus.setText(""), 500);
    }

    public void openLog(View view) {
        Intent intent = new Intent(this, LogActivity.class);
        intent.putStringArrayListExtra("log", emotionLog);
        startActivity(intent);
    }

    public void openSummary(View view) {
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putStringArrayListExtra("log", emotionLog);
        startActivity(intent);
    }

}
