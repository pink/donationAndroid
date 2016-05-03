package com.example.psweeney.donationappandroid.chart;

import android.content.Context;
import android.graphics.Color;

import com.example.psweeney.donationappandroid.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by psweeney on 5/2/16.
 */
public class PieChartBuilder {
    private static String missingLabel = "Error";
    private static Integer missingColor = Color.BLACK;

    public static PieData convertToPieData(List<String> xVals, List<Float> percentages, List<Integer> colors, String title){
        ArrayList<String> sortedXVals = new ArrayList<>(xVals);
        ArrayList<Float> sortedPercentages = new ArrayList<>(percentages);
        ArrayList<Integer> sortedColors = new ArrayList<>(colors);

        sortLists(sortedXVals, sortedPercentages, sortedColors);

        ArrayList<Entry> yVals = new ArrayList<>();

        for(int i = 0; i < sortedPercentages.size(); i++){
            yVals.add(new Entry(sortedPercentages.get(i), i));
        }

        PieDataSet dataSet = new PieDataSet(yVals, "");
        dataSet.setColors(sortedColors);

        return new PieData(sortedXVals, dataSet);
    }

    private static void sortLists(ArrayList<String> xVals, ArrayList<Float> percentages, ArrayList<Integer> colors){
        ArrayList<String> newXVals = new ArrayList<>();
        ArrayList<Float> newPercentages = new ArrayList<>();
        ArrayList<Integer> newColors = new ArrayList<>();

        while(percentages.size() > 0){
            float largestPercentage = 0;
            int largestIndex = 0;
            for(int i = 0; i < percentages.size(); i++){
                float currPercentage = percentages.get(i);
                if(currPercentage > largestPercentage){
                    largestPercentage = percentages.get(i);
                    largestIndex = i;
                }
            }

            newPercentages.add(percentages.remove(largestIndex));

            if(xVals.size() > largestIndex){
                newXVals.add(xVals.remove(largestIndex));
            } else {
                newXVals.add(missingLabel);
            }

            if(colors.size() > largestIndex){
                newColors.add(colors.remove(largestIndex));
            } else {
                newColors.add(missingColor);
            }
        }

        xVals.clear();
        percentages.clear();
        colors.clear();

        xVals.addAll(newXVals);
        percentages.addAll(newPercentages);
        colors.addAll(newColors);
    }
}
