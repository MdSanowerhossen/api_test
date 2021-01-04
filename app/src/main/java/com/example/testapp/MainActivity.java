package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.testapp.Adapters.ItemAdapter;
import com.example.testapp.Network.IRetrofit;
import com.example.testapp.NetworkModel.TestResponse;
import com.example.testapp.Utils.Common;
import com.example.testapp.Utils.Utils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TestResponse.Data> list = new ArrayList<>();
    private ItemAdapter adapter;

    private CoordinatorLayout rootlayout;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private IRetrofit mIRetrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIRetrofit = Common.getApi();

        recyclerView = (RecyclerView)findViewById(R.id.mainRecyclerID);
        rootlayout = (CoordinatorLayout)findViewById(R.id.mainRootLayoutID);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemAdapter(list,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (Utils.broadcastIntent(this,rootlayout)){
            loadData();
        }else {
            NoInternetSnackBar();
        }
    }

    private void loadData() {
        compositeDisposable.add(mIRetrofit.getData().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<TestResponse.Data>>() {
            @Override
            public void accept(List<TestResponse.Data> response) throws Exception {
                list = response;
                adapter.updateList(list);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                snackBarMsg(throwable.getMessage());
            }
        }));
    }

    private void NoInternetSnackBar(){
        Snackbar snackbar = Snackbar.make(rootlayout,"No Internet Connection!",5000);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.broadcastIntent(MainActivity.this,rootlayout)){
                    loadData();
                }else {
                    NoInternetSnackBar();
                }
            }
        });

        snackbar.show();
    }

    private void snackBarMsg(String msg){
        Snackbar snackbar = Snackbar.make(rootlayout,""+msg,5000);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.broadcastIntent(MainActivity.this,rootlayout)){
                    loadData();
                }else {
                    NoInternetSnackBar();
                }
            }
        });

        snackbar.show();
    }

}