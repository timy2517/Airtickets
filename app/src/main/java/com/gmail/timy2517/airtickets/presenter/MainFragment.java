package com.gmail.timy2517.airtickets.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.gmail.timy2517.airtickets.R;
import com.gmail.timy2517.airtickets.network.LoadData;
import com.gmail.timy2517.airtickets.network.RequestParams;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Artem Novik on 17.6.17.
 */

public class MainFragment extends Fragment {

    public static final String DIALOG_DATE = "DialogDate";
    public static final int REQUEST_DATE = 0;
    public static final String KEY_DATE_FROM_BUTTON = "mDateFromButtonText";
    public static final String KEY_DATE_TO_BUTTON = "mDateToButtonText";


    private boolean isDateFromPressedLast;
    DateFormat buttonDateFormat;
    Calendar mCalendar;

    private Button mMakeMeWellButton;
    private Button mDateFromButton;
    private Button mDateToButton;
    private Spinner mSpinnerCityFrom;
    private Spinner mSpinnerCityTo;
    private CheckBox mCheckBoxTransfer;
    private RadioGroup mRadioGroupSort;

    LoadData loadData;
    RequestParams mRequestParams;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_DATE_FROM_BUTTON, mDateFromButton.getText().toString());
        outState.putString(KEY_DATE_TO_BUTTON, mDateToButton.getText().toString());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mRequestParams = new RequestParams();
        buttonDateFormat = new SimpleDateFormat("d MM yyyy");


        mSpinnerCityFrom = v.findViewById(R.id.spinnerCityFrom);
        mSpinnerCityTo = v.findViewById(R.id.spinnerCityTo);

        mDateFromButton = v.findViewById(R.id.dateFromButton);
        mDateFromButton.setText(buttonDateFormat.format(mRequestParams.getDateFrom()));
        mDateToButton = v.findViewById(R.id.dateToButton);
        mDateToButton.setText(buttonDateFormat.format(mRequestParams.getDateTo()));
        mMakeMeWellButton = v.findViewById(R.id.makeMeWell);

        mCheckBoxTransfer = v.findViewById(R.id.cbTransfer);
        mRadioGroupSort = v.findViewById(R.id.radioGroupSort);

        if (savedInstanceState != null) {
            mDateFromButton.setText(savedInstanceState.getString(KEY_DATE_FROM_BUTTON, getString(R.string.default_button_from_text)));
            mDateToButton.setText(savedInstanceState.getString(KEY_DATE_TO_BUTTON, getString(R.string.default_button_to_text)));
        }


        mDateFromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDateFromPressedLast = true;
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(MainFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);

            }
        });

        mDateToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDateFromPressedLast = false;
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(MainFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mMakeMeWellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRequestParams.setCityFrom(mSpinnerCityFrom.getSelectedItem().toString());
                mRequestParams.setCityTo(mSpinnerCityTo.getSelectedItem().toString());
                if (mRequestParams.getCityFrom().equals(mRequestParams.getCityTo())) {
                    Toast.makeText(getActivity(), R.string.select_a_destination, Toast.LENGTH_SHORT).show();
                    return;
                }
                mRequestParams.setWithTransfer(mCheckBoxTransfer.isChecked());

                mCalendar = Calendar.getInstance();
                mCalendar.clear(Calendar.HOUR);
                mCalendar.clear(Calendar.MINUTE);
                mCalendar.clear(Calendar.SECOND);

                if (mRequestParams.getDateTo().before(mRequestParams.getDateFrom()) ||
                        mCalendar.before(mRequestParams.getDateTo())) {
                    Toast.makeText(getActivity(), R.string.select_the_correct_time, Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (mRadioGroupSort.getCheckedRadioButtonId()) {
                    case R.id.rbBest:
                        mRequestParams.setSort(RequestParams.CHOICE_OF_THE_BEST);
                        break;
                    case R.id.rbPrice:
                        mRequestParams.setSort(RequestParams.SORTING_BY_PRICE);
                        break;
                    case R.id.rbTimeTransfer:
                        mRequestParams.setSort(RequestParams.SORTING_BY_DURATION);
                        break;
                }

                try {
                    loadData = new LoadData(mRequestParams, getActivity().getSupportFragmentManager());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                loadData.execute();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            if (isDateFromPressedLast) {
                mDateFromButton.setText(buttonDateFormat.format(date));
                mRequestParams.setDateFrom(date);
            } else {
                mDateToButton.setText(buttonDateFormat.format(date));
                mRequestParams.setDateTo(date);
            }
        }
    }

}
