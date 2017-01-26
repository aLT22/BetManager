package com.bytebuilding.betmanager.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.betmanager.R;
import com.bytebuilding.betmanager.adapters.ActiveBetsRecyclerAdapter;
import com.bytebuilding.betmanager.data.Bet;
import com.bytebuilding.betmanager.database.DBHelper;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

public class ActiveBetsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipyRefreshLayout activeBetsContainer;
    private DBHelper dbHelper;
    private List<Bet> listData;

    private ActiveBetsRecyclerAdapter activeBetsAdapter;

    public ActiveBetsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_active_bets, container, false);
        dbHelper = new DBHelper(getContext().getApplicationContext());
        listData = dbHelper.getAllBets();

        initContainer(rootView);

        initRecView(rootView);

        return rootView;
    }

    private void initRecView(View rootView) {

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_active_bets);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        activeBetsAdapter = new ActiveBetsRecyclerAdapter(dbHelper.getAllBets(), getContext()
                .getApplicationContext());

        recyclerView.setAdapter(activeBetsAdapter);
    }

    private void initContainer(View rootView) {
        activeBetsContainer = (SwipyRefreshLayout) rootView.findViewById(R.id.active_bets_container);

        activeBetsContainer.setDirection(SwipyRefreshLayoutDirection.BOTH);

        activeBetsContainer.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                refreshItems();
            }
        });
    }

    private void refreshItems() {
        listData.clear();
        listData = dbHelper.getAllBets();

        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        activeBetsAdapter.updateRecyclerView(listData);

        activeBetsAdapter.notifyDataSetChanged();

        recyclerView.smoothScrollToPosition(listData.size());

        activeBetsContainer.setRefreshing(false);
    }
}