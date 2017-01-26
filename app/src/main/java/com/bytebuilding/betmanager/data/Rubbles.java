package com.bytebuilding.betmanager.data;

/**
 * Created by Alexey on 10.10.2016.
 */

public class Rubbles {

    private int mRub;
    private int mCop;

    public Rubbles() {
        this.mRub = 0;
        this.mCop = 0;
    }

    public Rubbles(int mRub, int mCop) {
        this.mRub = mRub;
        this.mCop = mCop;
        getConvertMoneys();
    }

    public Rubbles(String money) {
        this.mRub = toRubbles(money).getRubs();
        this.mCop = toRubbles(money).getCops();
    }

    public void getConvertMoneys() {
        if (mCop > 99) {
            mRub++;
            mCop -= 100;
            getConvertMoneys();
        }
    }

    public int getRubs() {
        return mRub;
    }

    public int getCops() {
        return mCop;
    }

    public void setmRub(int mRub) {
        this.mRub = mRub;
    }

    public void setmCop(int mCop) {
        this.mCop = mCop;
    }

    @Override
    public String toString() {
        String moneys;
        getConvertMoneys();
        Integer r = getRubs();
        Integer c = getCops();
        moneys = r.toString() + "." + c.toString();
        return moneys;
    }

    public Rubbles toRubbles(String moneys) {
        Rubbles rubbles = new Rubbles();

        String[] rc = moneys.split("[.]");
        System.out.println(rc[0] + rc[1]);

        rubbles.setmRub(Integer.parseInt(rc[0]));
        rubbles.setmCop(Integer.parseInt(rc[1]));

        return rubbles;
    }

    public int toInt() {
        return Integer.parseInt(String.valueOf(this.mRub) + String.valueOf(this.mCop));
    }

}