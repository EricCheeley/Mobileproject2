package com.example.mobileproject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GeneralChatActivity extends AppCompatActivity {


    private List<GeneralChat> commentsList = new ArrayList<>(); // Use GeneralChat instead of Comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_chat);

        // TODO: Initialize UI components and set up layout
        // For instance, use a RecyclerView or ListView to display comments,
        // and an EditText and Button for users to add new comments.

        // Simulating comments (for example)
        commentsList.add(new GeneralChat("User1", "This is the first comment!"));
        commentsList.add(new GeneralChat("User2", "Nice chat!"));
        // You can add more comments as needed
    }

    // Method to add a new comment to the list
    private void addComment(String username, String commentContent) {
        GeneralChat newComment = new GeneralChat(username, commentContent);
        commentsList.add(newComment);
        // Update UI to display the new comment
        // For example, notify the adapter in RecyclerView or update the ListView
    }

    // Example method to simulate adding a comment on button click
    public void onAddCommentButtonClick(View view) {
        // Assume there's an EditText for username and another for comment content
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText commentEditText = findViewById(R.id.commentEditText);

        String username = usernameEditText.getText().toString();
        String commentContent = commentEditText.getText().toString();

        addComment(username, commentContent);
    }
}
