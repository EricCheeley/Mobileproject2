package com.example.mobileproject; // Ensure this package name matches your project's package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RecommendedAnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_anime);

        TextView titleTextView = findViewById(R.id.titleTextView);

        // Retrieve recommended anime titles from Intent extras
        ArrayList<String> recommendedAnime = getIntent().getStringArrayListExtra("recommendedAnime");

        // Display the title in the TextView (optional)
        if (recommendedAnime != null && !recommendedAnime.isEmpty()) {
            StringBuilder recommendedAnimeText = new StringBuilder();
            for (String anime : recommendedAnime) {
                recommendedAnimeText.append(anime).append("\n");
            }
            titleTextView.setText(recommendedAnimeText.toString());
        }

        // Adding click listener to the button and navigating to Screen 2
        findViewById(R.id.homeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToScreen2();
            }
        });
    }

    // Function to navigate to Screen 2
    private void goToScreen2() {
        Intent intent = new Intent(this, AnimeListActivity.class);
        startActivity(intent);
    }
}
