package com.example.bookreviewapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.bookreviewapp.R;
import com.example.bookreviewapp.viewmodel.BookViewModel;

public class BookDetailActivity extends AppCompatActivity {
    private BookViewModel bookViewModel;
    private ImageView imageViewBookDetail;
    private TextView textViewTitleDetail;
    private TextView textViewAuthorDetail;
    private TextView textViewDescriptionDetail;
    private RatingBar ratingBarDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // Initialize views
        imageViewBookDetail = findViewById(R.id.imageViewBookDetail);
        textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        textViewAuthorDetail = findViewById(R.id.textViewAuthorDetail);
        textViewDescriptionDetail = findViewById(R.id.textViewDescriptionDetail);
        ratingBarDetail = findViewById(R.id.ratingBarDetail);

        // Initialize ViewModel
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        // Get book ID from intent
        int bookId = getIntent().getIntExtra("book_id", -1);
        if (bookId != -1) {
            loadBookDetails(bookId);
        }

        // Setup back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Book Details");
        }
    }

    private void loadBookDetails(int bookId) {
        bookViewModel.getBookById(bookId).observe(this, book -> {
            if (book != null) {
                textViewTitleDetail.setText(book.getTitle());
                textViewAuthorDetail.setText(book.getAuthor());
                textViewDescriptionDetail.setText(book.getDescription());
                ratingBarDetail.setRating(book.getRating());

                // Load image
                if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) {
                    Glide.with(this)
                            .load(book.getImageUrl())
                            .placeholder(R.drawable.ic_book_placeholder)
                            .error(R.drawable.ic_book_placeholder)
                            .into(imageViewBookDetail);
                } else {
                    imageViewBookDetail.setImageResource(R.drawable.ic_book_placeholder);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
