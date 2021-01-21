package com.example.googlebooks.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.googlebooks.Adapter.BookAdapter;
import com.example.googlebooks.Models.Items;
import com.example.googlebooks.R;
import com.example.googlebooks.ViewModel.BookViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static int RESULTS_RETURNED = 40;
    private EditText term;
    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRecyclerView();

        term = findViewById(R.id.query);
        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void initializeRecyclerView() {
        BookAdapter bookAdapter = new BookAdapter(null);
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        bookViewModel.getListLiveData().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(List<Items> items) {
                if(items != null) {
                    bookAdapter.setResults(items);
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void performSearch() {
        if(TextUtils.isEmpty(term.getText().toString())) {
            Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
        } else {
            bookViewModel.searchBooks(term.getText().toString(), RESULTS_RETURNED);
        }
    }
}