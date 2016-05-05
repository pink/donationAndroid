package com.example.psweeney.donationappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.feed.DonationPostData;
import com.example.psweeney.donationappandroid.feed.PostData;
import com.example.psweeney.donationappandroid.feed.PostFactory;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        drawWeekGraph();
        drawMonthGraph();
        drawYearGraph();
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

    private void drawWeekGraph() {
        LinearLayout linearLayoutWeek = (LinearLayout) findViewById(R.id.frameLayoutWeek);
        FrameLayout weekGraphContainer = (FrameLayout) linearLayoutWeek.findViewById(R.id.weekChartContainer);

        BarChart lineChart = new BarChart(getApplicationContext());

        ArrayList<String> xVals = new ArrayList<String>();
        Calendar week = Calendar.getInstance();
        week.add(Calendar.DAY_OF_YEAR, -6);
        for (int i = 0; i < 7; i++) {
            xVals.add(new SimpleDateFormat("MMM d").format(week.getTime()));
            week.add(Calendar.DAY_OF_YEAR, 1);
        }

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        float[] vals = new float[7];
        float total = 0;
        for (int i = 0; i < 7; i++) {
            float mult = (3);
            float val = (float) (Math.random() * mult) + 1;// + (float)
            total += val;
            yVals.add(new BarEntry(val, i));
        }

        /*
        List<PostData> userPosts = PostFactory.getAllUserPosts();
        for (PostData p: userPosts) {

            Calendar c = p.getPostTime();
            c.get(Calendar.DAY_OF_YEAR);


        }
        */
        //userPosts.get(0).getPostTime()

        //System.out.println(new SimpleDateFormat("MMM d").format(week.getTime()));



        BarDataSet dataset = new BarDataSet(yVals, "Amount Donated");
        //dataset.setDrawCubic(true);
        //dataset.setDrawFilled(true);
        BarData data = new BarData(xVals, dataset);
        data.setValueTextSize(9f);
        lineChart.setData(data);
        lineChart.animateY(500);
        lineChart.setDescription("");

        weekGraphContainer.addView(lineChart);

        TextView line = (TextView) linearLayoutWeek.findViewById(R.id.history_week_text);
        String newText = "$" + Math.round(total * 100.0) / 100.0 + " this week";
        line.setText(newText);
    }

    private void drawMonthGraph() {
        LinearLayout linearLayoutMonth = (LinearLayout) findViewById(R.id.frameLayoutMonth);
        FrameLayout monthGraphContainer = (FrameLayout) linearLayoutMonth.findViewById(R.id.monthChartContainer);

        BarChart lineChart = new BarChart(getApplicationContext());

        ArrayList<String> xVals = new ArrayList<String>();
        Calendar month = Calendar.getInstance();
        month.add(Calendar.WEEK_OF_YEAR, -4);
        for (int i = 0; i < 5; i++) {
            xVals.add(new SimpleDateFormat("MMM d").format(month.getTime()));
            month.add(Calendar.WEEK_OF_YEAR, 1);
        }

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        float[] vals = new float[7];
        float total = 0;
        for (int i = 0; i < 5; i++) {
            float mult = (6 + 1);
            float val = (float) (Math.random() * mult) + 10;// + (float)
            total += val;
            yVals.add(new BarEntry(val, i));
        }

        BarDataSet dataset = new BarDataSet(yVals, "Amount Donated");
        //dataset.setDrawCubic(true);
        //dataset.setDrawFilled(true);
        BarData data = new BarData(xVals, dataset);
        data.setValueTextSize(9f);
        lineChart.setData(data);
        lineChart.animateY(500);
        lineChart.setDescription("");

        monthGraphContainer.addView(lineChart);

        TextView line = (TextView) linearLayoutMonth.findViewById(R.id.history_month_text);
        String newText = "$" + Math.round(total * 100.0) / 100.0 + " this month";
        line.setText(newText);
    }

    private void drawYearGraph() {
        LinearLayout linearLayoutYear = (LinearLayout) findViewById(R.id.frameLayoutYear);
        FrameLayout yearGraphContainer = (FrameLayout) linearLayoutYear.findViewById(R.id.yearChartContainer);

        BarChart barChart = new BarChart(getApplicationContext());

        ArrayList<String> xVals = new ArrayList<String>();
        Calendar year = Calendar.getInstance();
        year.add(Calendar.MONTH, -11);
        for (int i = 0; i < 12; i++) {
            xVals.add(new SimpleDateFormat("MMM").format(year.getTime()));
            year.add(Calendar.MONTH, 1);
        }

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        float[] vals = new float[7];
        float total = 0;
        for (int i = 0; i < 12; i++) {
            float mult = (6 + 1);
            float val = (float) (Math.random() * mult) + 100;// + (float)
            total += val;
            yVals.add(new BarEntry(val, i));
        }

        BarDataSet dataset = new BarDataSet(yVals, "Amount Donated");
        //dataset.setDrawCubic(true);
        //dataset.setDrawFilled(true);
        BarData data = new BarData(xVals, dataset);
        data.setValueTextSize(9f);
        barChart.setData(data);
        barChart.animateY(500);
        barChart.setDescription("");

        yearGraphContainer.addView(barChart);

        TextView line = (TextView) linearLayoutYear.findViewById(R.id.history_year_text);
        String newText = "$" + Math.round(total * 100.0) / 100.0 + " this year";
        line.setText(newText);
    }
}
