package com.saunik.gitsample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saunik.gitsample.pojo.GitItems;

import java.util.List;

/**
 * @author Saunik Singh on 27/8/18.
 * Cars24 Services Private Limited.
 */
public class GitAdapter extends RecyclerView.Adapter<GitAdapter.MyViewHolder> {
    private List<GitItems> gitItemsList;
    private OnItemClick mOnItemClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        ImageView imageView;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            desc = view.findViewById(R.id.desc);
            imageView = view.findViewById(R.id.image_id);
        }

        void bind(final GitItems gitItems) {
            title.setText(gitItems.name);
            desc.setText(gitItems.description);
            if (gitItems.owner != null)
                Glide.with(imageView).setDefaultRequestOptions(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.mipmap.ic_launcher))
                        .load(gitItems.owner.avatar_url).into(imageView);
            else
                Glide.with(imageView).load(R.mipmap.ic_launcher).into(imageView);
            itemView.setOnClickListener(v -> mOnItemClick.onItemClick(gitItems));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        itemView.setOnClickListener(v -> mOnItemClick.onItemClick(gitItemsList.get(i)));
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(gitItemsList.get(i));
    }

    @Override
    public int getItemCount() {
        return gitItemsList.size();
    }

    GitAdapter(List<GitItems> pGitItemsList, OnItemClick onItemClick) {
        this.gitItemsList = pGitItemsList;
        mOnItemClick = onItemClick;
    }
}
