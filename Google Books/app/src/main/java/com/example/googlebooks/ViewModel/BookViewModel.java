package com.example.googlebooks.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.googlebooks.Repository.BookRepository;
import com.example.googlebooks.Models.Items;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private final BookRepository bookRepository;
    private final LiveData<List<Items>> listLiveData;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository();
        listLiveData = bookRepository.getBookList();
    }

    public void searchBooks(String q, int maxResults) {
        bookRepository.searchTerm(q,maxResults);
    }

    public LiveData<List<Items>> getListLiveData() {
        return listLiveData;
    }
}
