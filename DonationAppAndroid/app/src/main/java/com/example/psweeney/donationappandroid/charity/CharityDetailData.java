package com.example.psweeney.donationappandroid.charity;

import com.example.psweeney.donationappandroid.FeedActivity;
import com.example.psweeney.donationappandroid.feed.PostData;
import com.example.psweeney.donationappandroid.feed.PostFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by psweeney on 4/22/16.
 */
public class CharityDetailData {
    public enum DonationSpendingCategory{
        FOOD, SUPPLIES, FUNDRAISING, MARKETING, SALARY
    }

    public static String charityIdentifierKey = "charityIdentifier";

    private String _displayName = "Fake Charity";
    private int _iconId = PostFactory.defCharityIconId;

    private int _addressStreetNumber = 0;
    private String _addressStreet = "Fake Street";
    private String _addressCity = "College Park";
    private String _addressState = "MD";
    private int _addressZipCode = 20740;

    private int[] _phoneNumber = new int[10];

    private String _emailUser = "fake";
    private String _emailHost = "charity.com";

    private boolean _isCurrentRecipient = false;

    private String _bioText = "";

    private float _rating = 0.8f;

    private List<PostData> _posts;

    private Map<Float, DonationSpendingCategory> _spendingBreakdown = new TreeMap<>();

    public CharityDetailData(){
        _spendingBreakdown.put(1f, DonationSpendingCategory.SALARY);
    }

    public CharityDetailData(String displayName, int iconId, int addressStreetNumber, String addressStreet, String addressCity,
                             String addressState, int addressZipCode, int[] phoneNumber, String emailUser, String emailHost,
                             boolean isCurrentRecipient, String bioText, float rating,
                             Map<Float, DonationSpendingCategory> spendingBreakdown){
        _displayName = displayName;
        _iconId = iconId;

        _addressStreetNumber = addressStreetNumber;
        _addressStreet = addressStreet;
        _addressCity = addressCity;
        _addressState = addressState;
        _addressZipCode = addressZipCode;

        _phoneNumber = new int[10];

        for(int i = 0; i < phoneNumber.length; i++){
            _phoneNumber[i] = phoneNumber[i];
        }

        _emailUser = emailUser;
        _emailHost = emailHost;

        _isCurrentRecipient = isCurrentRecipient;
        _bioText = bioText;
        _rating = rating;
        if(spendingBreakdown != null) {
            _spendingBreakdown = spendingBreakdown;
        }

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

    public String getAddressShort(){
        return Integer.toString(_addressStreetNumber) + " " + _addressStreet;
    }

    public String getAddressLong(){
        return getAddressShort() + ", " + _addressCity + ", " + _addressState + " " + Integer.toString(_addressZipCode);
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

    public Map<Float, DonationSpendingCategory> getSpendingBreakdown(){
        return _spendingBreakdown;
    }

    public List<PostData> getPosts(){
        return _posts;
    }
}
