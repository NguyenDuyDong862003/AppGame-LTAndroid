package com.example.appgame3.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appgame3.R;
import com.example.appgame3.database.DatabaseHelper;
import com.example.appgame3.database.LevelDAO;

import java.util.List;

public class LevelsActivity extends AppCompatActivity {
    private LevelDAO DAO;
    private DatabaseHelper dbHelper;
    private LinearLayout layoutLevelButtons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_levels_activioty);
        layoutLevelButtons = findViewById(R.id.layoutLevelButtons);
        dbHelper = new DatabaseHelper(this);
        DAO = LevelDAO.getInstance(dbHelper);
        loadLevels();
    }
    // load các level hiện có để tạo trang chọn level
    private void loadLevels() {
        List<Integer> levelIds = DAO.getAllLevels(dbHelper.getReadableDatabase());

        for (int levelId : levelIds) {
            Button button = new Button(this);
            button.setText("Level " + levelId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LevelsActivity.this, SokobanActivity.class);
                    intent.putExtra("LEVEL_NUMBER", levelId);
                    startActivity(intent);
                }
            });

            layoutLevelButtons.addView(button);
        }
    }
}