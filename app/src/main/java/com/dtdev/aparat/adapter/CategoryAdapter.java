package com.dtdev.aparat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dtdev.aparat.R;
import com.dtdev.aparat.activites.CategoryVideoActivity;
import com.dtdev.aparat.models.Category;
import com.dtdev.aparat.models.IResponseListener;
import com.dtdev.aparat.models.Video;
import com.dtdev.aparat.webService.WebServiceCaller;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    List<Category> categoryList;
    LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @NotNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.category_row, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryAdapter.CategoryViewHolder holder, int position) {

        Category category = categoryList.get(position);

        holder.txvTitle.setText(category.getTitle());
        Picasso.get().load(category.getIcon()).into(holder.imageView);
        holder.cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryVideoActivity.class);
                intent.putExtra("categoryVideo", category);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imageView;
        AppCompatTextView txvTitle;
        CardView cardCategory;

        public CategoryViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_category);
            txvTitle = itemView.findViewById(R.id.txv_title);
            cardCategory = itemView.findViewById(R.id.card_category);
        }
    }
}
