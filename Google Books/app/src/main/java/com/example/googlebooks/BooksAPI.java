package com.example.googlebooks;

import com.example.googlebooks.Models.Wrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksAPI {
    @GET("/books/v1/volumes")
    Call<Wrapper> getBooks(@Query("q") String q,
                           @Query("maxResults") int maxResults);
}
