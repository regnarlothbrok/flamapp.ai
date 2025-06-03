package com.example.bookreviewapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookreviewapp.model.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM books")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM books WHERE isFavorite = 1")
    LiveData<List<Book>> getFavoriteBooks();

    @Query("SELECT * FROM books WHERE id = :id")
    LiveData<Book> getBookById(int id);
}
