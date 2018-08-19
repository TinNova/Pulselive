package com.example.tin.pulselive.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tin.pulselive.listeners.ItemPositionListener;
import com.example.tin.pulselive.R;
import com.example.tin.pulselive.models.content_item.Item;
import com.example.tin.pulselive.utils.Const;
import com.example.tin.pulselive.utils.StateOfLoading;

import java.util.ArrayList;

import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING;
import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING_COMPLETE;
import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING_ERROR;

public class MainActivity extends AppCompatActivity implements ItemPositionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ContentAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        bindOnViewModel();
    }

    private void setupViews() {

        /* Setting up the RecyclerView and Adapter*/
        mRecyclerView = findViewById(R.id.rV_main);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new ContentAdapter(getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar = findViewById(R.id.pB_main);

        pullToRefresh();

    }

    private void bindOnViewModel() {

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.listenToStatesChanges().observe(this, new Observer<StateOfLoading.stateCodes>() {
            @Override
            public void onChanged(@Nullable StateOfLoading.stateCodes state) {

                if (state.code == LOADING) {

                    showProgressBar();

                } else if (state.code == LOADING_COMPLETE) {

                    hideProgressBar();

                } else if (state.code == LOADING_ERROR) {

                    hideProgressBar();

                }
            }
        });

        mainViewModel.listenToDataChanges().observe(this, new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Item> items) {

                mAdapter.addItems(items);

                hideProgressBar();
            }
        });
    }

    private void pullToRefresh() {

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.sRL_main);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mainViewModel.loadItems();
                pullToRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void coinItemClick(Item item) {

        Log.d(TAG, "clickedOn DetailItem: " + item);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Const.ARG_CONTENT_ITEM, item);
        startActivity(intent);
    }

    private void hideProgressBar() {

        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showProgressBar() {

        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }
}
