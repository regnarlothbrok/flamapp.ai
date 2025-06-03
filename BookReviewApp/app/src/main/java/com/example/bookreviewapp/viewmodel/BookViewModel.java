package com.example.bookreviewapp.viewmodel;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bookreviewapp.model.Book;
import com.example.bookreviewapp.repository.BookRepository;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private BookRepository repository;
    private LiveData<List<Book>> allBooks;
    private LiveData<List<Book>> favoriteBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        allBooks = repository.getAllBooks();
        favoriteBooks = repository.getFavoriteBooks();
    }

    public void insert(Book book) {
        repository.insert(book);
    }

    public void update(Book book) {
        repository.update(book);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public LiveData<List<Book>> getFavoriteBooks() {
        return favoriteBooks;
    }

    public LiveData<Book> getBookById(int id) {
        return repository.getBookById(id);
    }
}
