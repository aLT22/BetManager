package com.bytebuilding.betmanager.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.betmanager.R;
import com.bytebuilding.betmanager.adapters.InactiveBetsRecyclerAdapter;
import com.bytebuilding.betmanager.data.Bet;
import com.bytebuilding.betmanager.database.DBHelper;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

public class InactiveBetsFragment extends Fragment {

    private DBHelper dbHelper;

    private List<Bet> listData;
    private InactiveBetsRecyclerAdapter inactiveBetsAdapter;

    private RecyclerView recyclerView;
    private SwipyRefreshLayout inactiveBetsContainer;

    public InactiveBetsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inactive_bets, container, false);

        dbHelper = new DBHelper(getContext().getApplicationContext());
        listData = dbHelper.getDoneBets();

        initContainer(rootView);

        initRecyclerView(rootView);

        return rootView;
    }

    private void initRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_inactive_bets);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        inactiveBetsAdapter = new InactiveBetsRecyclerAdapter(dbHelper.getDoneBets(), getContext()
                .getApplicationContext());

        recyclerView.setAdapter(inactiveBetsAdapter);
    }

    private void initContainer(View rootView) {
        inactiveBetsContainer = (SwipyRefreshLayout) rootView.findViewById(R.id.inactive_bets_container);

        inactiveBetsContainer.setDirection(SwipyRefreshLayoutDirection.BOTH);

        inactiveBetsContainer.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                refreshItems();
            }
        });
    }

    private void refreshItems() {
        listData.clear();
        listData = dbHelper.getDoneBets();

        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        inactiveBetsAdapter.updateRecyclerView(listData);

        inactiveBetsAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(listData.size());

        inactiveBetsContainer.setRefreshing(false);
    }
}
