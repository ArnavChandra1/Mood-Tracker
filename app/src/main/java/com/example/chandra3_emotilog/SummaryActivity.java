/*
 * Purpose:
 * This screen shows a daily summary of emotions, namely the number of times each emotion was
 * selected and its frequency (number of times each emoticon was pressed / total emoticons
 * logged for that day) displayed as a percentage.
 *
 * Design Rationale:
 * The emotion summaries are calculated by counting the entries for each emotion in the log for the
 * selected date.
 *
 * Outstanding Issues:
 * Currently, summary logs store emotions as strings as it's simple and sufficient for this task.
 * However, we can improve it by storing data in a more structured method like objects or databases.
 */

package com.example.chandra3_emotilog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Button buttonPickDate = findViewById(R.id.buttonPickDate);
        TextView textMessage = findViewById(R.id.textMessage);

        TextView textHappy = findViewById(R.id.textHappyCount);
        TextView textSad = findViewById(R.id.textSadCount);
        TextView textGrateful = findViewById(R.id.textGratefulCount);
        TextView textAngry = findViewById(R.id.textAngryCount);
        TextView textCalm = findViewById(R.id.textCalmCount);
        TextView textExcited = findViewById(R.id.textExcitedCount);

        textHappy.setVisibility(View.GONE);
        textSad.setVisibility(View.GONE);
        textAngry.setVisibility(View.GONE);
        textCalm.setVisibility(View.GONE);
        textExcited.setVisibility(View.GONE);
        textGrateful.setVisibility(View.GONE);


        ArrayList<String> log =
                getIntent().getStringArrayListExtra("log");

        /*
         The following function is from GeeksforGeeks:
         https://www.geeksforgeeks.org/android/datepickerdialog-in-android/, 2/3/26
        */
        buttonPickDate.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    SummaryActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {

                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(selectedYear, selectedMonth, selectedDay);

                        SimpleDateFormat dateFormat =
                                new SimpleDateFormat("MMM d, yyyy");

                        String selectedDateString =
                                dateFormat.format(selectedDate.getTime());

                        updateSummaryForDate(
                                selectedDateString,
                                log,
                                textMessage,
                                textHappy,
                                textSad,
                                textAngry,
                                textCalm,
                                textExcited,
                                textGrateful
                        );
                    },
                    year, month, day
            );

            datePickerDialog.show();
        });
    }

    private void updateSummaryForDate(
            String date,
            ArrayList<String> log,
            TextView textMessage,
            TextView textHappy,
            TextView textSad,
            TextView textAngry,
            TextView textCalm,
            TextView textExcited,
            TextView textGrateful
    ) {

        int happy = 0, sad = 0, angry = 0, calm = 0, excited = 0, grateful = 0;

        if (log != null) {
            for (String entry : log) {
                if (entry.contains(date)) {
                    if (entry.startsWith("Happy")) happy++;
                    else if (entry.startsWith("Sad")) sad++;
                    else if (entry.startsWith("Grateful")) grateful++;
                    else if (entry.startsWith("Angry")) angry++;
                    else if (entry.startsWith("Calm")) calm++;
                    else if (entry.startsWith("Excited")) excited++;
                }
            }
        }

        int total = happy + sad + angry + calm + excited + grateful;

        if (total == 0) {
            textMessage.setText("No emotions logged on " + date);

            textHappy.setVisibility(View.GONE);
            textSad.setVisibility(View.GONE);
            textAngry.setVisibility(View.GONE);
            textCalm.setVisibility(View.GONE);
            textExcited.setVisibility(View.GONE);
            textGrateful.setVisibility(View.GONE);

        } else {
            textMessage.setText("Summary for " + date);

            textHappy.setVisibility(View.VISIBLE);
            textSad.setVisibility(View.VISIBLE);
            textAngry.setVisibility(View.VISIBLE);
            textCalm.setVisibility(View.VISIBLE);
            textExcited.setVisibility(View.VISIBLE);
            textGrateful.setVisibility(View.VISIBLE);

            textHappy.setText("Happy: " + formatCountAndFrequency(happy, total));
            textSad.setText("Sad: " + formatCountAndFrequency(sad, total));
            textAngry.setText("Angry: " + formatCountAndFrequency(angry, total));
            textCalm.setText("Calm: " + formatCountAndFrequency(calm, total));
            textExcited.setText("Excited: " + formatCountAndFrequency(excited, total));
            textGrateful.setText("Grateful: " + formatCountAndFrequency(grateful, total));

        }
    }
    private String formatCountAndFrequency(int count, int total) {
        if (total == 0) {
            return count + " (0%)";
        }
        int percentage = (int) Math.round((count * 100.0) / total);
        return count + " (" + percentage + "%)";
    }
    public void goBack(View view) {
        finish();
    }
}




