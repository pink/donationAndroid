package com.example.psweeney.donationappandroid.charity;

import com.example.psweeney.donationappandroid.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by psweeney on 4/22/16.
 */
public class CharityDetailFactory {
    private static Map<Integer, CharityDetailData> _charityMap;

    public static void init(){
        _charityMap = new HashMap<>();

        CharityDetailData newCharity = new CharityDetailData("Charity A", R.drawable.ic_local_florist_black_48dp,
                1234, "A Street", "College Park", "MD", 20740, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, "charity_a", "charities.com", true,
                "Here at Charity, A, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 0.8f, null);
        newCharity.getSpendingBreakdown().put(5f, CharityDetailData.DonationSpendingCategory.SUPPLIES);
        newCharity.getSpendingBreakdown().put(2f, CharityDetailData.DonationSpendingCategory.SALARY);

        _charityMap.put(newCharity.getIdentifier(), newCharity);

        newCharity = new CharityDetailData("Charity B", R.drawable.ic_local_florist_black_48dp,
                999, "B Street", "Silver Spring", "MD", 20901, new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1 }, "charity_b", "charities.com", false,
                "Here at Charity, B, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 0.6f, null);
        newCharity.getSpendingBreakdown().put(4f, CharityDetailData.DonationSpendingCategory.SUPPLIES);
        newCharity.getSpendingBreakdown().put(3f, CharityDetailData.DonationSpendingCategory.SALARY);

        _charityMap.put(newCharity.getIdentifier(), newCharity);
    }

    public static CharityDetailData searchByCharityName(String name){
        for(CharityDetailData c : _charityMap.values()){
            if(c.getDisplayName().equals(name)){
                return c;
            }
        }
        return null;
    }

    public static CharityDetailData getCharityById(Integer key){
        if(!_charityMap.containsKey(key)){
            return null;
        }
        return _charityMap.get(key);
    }
}
