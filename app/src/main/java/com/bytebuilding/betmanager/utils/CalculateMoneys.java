package com.bytebuilding.betmanager.utils;

import android.content.Context;

import com.bytebuilding.betmanager.data.Bet;
import com.bytebuilding.betmanager.data.Rubbles;
import com.bytebuilding.betmanager.database.DBHelper;

import java.util.List;

/**
 * Created by Alexey on 27.10.2016.
 */

public class CalculateMoneys {

    private DBHelper dbHelper;

    private List<Bet> totalBetList;
    private List<Bet> winBetList;
    private List<Bet> lostBetList;

    public CalculateMoneys(Context context) {
        this.dbHelper = new DBHelper(context.getApplicationContext());

        totalBetList = dbHelper.getAllBets();
        winBetList = dbHelper.getDoneBets();
        lostBetList = dbHelper.getBadBets();
    }

    public Rubbles getTotalMoneyFromList(List<Bet> list) {
        int rub = 0;
        int cop = 0;
        for (Bet bet:
             list) {
            rub += bet.getMoneyBettedRub();
            cop += bet.getMoneyBettedCop();
        }
        Rubbles rubbles = new Rubbles(rub, cop);

        return rubbles;
    }

    public Rubbles getTotalWinMoneyFromList(List<Bet> list) {
        int rub = 0;
        int cop = 0;
        for (Bet bet:
                list) {
            rub += bet.getPossibleProfitRub();
            cop += bet.getPossibleProfitCop();
        }
        Rubbles rubbles = new Rubbles(rub, cop);

        return rubbles;
    }

    public List<Bet> getTotalBetList() {
        return totalBetList;
    }

    public List<Bet> getWinBetList() {
        return winBetList;
    }

    public List<Bet> getLostBetList() {
        return lostBetList;
    }

    public String getTotalProfit(Rubbles totalWin, Rubbles totalBetted) {
        String result = "";
        int totalWinInCop = 0;
        int totalBettedInCop = 0;

        totalWinInCop = totalWin.getCops() + totalWin.getRubs() * 100;
        totalBettedInCop = totalBetted.getCops() + totalBetted.getRubs() * 100;

        if (totalWinInCop > totalBettedInCop) {
            result += new Rubbles(0, totalWinInCop - totalBettedInCop).toString();
        } else {
            result = "-";
            result += new Rubbles(0, totalBettedInCop - totalWinInCop).toString();
        }

        return result;
    }
}