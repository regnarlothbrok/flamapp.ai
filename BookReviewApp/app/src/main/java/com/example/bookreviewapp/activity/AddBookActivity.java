package com.example.bookreviewapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.example.bookreviewapp.R;
import com.example.bookreviewapp.model.Book;
import com.example.bookreviewapp.viewmodel.BookViewModel;

public class AddBookActivity extends AppCompatActivity {
    private TextInputEditText editTextTitle;
    private TextInputEditText editTextAuthor;
    private TextInputEditText editTextImageUrl;
    private TextInputEditText editTextDescription;
    private RatingBar ratingBarAdd;
    private Button buttonSave;
    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // Initialize views
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextImageUrl = findViewById(R.id.editTextImageUrl);
        editTextDescription = findViewById(R.id.editTextDescription);
        ratingBarAdd = findViewById(R.id.ratingBarAdd);
        buttonSave = findViewById(R.id.buttonSave);

        // Initialize ViewModel
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        // Setup toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Add New Book");
        }

        // Setup save button
        buttonSave.setOnClickListener(v -> saveBook());
    }

    private void saveBook() {
        String title = editTextTitle.getText().toString().trim();
        String author = editTextAuthor.getText().toString().trim();
        String imageUrl = editTextImageUrl.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        float rating = ratingBarAdd.getRating();

        // Validate input
        if (title.isEmpty()) {
            editTextTitle.setError("Title is required");
            editTextTitle.requestFocus();
            return;
        }

        if (author.isEmpty()) {
            editTextAuthor.setError("Author is required");
            editTextAuthor.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            editTextDescription.setError("Description is required");
            editTextDescription.requestFocus();
            return;
        }

        // Create and save book
        Book book = new Book(title, author, description, rating, imageUrl);
        bookViewModel.insert(book);

        Toast.makeText(this, "Book saved successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
