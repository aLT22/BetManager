package com.bytebuilding.betmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bytebuilding.betmanager.data.Bet;
import com.bytebuilding.betmanager.data.Rubbles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 16.10.2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = "DATABASE_HELPER";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BetManagaer";

    public static final String TABLE_NAME = "Bet";

    /*BET FIELDS*/
    public static final String BET_ID = "bet_id";
    public static final String BET_SPORT = "bet_sport";
    public static final String BET_MONEY = "bet_money";
    public static final String BET_CAF = "bet_caf";
    public static final String BET_POSSIBLE_PROFIT = "bet_possible_profit";
    public static final String BET_STATUS = "bet_status";

    /*CREATION SCRIPT*/
    public static final String CREATE_TABLE_BET = "CREATE TABLE " +
            TABLE_NAME + "(" + BET_ID + " INTEGER PRIMARY KEY, " +
            BET_SPORT + " TEXT, " + BET_MONEY + " TEXT, " +
            BET_CAF + " REAL, " + BET_POSSIBLE_PROFIT + " TEXT, " +
            BET_STATUS + " INTEGER);";

    /*DROP SCRIPT*/
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + TABLE_NAME);
        onCreate(db);
    }

    /*Creating Bet*/
    public long createBet(Bet bet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BET_ID, bet.getBet_id());
        contentValues.put(BET_SPORT, bet.getSport());
        contentValues.put(BET_MONEY, bet.getMoneyBetted());
        contentValues.put(BET_CAF, bet.getCaf());
        contentValues.put(BET_POSSIBLE_PROFIT, bet.getPossibleProfit());
        contentValues.put(BET_STATUS, bet.isDone());

        long bet_id = db.insert(TABLE_NAME, null, contentValues);

        return bet_id;
    }

    /*Get single Bet (select * from Bet where input_id = bet_id)*/
    public Bet getBet(long bet_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String statement = "SELECT * FROM " + TABLE_NAME + " WHERE " + BET_ID + " = " + bet_id;

        Log.e(LOG_TAG, statement);

        Cursor cursor = db.rawQuery(statement, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Bet bet = new Bet();

        bet.setBet_id(cursor.getLong(cursor.getColumnIndex(BET_ID)));
        bet.setSport(cursor.getString(cursor.getColumnIndex(BET_SPORT)));
        bet.setMoneyBetted(cursor.getString(cursor.getColumnIndex(BET_MONEY)));
        bet.setCaf(cursor.getDouble(cursor.getColumnIndex(BET_CAF)));
        bet.setPossibleProfit(cursor.getString(cursor.getColumnIndex(BET_POSSIBLE_PROFIT)));
        bet.setIsDone(cursor.getInt(cursor.getColumnIndex(BET_STATUS)));

        return bet;
    }

    /*Get list of Bets (select * from bets)*/
    public List<Bet> getAllBets() {
        List<Bet> bets = new ArrayList<>();

        String statement = "SELECT * FROM " + TABLE_NAME;

        Log.e(LOG_TAG, statement);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                Bet bet = new Bet();

                bet.setBet_id(cursor.getLong(cursor.getColumnIndex(BET_ID)));
                bet.setSport(cursor.getString(cursor.getColumnIndex(BET_SPORT)));
                bet.setMoneyBetted(cursor.getString(cursor.getColumnIndex(BET_MONEY)));
                bet.setCaf(cursor.getDouble(cursor.getColumnIndex(BET_CAF)));
                bet.setPossibleProfit(cursor.getString(cursor.getColumnIndex(BET_POSSIBLE_PROFIT)));
                bet.setIsDone(cursor.getInt(cursor.getColumnIndex(BET_STATUS)));

                bets.add(bet);
            } while (cursor.moveToNext());
        }

        return bets;
    }

    /*get list of inactive(done bets) bets*/
    public List<Bet> getDoneBets() {
        List<Bet> doneBets = new ArrayList<>();

        String statement = "SELECT * FROM " + TABLE_NAME + " WHERE " + BET_STATUS + " = 1";

        Log.e(LOG_TAG, statement);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                Bet bet = new Bet();

                bet.setBet_id(cursor.getLong(cursor.getColumnIndex(BET_ID)));
                bet.setSport(cursor.getString(cursor.getColumnIndex(BET_SPORT)));
                bet.setMoneyBetted(cursor.getString(cursor.getColumnIndex(BET_MONEY)));
                bet.setCaf(cursor.getDouble(cursor.getColumnIndex(BET_CAF)));
                bet.setPossibleProfit(cursor.getString(cursor.getColumnIndex(BET_POSSIBLE_PROFIT)));
                bet.setIsDone(cursor.getInt(cursor.getColumnIndex(BET_STATUS)));

                doneBets.add(bet);
            } while (cursor.moveToNext());
        }

        return doneBets;
    }

    /*get list of non-done bets*/
    public List<Bet> getBadBets() {
        List<Bet> doneBets = new ArrayList<>();

        String statement = "SELECT * FROM " + TABLE_NAME + " WHERE " + BET_STATUS + " = 0";

        Log.e(LOG_TAG, statement);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                Bet bet = new Bet();

                bet.setBet_id(cursor.getLong(cursor.getColumnIndex(BET_ID)));
                bet.setSport(cursor.getString(cursor.getColumnIndex(BET_SPORT)));
                bet.setMoneyBetted(cursor.getString(cursor.getColumnIndex(BET_MONEY)));
                bet.setCaf(cursor.getDouble(cursor.getColumnIndex(BET_CAF)));
                bet.setPossibleProfit(cursor.getString(cursor.getColumnIndex(BET_POSSIBLE_PROFIT)));
                bet.setIsDone(cursor.getInt(cursor.getColumnIndex(BET_STATUS)));

                doneBets.add(bet);
            } while (cursor.moveToNext());
        }

        return doneBets;
    }

    /*Update bet (update by id)*/
    public int updateBet(Bet bet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BET_ID, bet.getBet_id());
        contentValues.put(BET_SPORT, bet.getSport());
        contentValues.put(BET_MONEY, bet.getMoneyBetted());
        contentValues.put(BET_CAF, bet.getCaf());
        contentValues.put(BET_POSSIBLE_PROFIT, bet.getPossibleProfit());
        contentValues.put(BET_STATUS, bet.isDone());

        return db.update(TABLE_NAME, contentValues, BET_ID + " = ?",
                new String[] {String.valueOf(bet.getBet_id())});
    }

    /*update bet's status*/
    public int updateBetStatus(Bet bet, boolean newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BET_STATUS, newStatus);

        return db.update(TABLE_NAME, contentValues, BET_ID + " = ?",
                new String[] {String.valueOf(bet.getBet_id())});
    }

    /*Delete bet (removed by id)*/
    public void deleteBet(long bet_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, BET_ID + " = ?",
                new String[] {String.valueOf(bet_id)});
    }
}