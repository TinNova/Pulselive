package com.example.tin.pulselive;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tin.pulselive.models.content_detail.DetailItem;
import com.example.tin.pulselive.models.content_item.Item;
import com.example.tin.pulselive.utils.Const;


public class DetailActivity extends AppCompatActivity {

    private DetailViewModel detailViewModel;

    TextView titleTv;
    TextView subtitleTv;
    TextView dateTimeTv;
    TextView idTv;
    TextView bodyTv;

    Item item;

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

            item = getIntent.getParcelableExtra(Const.ARG_CONTENT_ITEM);
            int itemId = item.getId();

            bindOnViewModel(itemId);

//            pullToRefresh(itemId);

        }
    }

    private void bindOnViewModel(int itemId) {

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

//        detailViewModel.listenToStatesChanges(itemId).observe(this, new Observer<StateOfLoading.stateCodes>() {
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


        detailViewModel.listenToDataChanges(itemId).observe(this, new Observer<DetailItem>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable DetailItem detailItem) {


                titleTv.setText(detailItem.getTitle());
                subtitleTv.setText(detailItem.getSubtitle());
                dateTimeTv.setText(detailItem.getDate());
                idTv.setText(String.valueOf(detailItem.getId()));
                bodyTv.setText(detailItem.getBody());


//                hideProgressBar();

            }
        });
    }
}
