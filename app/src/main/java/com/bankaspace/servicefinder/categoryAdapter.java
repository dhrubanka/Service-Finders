package com.bankaspace.servicefinder;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {


    private Context mCtx;
    private List<category> categoryList;
    private final OnItemClickListener listener;

    public categoryAdapter(Context mCtx, List<category> categoryList,OnItemClickListener listener) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(category item);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.category, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        category product = categoryList.get(position);
        holder.bind(categoryList.get(position), listener);

        //loading the image
        Glide.with(mCtx)
                .load(product.getImagepath())
                .into(holder.imageView);

        holder.textViewTitle.setText(product.getName());
        holder.textViewShortDesc.setText(product.getCategory());
        holder.textViewRating.setText(String.valueOf(product.getLocality()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final category item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
