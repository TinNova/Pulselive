package com.example.tin.pulselive.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tin.pulselive.R;
import com.example.tin.pulselive.models.content_detail.DetailItem;
import com.example.tin.pulselive.models.content_item.Item;
import com.example.tin.pulselive.utils.Const;
import com.example.tin.pulselive.utils.StateOfLoading;

import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING;
import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING_COMPLETE;
import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING_ERROR;


public class DetailActivity extends AppCompatActivity {

    private TextView titleTv;
    private TextView subtitleTv;
    private TextView dateTimeTv;
    private TextView idTv;
    private TextView bodyTv;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupViews();

    }

    private void setupViews() {

        Intent getIntent = getIntent();

        if (getIntent == null) {

            Toast.makeText(this, "Error loading data, please try again.", Toast.LENGTH_SHORT).show();

        } else {

            titleTv = findViewById(R.id.tV_title_detail);
            subtitleTv = findViewById(R.id.tV_subTitle_detail);
            dateTimeTv = findViewById(R.id.tV_dateTime_detail);
            idTv = findViewById(R.id.tV_id_detail);
            bodyTv = findViewById(R.id.tV_body_detail);
            mProgressBar = findViewById(R.id.pB_detail);


            Item item = getIntent.getParcelableExtra(Const.ARG_CONTENT_ITEM);
            int itemId = item.getId();

            bindOnViewModel(itemId);

        }
    }

    private void bindOnViewModel(int itemId) {

        DetailViewModel detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        detailViewModel.listenToStatesChanges(itemId).observe(this, new Observer<StateOfLoading.stateCodes>() {
            @Override
            public void onChanged(StateOfLoading.stateCodes state) {

                if (state.code == LOADING) {

                    showProgressBar();

                } else if (state.code == LOADING_COMPLETE) {

                    hideProgressBar();

                } else if (state.code == LOADING_ERROR) {

                    hideProgressBar();
                }
            }
        });


        detailViewModel.listenToDataChanges(itemId).observe(this, new Observer<DetailItem>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable DetailItem detailItem) {

                assert detailItem != null;
                setTitle(detailItem.getTitle());
                titleTv.setText(detailItem.getTitle());
                subtitleTv.setText(detailItem.getSubtitle());
                dateTimeTv.setText(detailItem.getDate());
                idTv.setText(String.valueOf(detailItem.getId()));
                bodyTv.setText(detailItem.getBody());

            }
        });
    }


    private void hideProgressBar() {

        mProgressBar.setVisibility(View.GONE);
        titleTv.setVisibility(View.VISIBLE);
        subtitleTv.setVisibility(View.VISIBLE);
        dateTimeTv.setVisibility(View.VISIBLE);
        idTv.setVisibility(View.VISIBLE);
        bodyTv.setVisibility(View.VISIBLE);
    }

    private void showProgressBar() {

        mProgressBar.setVisibility(View.VISIBLE);
        titleTv.setVisibility(View.GONE);
        subtitleTv.setVisibility(View.GONE);
        dateTimeTv.setVisibility(View.GONE);
        idTv.setVisibility(View.GONE);
        bodyTv.setVisibility(View.GONE);
    }
}
