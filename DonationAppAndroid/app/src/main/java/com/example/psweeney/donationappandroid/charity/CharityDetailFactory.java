package com.example.psweeney.donationappandroid.charity;

import android.content.res.Resources;

import com.example.psweeney.donationappandroid.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by psweeney on 4/22/16.
 */
public class CharityDetailFactory {
    private static Map<Integer, CharityDetailData> _charityMap;
    public static Map<CharityDetailData.DonationSpendingCategory, Integer> colorMap;

    public static void init(Resources resources){
        _charityMap = new HashMap<>();

        CharityDetailData newCharity = new CharityDetailData("Charity A", R.drawable.ic_local_florist_black_48dp,
                1234, "A Street", "College Park", "MD", 20740, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, "charity_a", "charities.com", true,
                "Here at Charity, A, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 0.9f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 5f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 2f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FOOD, 1f);

        _charityMap.put(newCharity.getIdentifier(), newCharity);

        newCharity = new CharityDetailData("Charity B", R.drawable.ic_local_florist_black_48dp,
                999, "B Street", "Silver Spring", "MD", 20901, new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1 }, "charity_b", "charities.com", false,
                "Here at Charity, B, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 0.6f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 4f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 3f);

        _charityMap.put(newCharity.getIdentifier(), newCharity);

        colorMap = new HashMap<>();
        colorMap.put(CharityDetailData.DonationSpendingCategory.FOOD, resources.getColor(R.color.spendingCategoryColorFood));
        colorMap.put(CharityDetailData.DonationSpendingCategory.SUPPLIES, resources.getColor(R.color.spendingCategoryColorSupplies));
        colorMap.put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, resources.getColor(R.color.spendingCategoryColorFundraising));
        colorMap.put(CharityDetailData.DonationSpendingCategory.MARKETING, resources.getColor(R.color.spendingCategoryColorMarketing));
        colorMap.put(CharityDetailData.DonationSpendingCategory.SALARY, resources.getColor(R.color.spendingCategoryColorSalary));
        colorMap.put(CharityDetailData.DonationSpendingCategory.OTHER, resources.getColor(R.color.spendingCategoryColorOther));
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

    public static void setUserAutoDonateCharity(Integer charityId){
        if(!_charityMap.containsKey(charityId)){
            return;
        }

        for(CharityDetailData data : _charityMap.values()){
            data.setIsCurrentRecipient(false);
        }

        _charityMap.get(charityId).setIsCurrentRecipient(true);

    }
}
