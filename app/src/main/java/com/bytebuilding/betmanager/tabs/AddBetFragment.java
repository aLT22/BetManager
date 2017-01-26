package com.bytebuilding.betmanager.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bytebuilding.betmanager.R;
import com.bytebuilding.betmanager.activities.MainActivity;
import com.bytebuilding.betmanager.data.Bet;
import com.bytebuilding.betmanager.data.Rubbles;
import com.bytebuilding.betmanager.database.DBHelper;

import java.util.Calendar;

public class AddBetFragment extends Fragment implements View.OnClickListener {

    private EditText etSport;
    private EditText etBettedMoneys;
    private EditText etCoefficient;
    private Button btnAddBet;
    private ScrollView scrollView;

    private DBHelper dbHelper;

    public AddBetFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_bet, container, false);

        initializeComponents(rootView);

        return rootView;
    }

    private void initializeComponents(View rootView) {
        dbHelper = new DBHelper(getContext().getApplicationContext());

        etSport = (EditText) rootView.findViewById(R.id.et_sport);
        etBettedMoneys = (EditText) rootView.findViewById(R.id.et_betted_moneys);
        etCoefficient = (EditText) rootView.findViewById(R.id.et_coefficient);
        etCoefficient.setOnClickListener(this);

        btnAddBet = (Button) rootView.findViewById(R.id.btn_add_bet);
        btnAddBet.setOnClickListener(this);

        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
    }

    @Override
    public void onClick(View v) {
        Rubbles rubbles = new Rubbles();
        switch (v.getId()) {
            case R.id.btn_add_bet:

                if (isEmpty()) {
                    Toast.makeText(getContext().getApplicationContext(), getString(R.string.input_error),
                            Toast.LENGTH_LONG).show();
                } else {
                    if (etBettedMoneys.getText().toString().contains(".")) {
                        String[] moneys = etBettedMoneys.getText().toString().split("[.]");
                        rubbles.setmRub(Integer.parseInt(moneys[0]));
                        rubbles.setmCop(Integer.parseInt(moneys[1]));
                    } else {
                        rubbles.setmRub(Integer.parseInt(etBettedMoneys.getText().toString()));
                    }

                    Bet bet = new Bet(Calendar.getInstance().getTimeInMillis(),
                            etSport.getText().toString(), rubbles,
                            Double.parseDouble(etCoefficient.getText().toString()), 0);
                    dbHelper.createBet(bet);

                    MainActivity.viewPager.setCurrentItem(2);
                }

                break;

            case R.id.et_coefficient:
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    private boolean isEmpty() {
        if (etSport.getText().toString().trim().length() == 0 || etBettedMoneys.getText()
                .toString().trim().length() == 0 || etCoefficient.getText().toString()
                .trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}