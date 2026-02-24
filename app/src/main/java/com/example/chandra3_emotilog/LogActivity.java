/*
 * Purpose:
 * This screen shows the log of all emotions registered along with the timestamps.
 *
 * Design Rationale:
 * The logs are passed from MainActivity using Intent and displayed as plain text in a scrollable
 * list.
 *
 * Outstanding Issues:
 * If there was data from multiple days, right now there is no way to see the logs of a specific
 * date, they just show all of them sorted in the order they were clicked. I can add a
 * DatePickerDialog to make it more organized.
 */

package com.example.chandra3_emotilog;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        ArrayList<String> log =
                getIntent().getStringArrayListExtra("log");

        ListView listView = findViewById(R.id.logList);

        if (log != null) {
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(this,
                            android.R.layout.simple_list_item_1,
                            log);
            listView.setAdapter(adapter);
        }
    }
    public void goBack(View view) {
        finish();
    }
}