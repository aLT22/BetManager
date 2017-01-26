package com.bytebuilding.betmanager.data;

import java.util.ArrayList;
import java.util.List;

public class Bet {

    public long getBet_id() {
        return bet_id;
    }

    private long bet_id;
    private String sport;
    private Rubbles moneyBetted;
    private double caf;
    private Rubbles possibleProfit;
    private int isDone;

    public Bet(long bet_id, String sport, Rubbles moneyBetted, double caf, int isDone) {
        this.bet_id = bet_id;
        this.sport = sport;
        this.moneyBetted = moneyBetted;
        this.caf = caf;
        Rubbles rubbles = new Rubbles(0,
                (int)((this.moneyBetted.getRubs() * 100 + this.moneyBetted.getCops())*caf));
        this.possibleProfit = rubbles;
        this.isDone = isDone;
    }

    public String getSport() {
        return sport;
    }

    public String getMoneyBetted() {
        return moneyBetted.toString();
    }

    public int getMoneyBettedRub() {
        return this.moneyBetted.getRubs();
    }

    public int getMoneyBettedCop() {
        return this.moneyBetted.getCops();
    }

    public int getPossibleProfitRub() {
        return this.possibleProfit.getRubs();
    }

    public int getPossibleProfitCop() {
        return this.possibleProfit.getCops();
    }

    public double getCaf() {
        return caf;
    }

    public String getPossibleProfit() {
        return possibleProfit.toString();
    }

    public boolean isDone() {
        if (this.isDone == 0) return false;

        return true;
    }

    public Bet() {
    }

    public void setBet_id(Long bet_id) {
        this.bet_id = bet_id;
    }

    public static List<Bet> getDataList() {
        List<Bet> data = new ArrayList<>();


        return data;
    }

    public static boolean toBool(int param) {
        if (param != 0) {
            return true;
        } else return false;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setMoneyBetted(String moneyBetted) {
        Rubbles rubbles = new Rubbles();
        this.moneyBetted = rubbles.toRubbles(moneyBetted);
    }

    public void setCaf(double caf) {
        this.caf = caf;
    }

    public void setPossibleProfit(String possibleProfit) {
        Rubbles rubbles = new Rubbles();
        this.possibleProfit = rubbles.toRubbles(possibleProfit);
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }
}