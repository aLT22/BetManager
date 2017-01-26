package com.bytebuilding.betmanager.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bytebuilding.betmanager.R;
import com.bytebuilding.betmanager.data.Bet;
import com.bytebuilding.betmanager.database.DBHelper;

import java.util.List;

/**
 * Created by Alexey on 11.10.2016.
 */

public class ActiveBetsRecyclerAdapter extends RecyclerView.Adapter<ActiveBetsRecyclerAdapter.ViewHolder> {

    private DBHelper dbHelper;

    private List<Bet> betList;

    private LayoutInflater inflater;

    public ActiveBetsRecyclerAdapter(List<Bet> betList, Context context) {
        this.betList = betList;
        this.inflater = LayoutInflater.from(context);
        this.dbHelper = new DBHelper(context.getApplicationContext());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bet_card_item_active, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Bet bet = betList.get(position);

        if (Build.VERSION.SDK_INT < 20) {
            holder.checkBox.setText("");
        }

        holder.sportName.setText(bet.getSport());
        holder.moneysBetted.setText(bet.getMoneyBetted());
        holder.caf.setText(String.valueOf(bet.getCaf()));
        holder.possibleProfit.setText(bet.getPossibleProfit());
        holder.checkBox.setSelected((bet.isDone()));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.checkBox.setSelected(isChecked);

                if (holder.checkBox.isChecked()) {
                    dbHelper.updateBetStatus(bet, true);
                } else {
                    dbHelper.updateBetStatus(bet, false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return betList.size();
    }

    public void updateRecyclerView(List<Bet> list) {
        this.betList.clear();
        this.betList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;

        private TextView sportName;
        private TextView moneysBetted;
        private TextView caf;
        private TextView possibleProfit;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.chb_isDone);

            sportName = (TextView) itemView.findViewById(R.id.lbl_sport_name);
            moneysBetted = (TextView) itemView.findViewById(R.id.lbl_moneys_bet);
            caf = (TextView) itemView.findViewById(R.id.lbl_coefficient);
            possibleProfit = (TextView) itemView.findViewById(R.id.lbl_possible_profit);
        }
    }
}