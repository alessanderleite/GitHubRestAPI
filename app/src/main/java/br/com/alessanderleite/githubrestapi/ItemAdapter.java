package br.com.alessanderleite.githubrestapi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.alessanderleite.githubrestapi.controller.DetailActivity;
import br.com.alessanderleite.githubrestapi.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getLogin());
        viewHolder.githublink1.setText(items.get(i).getHtmlUrl());

        //Use Picasso to load images
        Picasso.get()
                .load(items.get(i).getAvatarUrl())
                .placeholder(R.drawable.load)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, githublink1;
        private ImageView imageView;

        public ViewHolder(@NonNull View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            githublink1 = (TextView) view.findViewById(R.id.githublink1);
            imageView = (ImageView) view.findViewById(R.id.cover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Item clickedDataItem = items.get(position);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("login", items.get(position).getLogin());
                        intent.putExtra("html_url", items.get(position).getHtmlUrl());
                        intent.putExtra("avatar_url", items.get(position).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked" + clickedDataItem.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}