package com.example.bookreviewapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.example.bookreviewapp.activity.AddBookActivity;
import com.example.bookreviewapp.adapter.BookAdapter;
import com.example.bookreviewapp.model.Book;
import com.example.bookreviewapp.viewmodel.BookViewModel;

public class MainActivity extends AppCompatActivity {
    private BookViewModel bookViewModel;
    private BookAdapter adapter;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        tabLayout = findViewById(R.id.tabLayout);
        FloatingActionButton fabAddBook = findViewById(R.id.fabAddBook);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookAdapter(this);
        recyclerView.setAdapter(adapter);

        // Initialize ViewModel
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        // Setup adapter click listener
        adapter.setOnFavoriteClickListener(book -> {
            book.setFavorite(!book.isFavorite());
            bookViewModel.update(book);
        });

        // Setup tab selection
        setupTabLayout();

        // Setup FAB
        fabAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        // Add some sample data if database is empty
        addSampleData();
    }

    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    // All Books tab
                    bookViewModel.getAllBooks().observe(MainActivity.this, books -> {
                        adapter.setBooks(books);
                    });
                } else {
                    // Favorites tab
                    bookViewModel.getFavoriteBooks().observe(MainActivity.this, books -> {
                        adapter.setBooks(books);
                    });
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Select first tab by default
        tabLayout.getTabAt(0).select();
        bookViewModel.getAllBooks().observe(this, books -> {
            adapter.setBooks(books);
        });
    }

    private void addSampleData() {
        // Add sample books for demonstration
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald",
                "A classic American novel set in the Jazz Age.", 4.2f, "");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee",
                "A gripping tale of racial injustice and childhood innocence.", 4.5f, "");
        Book book3 = new Book("1984", "George Orwell",
                "A dystopian social science fiction novel.", 4.3f, "");

        bookViewModel.insert(book1);
        bookViewModel.insert(book2);
        bookViewModel.insert(book3);
    }
}