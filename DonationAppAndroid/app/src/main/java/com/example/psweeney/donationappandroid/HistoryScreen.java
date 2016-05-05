package com.example.psweeney.donationappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class HistoryScreen extends AppCompatActivity {

    private enum RangeType{
        WEEK, MONTH, YEAR
    }

    private RangeType _currentSelection = RangeType.WEEK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_screen);

        updateRangeSelection();
        generateDataContents();
    }

    public void onButtonClickWeekHistory(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSelection != RangeType.WEEK){
            _currentSelection = RangeType.WEEK;
            updateRangeSelection();
        }
    }

    public void onButtonClickMonthHistory(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSelection != RangeType.MONTH){
            _currentSelection = RangeType.MONTH;
            updateRangeSelection();
        }
    }

    public void onButtonClickYearHistory(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSelection != RangeType.YEAR){
            _currentSelection = RangeType.YEAR;
            updateRangeSelection();
        }
    }

    private void updateRangeSelection(){
        TextView weekLabel = (TextView) findViewById(R.id.textViewWeekHistoryLabel);
        TextView monthLabel = (TextView) findViewById(R.id.textViewMonthHistoryLabel);
        TextView yearLabel = (TextView) findViewById(R.id.textViewYearHistoryLabel);

        LinearLayout weekView = (LinearLayout) findViewById(R.id.frameLayoutWeek);
        LinearLayout monthView = (LinearLayout) findViewById(R.id.frameLayoutMonth);
        LinearLayout yearView = (LinearLayout) findViewById(R.id.frameLayoutYear);

        switch (_currentSelection){
            case WEEK:
                weekLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                weekLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                monthLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                monthLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                yearLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                yearLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                weekView.setVisibility(View.VISIBLE);
                monthView.setVisibility(View.GONE);
                yearView.setVisibility(View.GONE);

                break;
            case MONTH:
                monthLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                monthLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                weekLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                weekLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                yearLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                yearLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                weekView.setVisibility(View.GONE);
                monthView.setVisibility(View.VISIBLE);
                yearView.setVisibility(View.GONE);

                break;
            case YEAR:
                yearLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                yearLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                weekLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                weekLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                monthLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                monthLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                weekView.setVisibility(View.GONE);
                monthView.setVisibility(View.GONE);
                yearView.setVisibility(View.VISIBLE);

                break;
        }
    }

    private void generateDataContents() {
        LinearLayout linearLayoutWeek = (LinearLayout) findViewById(R.id.frameLayoutWeek);
        FrameLayout weekGraphContainer = (FrameLayout) linearLayoutWeek.findViewById(R.id.weekChartContainer);
        LinearLayout breakdownContainer = (LinearLayout) findViewById(R.id.containerCharityDataItem2);

        LineChart lineChart = new LineChart(getApplicationContext());

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Sunday");
        xVals.add("Monday");
        xVals.add("Tuesday");
        xVals.add("Wednesday");
        xVals.add("Thursday");
        xVals.add("Friday");
        xVals.add("Saturday");

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < 7; i++) {
            float mult = (6 + 1);
            float val = (float) (Math.random() * mult) + 1;// + (float)
            yVals.add(new Entry(val, i));
        }

        LineDataSet dataset = new LineDataSet(yVals, "Amount Donated");
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);
        LineData data = new LineData(xVals, dataset);
        data.setValueTextSize(9f);
        lineChart.setData(data);
        lineChart.animateY(500);
        lineChart.setDescription("");

        weekGraphContainer.addView(lineChart);
    }
}
