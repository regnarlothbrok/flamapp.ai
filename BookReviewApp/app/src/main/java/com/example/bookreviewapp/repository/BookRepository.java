package com.example.bookreviewapp.repository;


import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.bookreviewapp.database.BookDao;
import com.example.bookreviewapp.database.BookDatabase;
import com.example.bookreviewapp.model.Book;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookRepository {
    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;
    private LiveData<List<Book>> favoriteBooks;
    private ExecutorService executor;

    public BookRepository(Application application) {
        BookDatabase database = BookDatabase.getInstance(application);
        bookDao = database.bookDao();
        allBooks = bookDao.getAllBooks();
        favoriteBooks = bookDao.getFavoriteBooks();
        executor = Executors.newFixedThreadPool(2);
    }

    public void insert(Book book) {
        executor.execute(() -> bookDao.insert(book));
    }

    public void update(Book book) {
        executor.execute(() -> bookDao.update(book));
    }

    public void delete(Book book) {
        executor.execute(() -> bookDao.delete(book));
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public LiveData<List<Book>> getFavoriteBooks() {
        return favoriteBooks;
    }

    public LiveData<Book> getBookById(int id) {
        return bookDao.getBookById(id);
    }
}
