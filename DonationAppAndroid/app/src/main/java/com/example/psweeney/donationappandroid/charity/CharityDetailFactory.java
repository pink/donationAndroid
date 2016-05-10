package com.example.psweeney.donationappandroid.charity;

import android.content.res.Resources;

import com.example.psweeney.donationappandroid.R;
import com.example.psweeney.donationappandroid.SearchActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by psweeney on 4/22/16.
 */
public class CharityDetailFactory {
    private static Map<Integer, CharityDetailData> _charityMap;
    public static Map<CharityDetailData.DonationSpendingCategory, Integer> colorMap;

    private static final Comparator<CharityDetailData> RATING_COMPARATOR = new Comparator<CharityDetailData>() {
        @Override
        public int compare(CharityDetailData lhs, CharityDetailData rhs) {
            if(lhs.getRating() > rhs.getRating()){
                return -1;
            } else if(lhs.getRating() < rhs.getRating()){
                return 1;
            }

            int nameCompare = lhs.getDisplayName().compareTo(rhs.getDisplayName());
            if(nameCompare != 0){
                return nameCompare;
            }

            if(lhs.getDistance() < rhs.getDistance()){
                return -1;
            } else if(lhs.getDistance() > rhs.getDistance()){
                return 1;
            }

            return lhs.getIdentifier() - rhs.getIdentifier();
        }
    };

    private static final Comparator<CharityDetailData> DISTANCE_COMPARATOR = new Comparator<CharityDetailData>() {
        @Override
        public int compare(CharityDetailData lhs, CharityDetailData rhs) {
            if(lhs.getDistance() < rhs.getDistance()){
                return -1;
            } else if(lhs.getDistance() > rhs.getDistance()){
                return 1;
            }

            int nameCompare = lhs.getDisplayName().compareTo(rhs.getDisplayName());
            if(nameCompare != 0){
                return nameCompare;
            }

            if(lhs.getRating() > rhs.getRating()){
                return -1;
            } else if(lhs.getRating() < rhs.getRating()){
                return 1;
            }

            return lhs.getIdentifier() - rhs.getIdentifier();
        }
    };

    private static final Comparator<CharityDetailData> NAME_COMPARATOR = new Comparator<CharityDetailData>() {
        @Override
        public int compare(CharityDetailData lhs, CharityDetailData rhs) {
            int nameCompare = lhs.getDisplayName().compareTo(rhs.getDisplayName());
            if(nameCompare != 0){
                return nameCompare;
            }

            if(lhs.getRating() > rhs.getRating()){
                return -1;
            } else if(lhs.getRating() < rhs.getRating()){
                return 1;
            }

            if(lhs.getDistance() < rhs.getDistance()){
                return -1;
            } else if(lhs.getDistance() > rhs.getDistance()){
                return 1;
            }

            return lhs.getIdentifier() - rhs.getIdentifier();
        }
    };

    public static CharityDetailData generateRandomCharity(){
        String charityChar = "" + (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));

        String charityName = "Charity " + charityChar;

        if(getCharityByDisplayName(charityName) != null){
            return generateRandomCharity();
        }

        int charityIcon = R.drawable.ic_local_florist_black_48dp;

        int charityStreetNumber = (int) (Math.random() * 9999f);
        String charityStreet;
        switch((int) (Math.random() * 4f)){
            case 0:
                charityStreet = "Fake Street";
                break;
            case 1:
                charityStreet = "Bogus Street";
                break;
            case 2:
                charityStreet = "Real Street";
                break;
            default:
                charityStreet = "Normal Street";
        }

        String charityCity;
        switch ((int) (Math.random() * 6f)){
            case 0:
                charityCity = "College Park";
                break;
            case 1:
                charityCity = "Silver Spring";
                break;
            case 2:
                charityCity = "Bethesda";
                break;
            case 3:
                charityCity = "Rockville";
                break;
            case 4:
                charityCity = "Baltimore";
                break;
            default:
                charityCity = "Laurel";
        }

        String charityState = "MD";

        int charityZip = 20000 + (int) (Math.random() * 2000f);

        float charityDistance = (float) Math.random() * 15f;

        int[] charityPhone = new int[10];
        for(int i = 0; i < charityPhone.length; i++){
            charityPhone[i] = (int) (Math.random() * 10f);
        }

        String charityEmailUser = "charity_" + charityChar.toLowerCase();
        String charityEmailDomain = "charities.com";

        String charityBioText = "Here at " + charityName + ", we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh";
        float charityRating = (float) Math.random() * 0.8f;

        CharityDetailData ret = new CharityDetailData(charityName, charityIcon, charityStreetNumber, charityStreet, charityCity, charityState, charityZip,
                charityDistance, charityPhone, charityEmailUser, charityEmailDomain, false, charityBioText, charityRating, null);

