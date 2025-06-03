package com.example.bookreviewapp.api;


import com.example.bookreviewapp.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookApiService {
    @GET("books") // This would be your actual API endpoint
    Call<List<Book>> getBooks();
}
