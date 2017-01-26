package com.bytebuilding.betmanager.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bytebuilding.betmanager.R;
import com.bytebuilding.betmanager.utils.CalculateMoneys;

public class MoneysFragment extends Fragment {

    private TextView totalBetted;
    private TextView totalWon;
    private TextView totalLost;
    private TextView totalProfit;

    private CalculateMoneys calculateMoneys;

    public MoneysFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_moneys, container, false);

        calculateMoneys = new CalculateMoneys(getContext());
        initViews(rootView);

        setConcrete();

        return rootView;
    }

    private void initViews(View rootView) {
        totalBetted = (TextView) rootView.findViewById(R.id.lbl_total_betted_moneys);
        totalWon = (TextView) rootView.findViewById(R.id.lbl_total_won_moneys);
        totalLost = (TextView) rootView.findViewById(R.id.lbl_total_lost_moneys);
        totalProfit = (TextView) rootView.findViewById(R.id.lbl_total_profit);
    }

    private void setConcrete() {
        totalBetted.setText(calculateMoneys.getTotalMoneyFromList(calculateMoneys.
                getTotalBetList()).toString());

        totalWon.setText(calculateMoneys.getTotalWinMoneyFromList(calculateMoneys.
                getWinBetList()).toString());

        totalLost.setText(calculateMoneys.getTotalMoneyFromList(calculateMoneys.
                getLostBetList()).toString());

        totalProfit.setText(calculateMoneys.getTotalProfit(calculateMoneys.
                getTotalWinMoneyFromList(calculateMoneys.
                getWinBetList()), calculateMoneys.getTotalMoneyFromList(calculateMoneys.
                getLostBetList())));
    }
}
