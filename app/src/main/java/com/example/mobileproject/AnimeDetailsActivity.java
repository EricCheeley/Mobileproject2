package com.example.mobileproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.widget.ImageView;
public class AnimeDetailsActivity extends AppCompatActivity {

    private List<String> animeList = Arrays.asList(
            "Naruto", "One Piece", "Bleach", "Dragon Ball",
            "Attack on Titan", "Death Note", "My Hero Academia",
            "Fullmetal Alchemist", "Demon Slayer", "Tokyo Ghoul",
            "Sword Art Online", "Hunter x Hunter", "Fairy Tail",
            "One Punch Man", "Steins;Gate"
    );

    // Add a map to store anime descriptions corresponding to their titles
    private Map<String, String> animeDescriptions = new HashMap<>();

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_details);

        // Initialize anime descriptions
        initializeAnimeDescriptions();

        sharedPreferences = getSharedPreferences("Bookmarks", MODE_PRIVATE);

        // Retrieve the anime title from intent
        String selectedAnime = getIntent().getStringExtra("animeTitle");

        // Display anime details including the title, genre, description, etc.
        displayAnimeDetails(selectedAnime);

        // Handle bookmark button click
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button bookmarkButton = findViewById(R.id.bookmarkButton);
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBookmarks(selectedAnime);
                Toast.makeText(AnimeDetailsActivity.this, "Bookmarked: " + selectedAnime, Toast.LENGTH_SHORT).show();
            }
        });

        // Additional button for generating recommended anime
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate random anime recommendations
                Intent intent = new Intent(AnimeDetailsActivity.this, RecommendedAnimeActivity.class);
                intent.putStringArrayListExtra("recommendedAnime", (ArrayList<String>) generateRandomRecommendations());
                startActivity(intent);
            }
        });
    }

    private void initializeAnimeDescriptions() {
        // Populate the map with descriptions for each anime title
        animeDescriptions.put("Naruto", "Naruto Uzumaki, a young ninja with a sealed demon fox within him, dreams of becoming the strongest ninja and leader of his village.");
        animeDescriptions.put("One Piece", "Monkey D. Luffy sets out on an adventure to find the ultimate treasure, the One Piece, and become the Pirate King.");
        animeDescriptions.put("Bleach", "Ichigo Kurosaki obtains the abilities of a Soul Reaper and battles evil spirits known as Hollows while navigating the afterlife.");
        animeDescriptions.put("Dragon Ball", "Goku, along with his companions, searches for the Dragon Balls to summon a wish-granting dragon and protect Earth from various threats.");
        animeDescriptions.put("Attack on Titan", "Eren Yeager joins the Survey Corps to fight against humanoid creatures called Titans and uncover the mysteries surrounding them.");
        animeDescriptions.put("Death Note", "Light Yagami discovers a supernatural notebook that allows him to kill anyone by writing their name, aiming to create a utopia by eliminating criminals.");
        animeDescriptions.put("My Hero Academia", "Izuku Midoriya aspires to become a hero despite being born without powers, attending U.A. High School to train and hone his abilities.");
        animeDescriptions.put("Fullmetal Alchemist", "Edward and Alphonse Elric seek the Philosopher's Stone to restore their bodies after a failed alchemical experiment.");
        animeDescriptions.put("Demon Slayer", "Tanjiro Kamado embarks on a quest to avenge his family and save his sister, who has turned into a demon.");
        animeDescriptions.put("Tokyo Ghoul", "Ken Kaneki, turned into a half-ghoul after an encounter, navigates the ghoul society while facing his inner struggles.");
        animeDescriptions.put("Sword Art Online", "Players get trapped in a virtual reality MMORPG, where death in the game means death in real life.");
        animeDescriptions.put("Hunter x Hunter", "Gon Freecss aims to become a Hunter and find his father, embarking on adventures and facing challenges.");
        animeDescriptions.put("Fairy Tail", "Lucy Heartfilia joins the Fairy Tail guild, forming a team with fellow wizards and going on adventures.");
        animeDescriptions.put("One Punch Man", "Saitama, a hero capable of defeating any opponent with a single punch, seeks excitement in his mundane hero life.");
        animeDescriptions.put("Steins;Gate", "Rintarou Okabe accidentally invents a time machine and gets involved in a conspiracy, attempting to alter the past to prevent disastrous outcomes.");
    }

    // Method to display anime details
    private void displayAnimeDetails(String selectedAnime) {
        // Retrieve and display anime details based on the selected anime
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(selectedAnime);

        // Display description based on the selected anime
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(getAnimeDescription(selectedAnime));

        // Load and display image based on the selected anime
        ImageView animeImageView = findViewById(R.id.animeImageView);
        int resourceId = getResources().getIdentifier(
                selectedAnime.toLowerCase().replace(" ", "_"),
                "drawable",
                getPackageName()
        );
        animeImageView.setImageResource(resourceId);
    }


    // Method to get anime description based on the title
    private String getAnimeDescription(String animeTitle) {
        // Retrieve description from the map using the anime title
        return animeDescriptions.get(animeTitle);
    }

    // Method to add anime to bookmarks
    private void addToBookmarks(String anime) {
        String bookmarks = sharedPreferences.getString("bookmarks", "");

        if (!bookmarks.contains(anime)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("bookmarks", bookmarks + anime + ",");
            editor.apply();
        }
    }

    // Method to generate random anime recommendations
    private List<String> generateRandomRecommendations() {
        List<String> recommendedAnime = new ArrayList<>();
        int numberOfRecommendations = 5; // Set the number of recommendations you want to display

        Random random = new Random();
        for (int i = 0; i < numberOfRecommendations; i++) {
            int randomIndex = random.nextInt(animeList.size());
            recommendedAnime.add(animeList.get(randomIndex));
        }

        return recommendedAnime;
    }



}
