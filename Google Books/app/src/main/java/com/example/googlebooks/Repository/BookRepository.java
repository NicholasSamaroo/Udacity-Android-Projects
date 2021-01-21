package com.example.googlebooks.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.googlebooks.BooksAPI;
import com.example.googlebooks.Models.Items;
import com.example.googlebooks.Models.Wrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BASE_URL = "https://www.googleapis.com/";
    private final BooksAPI booksAPI;
    private final MutableLiveData<List<Items>> itemLiveData;

    public BookRepository() {
        itemLiveData = new MutableLiveData<>();

        booksAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BooksAPI.class);
    }

    public void searchTerm(String q, int maxResults) {
        booksAPI.getBooks(q,maxResults)
                .enqueue(new Callback<Wrapper>() {
                    @Override
                    public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            itemLiveData.postValue(response.body().getItems());
                        }
                    }

                    @Override
                    public void onFailure(Call<Wrapper> call, Throwable t) {
                        itemLiveData.postValue(null);
                    }
                });
    }

    public LiveData<List<Items>> getBookList() {
        return itemLiveData;
    }
}
