package com.example.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class Bookmark extends AppCompatActivity {

    private ListView listViewBookmarks;
    private Button homeButton;

    private static final String[] bookmarkedAnimeTitles = {
            // List of bookmarked anime titles...
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        listViewBookmarks = findViewById(R.id.listViewBookmarks);
        homeButton = findViewById(R.id.homeButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookmarkedAnimeTitles
        );

        listViewBookmarks.setAdapter(adapter);

        // Handle click on bookmarked anime title
        listViewBookmarks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedAnime = bookmarkedAnimeTitles[position];
                // Navigate to Screen3Activity for the selected anime
                Intent intent = new Intent(Bookmark.this, AnimeDetailsActivity.class);
                intent.putExtra("animeTitle", selectedAnime);
                startActivity(intent);
            }
        });

        // Handle home button click to navigate to Screen2Activity
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bookmark.this, AnimeListActivity.class);
                startActivity(intent);
            }
        });
    }
}
