package com.example.psweeney.donationappandroid.charity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.psweeney.donationappandroid.R;
import com.example.psweeney.donationappandroid.chart.PieChartBuilder;
import com.example.psweeney.donationappandroid.feed.PostData;
import com.example.psweeney.donationappandroid.feed.PostFactory;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by psweeney on 4/22/16.
 */
public class CharityDetailData {
    public enum DonationSpendingCategory{
        FOOD, SUPPLIES, FUNDRAISING, MARKETING, SALARY, OTHER
    }

    public static String charityIdentifierKey = "charityIdentifier";

    private String _displayName = "Fake Charity";
    private int _iconId = PostFactory.defCharityIconId;

    private int _addressStreetNumber = 0;
    private String _addressStreet = "Fake Street";
    private String _addressCity = "College Park";
    private String _addressState = "MD";
    private int _addressZipCode = 20740;

    private float _distance = 0;

    private int[] _phoneNumber = new int[10];

    private String _emailUser = "fake";
    private String _emailHost = "charity.com";

    private boolean _isCurrentRecipient = false;

    private String _bioText = "";

    private float _rating = 1;

    private List<PostData> _posts;

    private Map<DonationSpendingCategory, Float> _spendingBreakdown = new HashMap<>();

    public CharityDetailData(){
        _spendingBreakdown.put(DonationSpendingCategory.SALARY, 1f);
    }

    public CharityDetailData(String displayName, int iconId, int addressStreetNumber, String addressStreet, String addressCity,
                             String addressState, int addressZipCode, float distance, int[] phoneNumber, String emailUser, String emailHost,
                             boolean isCurrentRecipient, String bioText, float rating,
                             Map<DonationSpendingCategory, Float> spendingBreakdown){
        _displayName = displayName;
        _iconId = iconId;

        _addressStreetNumber = addressStreetNumber;
        _addressStreet = addressStreet;
        _addressCity = addressCity;
        _addressState = addressState;
        _addressZipCode = addressZipCode;

        _distance = distance;

        _phoneNumber = new int[10];

        for(int i = 0; i < phoneNumber.length; i++){
            _phoneNumber[i] = phoneNumber[i];
        }

        _emailUser = emailUser;
        _emailHost = emailHost;

        _isCurrentRecipient = isCurrentRecipient;
        _bioText = bioText;
        _rating = rating;


        _spendingBreakdown = new HashMap<>();

        if(spendingBreakdown != null) {
            _spendingBreakdown.putAll(spendingBreakdown);
        }

        _posts = new ArrayList<>();
    }

    public void populatePosts(){
        _posts = PostFactory.getAllPostsByAuthor(getDisplayName());
    }

    public Integer getIdentifier(){
        Integer identifier = _displayName.hashCode();
        identifier += ((Integer) _iconId).hashCode();
        identifier += ((Integer) _addressStreetNumber).hashCode();
        identifier += _addressStreet.hashCode();
        identifier += _addressCity.hashCode();
        identifier += _addressState.hashCode();
        identifier += ((Integer) _addressZipCode).hashCode();

        return identifier;
    }

    public String getDisplayName(){
        return _displayName;
    }

    public int getIconId(){
        return _iconId;
    }

    public String getAddressLine1(){
        return Integer.toString(_addressStreetNumber) + " " + _addressStreet;
    }

    public String getAddressLine2(){
        return _addressCity + ", " + _addressState + " " + Integer.toString(_addressZipCode);
    }

    public float getDistance(){
        return _distance;
    }

    public String getDisplayDistance(){
        String displayDistance = Integer.toString(Math.round(_distance));
        displayDistance += '.' + Integer.toString(Math.round((_distance * 10) % 10));
        displayDistance += " miles away";
        return displayDistance;
    }

    public String getPhoneNumber(){
        String phoneString = "";
        for(int i = 0; i < _phoneNumber.length; i++){
            switch (i){
                case 0:
                    phoneString += "(";
                    break;
                case 3:
                    phoneString += ") ";
                    break;
                case 6:
                    phoneString += " - ";

            }
            phoneString += Integer.toString(_phoneNumber[i]);
        }
        return phoneString;
    }

    public String getEmailAddress(){
        return _emailUser + "@" + _emailHost;
    }

    public boolean isCurrentRecipient(){
        return _isCurrentRecipient;
    }

