package com.saunik.gitsample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.saunik.gitsample.api.ApiClient;
import com.saunik.gitsample.api.ApiInterface;
import com.saunik.gitsample.pojo.GitDetails;
import com.saunik.gitsample.pojo.GitItems;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitRepoListActivity extends BaseActivity implements OnItemClick {
    private List<GitItems> gitList = new ArrayList<>();
    private GitAdapter mAdapter;
    private int page = 1;
    String TAG = getClass().getName();
    private GitDetails gitDetails;
    private ProgressBar progressBar;
    private boolean loading = true;
    LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new GitAdapter(gitList, this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        // Pagination
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        progressBar = findViewById(R.id.progressBar);
        prepareGitItemsData();
    }

    private void prepareGitItemsData() {
        if (isNetworkEnabled(this)) {
            progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<GitDetails> gitListCall = apiService.getTopTrendingRepo("stars", "desc", page, 25, "l:android");
            gitListCall.enqueue(new Callback<GitDetails>() {
                @Override
                public void onResponse(@NonNull Call<GitDetails> call, @NonNull Response<GitDetails> response) {
                    gitDetails = response.body();
                    if (gitDetails != null) {
                        gitList.addAll(gitDetails.items);
                        mAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                    loading=true;
                }

                @Override
                public void onFailure(@NonNull Call<GitDetails> call, @NonNull Throwable t) {
                    Log.e(TAG, t.toString());
                    progressBar.setVisibility(View.GONE);
                    loading=true;
                    page--;
                }
            });
        } else {
            Toast.makeText(this, R.string.offline, Toast.LENGTH_SHORT).show();
        }
    }
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0) { // Check for scroll down
                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        page++;
                        prepareGitItemsData();
                    }
                }
            }
        }
    };

    @Override
    public void onItemClick(GitItems gitItems) {
        Intent intent = new Intent(this, GitDescActivity.class);
        intent.putExtra("desc", gitItems);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
