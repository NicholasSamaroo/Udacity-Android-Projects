package com.example.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int THREE_POINTS = 3;
    private static final int TWO_POINTS = 2;
    private static final int FREE_THROW = 1;

    private int aScore;
    private TextView teamAScore;

    private int bScore;
    private TextView teamBScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        aScore = 0;
        teamAScore = findViewById(R.id.teamAScore);
        Button teamA3Points = findViewById(R.id.teamA3Points);
        teamA3Points.setOnClickListener(this);
        Button teamA2Points = findViewById(R.id.teamA2Points);
        teamA2Points.setOnClickListener(this);
        Button teamAFreeThrow = findViewById(R.id.teamAFreeThrow);
        teamAFreeThrow.setOnClickListener(this);

        bScore = 0;
        teamBScore = findViewById(R.id.teamBScore);
        Button teamB3Points = findViewById(R.id.teamB3Points);
        teamB3Points.setOnClickListener(this);
        Button teamB2Points = findViewById(R.id.teamB2Points);
        teamB2Points.setOnClickListener(this);
        Button teamBFreeThrow = findViewById(R.id.teamBFreeThrow);
        teamBFreeThrow.setOnClickListener(this);

        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.teamA3Points:
                aScore += THREE_POINTS;
                teamAScore.setText(String.valueOf(aScore));
                break;
            case R.id.teamA2Points:
                aScore += TWO_POINTS;
                teamAScore.setText(String.valueOf(aScore));
                break;
            case R.id.teamAFreeThrow:
                aScore += FREE_THROW;
                teamAScore.setText(String.valueOf(aScore));
                break;
            case R.id.teamB3Points:
                bScore += THREE_POINTS;
                teamBScore.setText(String.valueOf(bScore));
                break;
            case R.id.teamB2Points:
                bScore += TWO_POINTS;
                teamBScore.setText(String.valueOf(bScore));
                break;
            case R.id.teamBFreeThrow:
                bScore += FREE_THROW;
                teamBScore.setText(String.valueOf(bScore));
                break;
            default:
                if (aScore == 0 && bScore == 0) {
                    Toast.makeText(this, "Values are already 0", Toast.LENGTH_SHORT).show();
                } else {
                    aScore = 0;
                    bScore = 0;
                    teamAScore.setText(String.valueOf(aScore));
                    teamBScore.setText(String.valueOf(bScore));
                }
        }
    }
}