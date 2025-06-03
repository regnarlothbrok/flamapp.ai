package com.example.bookreviewapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookreviewapp.R;
import com.example.bookreviewapp.activity.BookDetailActivity;
import com.example.bookreviewapp.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books = new ArrayList<>();
    private Context context;
    private OnFavoriteClickListener favoriteClickListener;

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Book book);
    }

    public BookAdapter(Context context) {
        this.context = context;
    }

    public void setOnFavoriteClickListener(OnFavoriteClickListener listener) {
        this.favoriteClickListener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book currentBook = books.get(position);
        holder.textViewTitle.setText(currentBook.getTitle());
        holder.textViewAuthor.setText(currentBook.getAuthor());
        holder.textViewDescription.setText(currentBook.getDescription());
        holder.ratingBar.setRating(currentBook.getRating());

        // Load image using Glide
        if (currentBook.getImageUrl() != null && !currentBook.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(currentBook.getImageUrl())
                    .placeholder(R.drawable.ic_book_placeholder)
                    .error(R.drawable.ic_book_placeholder)
                    .into(holder.imageViewBook);
        } else {
            holder.imageViewBook.setImageResource(R.drawable.ic_book_placeholder);
        }

        // Set favorite button state
        holder.buttonFavorite.setImageResource(
                currentBook.isFavorite() ?
                        android.R.drawable.btn_star_big_on :
                        android.R.drawable.btn_star_big_off
        );

        // Set click listeners
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookDetailActivity.class);
            intent.putExtra("book_id", currentBook.getId());
            context.startActivity(intent);
        });

        holder.buttonFavorite.setOnClickListener(v -> {
            if (favoriteClickListener != null) {
                favoriteClickListener.onFavoriteClick(currentBook);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewAuthor;
        private TextView textViewDescription;
        private ImageView imageViewBook;
        private RatingBar ratingBar;
        private ImageButton buttonFavorite;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewBook = itemView.findViewById(R.id.imageViewBook);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            buttonFavorite = itemView.findViewById(R.id.buttonFavorite);
        }
    }
}
