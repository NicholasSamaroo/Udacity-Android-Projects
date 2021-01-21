package com.example.googlebooks.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googlebooks.Models.Items;
import com.example.googlebooks.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Items> results;

    public BookAdapter(List<Items> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new BookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items item = results.get(position);

        holder.title.setText(item.getVolumeInfo().getTitle());

        StringBuilder stringBuilder = new StringBuilder();
        if(item.getVolumeInfo().getAuthors() == null) {
            String none = "No authors listed";
            holder.author.setText(none);
        } else {
            for(int i = 0; i < item.getVolumeInfo().getAuthors().size(); i++) {
                if(i == (item.getVolumeInfo().getAuthors().size() - 1)) {
                    stringBuilder.append(item.getVolumeInfo().getAuthors().get(i));
                    break;
                } else {
                    stringBuilder.append(item.getVolumeInfo().getAuthors().get(i)).append("\n");
                }
            }
            holder.author.setText(stringBuilder.toString());
        }

        if(item.getVolumeInfo().getCategories() == null) {
            String none = "No available categories";
            holder.categories.setText(none);
        } else {
            stringBuilder = new StringBuilder();
            for(int i = 0; i < item.getVolumeInfo().getCategories().size(); i++) {
                if(i == (item.getVolumeInfo().getCategories().size() - 1)) {
                    stringBuilder.append(item.getVolumeInfo().getCategories().get(i));
                    break;
                } else {
                    stringBuilder.append(item.getVolumeInfo().getCategories().get(i)).append("\n");
                }
            }
            holder.categories.setText(stringBuilder.toString());
        }

        holder.description.setText(item.getVolumeInfo().getDescription() == null
                ? "No description listed"
                : item.getVolumeInfo().getDescription());
    }

    @Override
    public int getItemCount() {
        if(results != null) {
            return results.size();
        } else {
            return 0;
        }
    }

    public void setResults(List<Items> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private TextView categories;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            categories = itemView.findViewById(R.id.categories);
            description = itemView.findViewById(R.id.description);
        }
    }
}
