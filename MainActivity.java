package com.example.tin.pulselive;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.tin.pulselive.models.ContentItemResponse;
import com.example.tin.pulselive.models.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemPositionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ContentAdapter mAdapter;
    private RecyclerView mRecyclerView;
//    private ProgressBar mProgressBar;
    MainViewModel mainViewModel;

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

//        mProgressBar = findViewById(R.id.pB_main);

////        pullToRefresh();
//
    }

    private void bindOnViewModel() {

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//
//        mainViewModel.listenToStatesChanges().observe(this, new Observer<StateOfLoading.stateCodes>() {
//            @Override
//            public void onChanged(@Nullable StateOfLoading.stateCodes state) {
//
//                if (state.code == LOADING) {
//
//                    showProgressBar();
//
//                } else if (state.code == LOADING_COMPLETE) {
//
//                    hideProgressBar();
//
//                } else if (state.code == LOADING_ERROR) {
//
//                    hideProgressBar();
//                    // or Toast or Alert Dialogue
//                }
//            }
//        });
//
        mainViewModel.listenToDataChanges().observe(this, new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Item> items) {

                mAdapter.addItems(items);

//                hideProgressBar();
            }
        });

    }

    @Override
    public void coinItemClick(Item item) {

        Log.d(TAG, "clickedOn Item: " + item);

    }
}
