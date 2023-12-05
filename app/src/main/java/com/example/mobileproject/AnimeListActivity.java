package com.example.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class AnimeListActivity extends AppCompatActivity {

    private ListView listViewAnime;
    private Button bookmarksButton;

    private static final String[] animeTitles = {
            "Naruto", "One Piece", "Bleach", "Dragon Ball",
            "Attack on Titan", "Death Note", "My Hero Academia",
            "Fullmetal Alchemist", "Demon Slayer", "Tokyo Ghoul",
            "Sword Art Online", "Hunter x Hunter", "Fairy Tail",
            "One Punch Man", "Steins;Gate"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);

        listViewAnime = findViewById(R.id.listViewAnime);
        bookmarksButton = findViewById(R.id.bookmarksButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                animeTitles
        );

        listViewAnime.setAdapter(adapter);

        listViewAnime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedAnime = animeTitles[position];
                Intent intent = new Intent(AnimeListActivity.this, AnimeDetailsActivity.class);
                intent.putExtra("animeTitle", selectedAnime);
                startActivity(intent);
            }
        });

        // Handle bookmarks button click to navigate to Screen5Activity
        bookmarksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimeListActivity.this, Bookmark.class);
                startActivity(intent);
            }
        });
    }
}