    public void setIsCurrentRecipient(boolean newValue){
        _isCurrentRecipient = newValue;
    }

    public String getBioText(){
        return _bioText;
    }

    public float getRating(){
        return _rating;
    }

    public String getRatingDisplayString(float ratingMax){
        float displayRatingValue = _rating * ratingMax;

        String numeratorString = Float.toString(displayRatingValue);
        numeratorString = numeratorString.substring(0, numeratorString.indexOf('.') + 2);

        String denominatorString = Float.toString(ratingMax);
        denominatorString = denominatorString.substring(0, denominatorString.indexOf('.') + 2);

        return numeratorString + " / " + denominatorString;
    }

    public Drawable getRatingBarDrawable(float width, float height, int backgroundColor, int foregroundColor){
        Bitmap barBitmap = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        Canvas barCanvas = new Canvas(barBitmap);

        Paint paint = new Paint();
        paint.setColor(backgroundColor);

        barCanvas.drawRect(0, 0, width, height, paint);

        paint.setColor(foregroundColor);
        barCanvas.drawRect(0, 0, width * _rating, height, paint);

        return new BitmapDrawable(barBitmap);
    }

    public Map<DonationSpendingCategory, Float> getSpendingBreakdown(){
        return _spendingBreakdown;
    }

    public List<PostData> getPosts(){
        return _posts;
    }

    public static String getDonationSpendingCategoryString(DonationSpendingCategory c){
        switch (c){
            case FOOD:
                return "Food";
            case SUPPLIES:
                return "Supplies";
            case FUNDRAISING:
                return "Fundraising";
            case MARKETING:
                return "Marketing";
            case SALARY:
                return "Salary";
            default:
                return "Other";
        }
    }

    public static Integer getDonationSpendingCategoryColor(DonationSpendingCategory c, Resources resources){
        switch (c){
            case FOOD:
                return resources.getColor(R.color.spendingCategoryColorFood);
            case SUPPLIES:
                return resources.getColor(R.color.spendingCategoryColorSupplies);
            case FUNDRAISING:
                return resources.getColor(R.color.spendingCategoryColorFundraising);
            case MARKETING:
                return resources.getColor(R.color.spendingCategoryColorMarketing);
            case SALARY:
                return resources.getColor(R.color.spendingCategoryColorSalary);
            default:
                return resources.getColor(R.color.spendingCategoryColorOther);
        }
    }

    private SortedSet<DonationSpendingCategory> getSpendingCategoriesSorted(){
        SortedSet<DonationSpendingCategory> categories = new TreeSet<>(new Comparator<DonationSpendingCategory>() {
            @Override
            public int compare(DonationSpendingCategory c1, DonationSpendingCategory c2) {
                if(_spendingBreakdown == null || !(_spendingBreakdown.containsKey(c1) || _spendingBreakdown.containsKey(c2))){
                    return 0;
                }else if(!_spendingBreakdown.containsKey(c1)){
                    return 1;
                } else if(!_spendingBreakdown.containsKey(c2)){
                    return -1;
                }

                if(_spendingBreakdown.get(c1) > _spendingBreakdown.get(c2)){
                    return -1;
                } else if(_spendingBreakdown.get(c1) < _spendingBreakdown.get(c2)){
                    return 1;
                }

                return 0;
            }
        });

        categories.addAll(_spendingBreakdown.keySet());
        return categories;
    }

    public PieData getPieData(Context context){
        Resources resources = context.getResources();
        List<String> xVals = new ArrayList<>();
        List<Float> yVals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        Set<DonationSpendingCategory> sortedCategories = getSpendingCategoriesSorted();

        float spendingTotal = 0;
        for(DonationSpendingCategory c : sortedCategories){
            spendingTotal += _spendingBreakdown.get(c);
        }

        float percentageMultiplier = 0;
        if(spendingTotal > 0){
            percentageMultiplier = 100/spendingTotal;
        }

        for(DonationSpendingCategory c : sortedCategories){
            xVals.add(getDonationSpendingCategoryString(c));
            yVals.add(_spendingBreakdown.get(c) * percentageMultiplier);
            colors.add(getDonationSpendingCategoryColor(c, resources));
        }

        return PieChartBuilder.convertToPieData(xVals, yVals, colors, "Donation Spending Breakdown");
    }
}
