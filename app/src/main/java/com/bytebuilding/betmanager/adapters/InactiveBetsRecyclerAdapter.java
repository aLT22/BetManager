package com.bytebuilding.betmanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bytebuilding.betmanager.R;
import com.bytebuilding.betmanager.data.Bet;
import com.bytebuilding.betmanager.database.DBHelper;

import java.util.List;

/**
 * Created by Alexey on 27.10.2016.
 */

public class InactiveBetsRecyclerAdapter extends RecyclerView.Adapter<InactiveBetsRecyclerAdapter.ViewHolder> {

    private DBHelper dbHelper;

    private List<Bet> betList;

    private LayoutInflater inflater;

    @Override
    public InactiveBetsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bet_card_item_inactive, parent, false);
        return new InactiveBetsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InactiveBetsRecyclerAdapter.ViewHolder holder, int position) {
        final Bet bet = betList.get(position);

        holder.sportName.setText(bet.getSport());
        holder.moneysBetted.setText(bet.getMoneyBetted());
        holder.caf.setText(String.valueOf(bet.getCaf()));
        holder.possibleProfit.setText(bet.getPossibleProfit());
    }

    @Override
    public int getItemCount() {
        return betList.size();
    }

    public InactiveBetsRecyclerAdapter(List<Bet> betList, Context context) {
        this.dbHelper = new DBHelper(context.getApplicationContext());
        this.betList = betList;
        this.inflater = LayoutInflater.from(context);
    }

    public void updateRecyclerView(List<Bet> list) {
        this.betList.clear();
        this.betList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView sportName;
        private TextView moneysBetted;
        private TextView caf;
        private TextView possibleProfit;

        public ViewHolder(View itemView) {
            super(itemView);

            sportName = (TextView) itemView.findViewById(R.id.lbl_sport_name_inactive);
            moneysBetted = (TextView) itemView.findViewById(R.id.lbl_moneys_bet_inactive);
            caf = (TextView) itemView.findViewById(R.id.lbl_coefficient_inactive);
            possibleProfit = (TextView) itemView.findViewById(R.id.lbl_possible_profit_inactive);
        }
    }

}