        ret.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FOOD, (float) Math.random() * 10f);
        ret.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, (float) Math.random() * 10f);
        ret.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, (float) Math.random() * 10f);
        ret.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, (float) Math.random() * 10f);
        ret.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.MARKETING, (float) Math.random() * 10f);
        ret.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.OTHER, (float) Math.random() * 5f);

        return ret;
    }

    public static void init(Resources resources){
        _charityMap = new HashMap<>();

        CharityDetailData newCharity = new CharityDetailData("Paint Branch Cleanup Project", R.drawable.ic_local_florist_black_48dp,
                6600, "Kenilworth Avenue", "Riverdale", "MD", 20737, 1.7f, new int[] { 3, 0, 1, 6, 9, 9, 2, 2, 5, 5 }, "pbcp", "pgparks.com", false,
                "Here at the Paint Branch Cleanup Project, we're committed to keeping Paint Branch stream clean and beautiful, " +
                        "from the Anacostia all throughout Montgomery and Prince George's county. We appreciate donations and we're always " +
                        "looking for more volunteers, so feel free to contact us by phone or email!", 0.9f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 4f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 1f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, 5f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.MARKETING, 2f);

        _charityMap.put(newCharity.getIdentifier(), newCharity);

        newCharity = new CharityDetailData("The Dr. Jon Froehlich Foundation", R.drawable.ic_local_florist_black_48dp,
                555, "Froehlich Street", "College Park", "MD", 20740, 6.1f, new int[] {0, 9, 1, 8, 2, 7, 3, 6, 4, 5 }, "jon_froehlich", "charities.com", false,
                "Here at the Dr. Jon Froehlich Foundation, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 0.6f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 1f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 3f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FOOD, 12f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, 2f);

        _charityMap.put(newCharity.getIdentifier(), newCharity);

        for(int i = 0; i < 15; i++){
            newCharity = generateRandomCharity();
            _charityMap.put(newCharity.getIdentifier(), newCharity);
        }

        /*
        newCharity = new CharityDetailData("Charity B", R.drawable.ic_local_florist_black_48dp,
                999, "B Street", "Silver Spring", "MD", 20901, 3.9f, new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1 }, "charity_b", "charities.com", false,
                "Here at Charity B, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 0.6f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 4f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 8f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.OTHER, 0.5f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, 7f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.MARKETING, 5f);

        _charityMap.put(newCharity.getIdentifier(), newCharity);

        newCharity = new CharityDetailData("Charity X", R.drawable.ic_local_florist_black_48dp,
                101, "X Street", "Chevy Chase", "MD", 20901, 2.0f, new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, "charity_x", "charities.com", false,
                "Here at Charity X, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 0.7f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 2f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 5f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.OTHER, 1f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, 3f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.MARKETING, 8f);

        _charityMap.put(newCharity.getIdentifier(), newCharity);

        colorMap = new HashMap<>();
        colorMap.put(CharityDetailData.DonationSpendingCategory.FOOD, resources.getColor(R.color.spendingCategoryColorFood));
        colorMap.put(CharityDetailData.DonationSpendingCategory.SUPPLIES, resources.getColor(R.color.spendingCategoryColorSupplies));
        colorMap.put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, resources.getColor(R.color.spendingCategoryColorFundraising));
        colorMap.put(CharityDetailData.DonationSpendingCategory.MARKETING, resources.getColor(R.color.spendingCategoryColorMarketing));
        colorMap.put(CharityDetailData.DonationSpendingCategory.SALARY, resources.getColor(R.color.spendingCategoryColorSalary));
        colorMap.put(CharityDetailData.DonationSpendingCategory.OTHER, resources.getColor(R.color.spendingCategoryColorOther));
        */
    }

    public static void populateCharityPosts(){
        for(CharityDetailData c : _charityMap.values()){
            c.populatePosts();
        }
    }

    public static List<CharityDetailData> getCharityList(){
        return new ArrayList<CharityDetailData>(_charityMap.values());
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

    public static CharityDetailData getCharityByDisplayName(String displayName){
        for(CharityDetailData data : _charityMap.values()){
            if(data.getDisplayName().equals(displayName)){
                return data;
            }
        }
        return null;
    }

    public static CharityDetailData getUserAutoDonateCharity(){
        for(CharityDetailData data : _charityMap.values()){
            if(data.isCurrentRecipient()){
                return data;
            }
        }

        return null;
    }

    public static Comparator<CharityDetailData> getComparatorForSuggestionSortType(SearchActivity.SuggestionSortType sortType){
        switch (sortType){
            case RATING:
                return RATING_COMPARATOR;
            case DISTANCE:
                return DISTANCE_COMPARATOR;
        }

        return NAME_COMPARATOR;
    }

    public static List<CharityDetailData> getSortedListOfDiscoverableCharities(Comparator<CharityDetailData> comparator){
        SortedSet<CharityDetailData> charities;
        if(comparator != null){
            charities = new TreeSet<>(comparator);
        } else {
            charities = new TreeSet<>(NAME_COMPARATOR);
        }

        for(CharityDetailData data : _charityMap.values()){
            if(!data.isCurrentRecipient()){
                charities.add(data);
            }
        }

        return new ArrayList<>(charities);
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
