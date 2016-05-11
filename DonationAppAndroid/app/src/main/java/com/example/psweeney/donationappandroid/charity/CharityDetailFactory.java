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
import java.util.TreeSet;

/**
 * Created by psweeney on 4/22/16.
 *
 * Used for retrieving and editing CharityDetailData across different classes and activities
 *
 * During the SplashScreen activity (setup period), this class's init() method is called, followed by
 * PostFactory's init() method, and finally this class's populateCharityPosts() method.
 *
 * A few CharityDetailData objects added to CHARITY_MAP are hardcoded but most are randomly
 * generated.
 *
 */
public class CharityDetailFactory {
    private static final Map<Integer, CharityDetailData> CHARITY_MAP = new HashMap<>();
    private static final int NUM_RANDOM_CHARITIES = 15;

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

    /**
     * Generates random charity
     *
     * @return randomized CharityDetailData object
     */
    public static CharityDetailData generateRandomCharity(){
        String charityChar = "" + (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));

        String charityName = "Charity " + charityChar;

        if(searchByCharityName(charityName) != null){
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

    /**
     * Generates a few hardcoded charities and some number of randomly generated charities
     * according to NUM_RANDOM_CHARITIES
     *
     */

    public static void init(){
        CharityDetailData newCharity = new CharityDetailData("Paint Branch Cleanup Project", R.drawable.ic_local_florist_black_48dp,
                6600, "Kenilworth Avenue", "Riverdale", "MD", 20737, 1.7f, new int[] { 3, 0, 1, 6, 9, 9, 2, 2, 5, 5 }, "pbcp", "pgparks.com", false,
                "Here at the Paint Branch Cleanup Project, we're committed to keeping Paint Branch stream clean and beautiful, " +
                        "from the Anacostia all throughout Montgomery and Prince George's county. We appreciate donations and we're always " +
                        "looking for more volunteers, so feel free to contact us by phone or email!", 0.9f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 4f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 1f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, 5f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.MARKETING, 2f);

        CHARITY_MAP.put(newCharity.getIdentifier(), newCharity);

        newCharity = new CharityDetailData("The Dr. Jon Froehlich Foundation", R.drawable.ic_local_florist_black_48dp,
                555, "Froehlich Street", "College Park", "MD", 20740, 6.1f, new int[] {0, 9, 1, 8, 2, 7, 3, 6, 4, 5 }, "jon_froehlich", "charities.com", false,
                "Here at the Dr. Jon Froehlich Foundation, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 1.0f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 1f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 3f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FOOD, 12f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, 2f);

        CHARITY_MAP.put(newCharity.getIdentifier(), newCharity);

        for(int i = 0; i < NUM_RANDOM_CHARITIES; i++){
            newCharity = generateRandomCharity();
            CHARITY_MAP.put(newCharity.getIdentifier(), newCharity);
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

        CHARITY_MAP.put(newCharity.getIdentifier(), newCharity);

        newCharity = new CharityDetailData("Charity X", R.drawable.ic_local_florist_black_48dp,
                101, "X Street", "Chevy Chase", "MD", 20901, 2.0f, new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, "charity_x", "charities.com", false,
                "Here at Charity X, we think that sdhbsdksdfgbm\nsdfsdfsdf\nasdfsfgdfhdh\nsdfgsdfhshfh\n", 0.7f, null);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SUPPLIES, 2f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.SALARY, 5f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.OTHER, 1f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, 3f);
        newCharity.getSpendingBreakdown().put(CharityDetailData.DonationSpendingCategory.MARKETING, 8f);

        CHARITY_MAP.put(newCharity.getIdentifier(), newCharity);

        colorMap = new HashMap<>();
        colorMap.put(CharityDetailData.DonationSpendingCategory.FOOD, resources.getColor(R.color.spendingCategoryColorFood));
        colorMap.put(CharityDetailData.DonationSpendingCategory.SUPPLIES, resources.getColor(R.color.spendingCategoryColorSupplies));
        colorMap.put(CharityDetailData.DonationSpendingCategory.FUNDRAISING, resources.getColor(R.color.spendingCategoryColorFundraising));
        colorMap.put(CharityDetailData.DonationSpendingCategory.MARKETING, resources.getColor(R.color.spendingCategoryColorMarketing));
        colorMap.put(CharityDetailData.DonationSpendingCategory.SALARY, resources.getColor(R.color.spendingCategoryColorSalary));
        colorMap.put(CharityDetailData.DonationSpendingCategory.OTHER, resources.getColor(R.color.spendingCategoryColorOther));
        */
    }

    /**
     * This is called after PostFactory.init() has completed; it populates the posts data structure of
     * each CharityDetailData object based on what posts have been created in PostFactory
     *
     */

    public static void populateCharityPosts(){
        for(CharityDetailData c : CHARITY_MAP.values()){
            c.populatePosts();
        }
    }

    /**
     * Returns a list of all CharityDetailData objects stored in CHARITY_MAP
     * @return List of CharityDetailData objects
     *
     */

    public static List<CharityDetailData> getCharityList(){
        return new ArrayList<CharityDetailData>(CHARITY_MAP.values());
    }

    /**
     * Attempt to find a CharityDetailData object with a display name matching the name parameter
     * @param name String used to search for charity name
     * @return CharityDetailData object matching name if found, null otherwise
     *
     */

    public static CharityDetailData searchByCharityName(String name){
        for(CharityDetailData c : CHARITY_MAP.values()){
            if(c.getDisplayName().equals(name)){
                return c;
            }
        }
        return null;
    }

    /**
     * Attempt to find a CharityDetailData object with an identifier matching the key parameter
     * @param key Integer used to search for charity key
     * @return CharityDetailData object matching identifier if found, null otherwise
     *
     */

    public static CharityDetailData getCharityById(Integer key){
        if(!CHARITY_MAP.containsKey(key)){
            return null;
        }
        return CHARITY_MAP.get(key);
    }

    /**
     * Attempt to find the CharityDetailData object that the user has set as their auto donate recipient
     * @return CharityDetailData object with (isCurrentRecipient() == true) if exists, null otherwise
     *
     */

    public static CharityDetailData getUserAutoDonateCharity(){
        for(CharityDetailData data : CHARITY_MAP.values()){
            if(data.isCurrentRecipient()){
                return data;
            }
        }

        return null;
    }

    /**
     * Returns one of this classes Comparators based on the sortType parameter
     * @param sortType SuggestionSortType corresponding to desired Comparator
     * @return Comparator<CharityDetailData> corresponding to sortType (default is NAME_COMPARATOR)
     *
     */
    public static Comparator<CharityDetailData> getComparatorForSuggestionSortType(SearchActivity.SuggestionSortType sortType){
        switch (sortType){
            case RATING:
                return RATING_COMPARATOR;
            case DISTANCE:
                return DISTANCE_COMPARATOR;
        }

        return NAME_COMPARATOR;
    }

    /**
     * Get list of all charities besides the user's auto-donate recipient, sorted according to the comparator parameter
     * @param comparator Comparator<CharityDetailData> used to sort list. Will use NAME_COMPARATOR if null.
     * @return sorted List<CharityDetailData> containing all charities besides the user's auto-donate recipient
     */

    public static List<CharityDetailData> getSortedListOfDiscoverableCharities(Comparator<CharityDetailData> comparator){
        SortedSet<CharityDetailData> charities;
        if(comparator != null){
            charities = new TreeSet<>(comparator);
        } else {
            charities = new TreeSet<>(NAME_COMPARATOR);
        }

        for(CharityDetailData data : CHARITY_MAP.values()){
            if(!data.isCurrentRecipient()){
                charities.add(data);
            }
        }

        return new ArrayList<>(charities);
    }

    /**
     * Calls setIsCurrentRecipient(false) on all charities besides the one with the given charityId,
     * which is set to true
     *
     * @param charityId Identifier key used to search for new auto-donate recipient
     *
     */

    public static void setUserAutoDonateCharity(Integer charityId){
        if(!CHARITY_MAP.containsKey(charityId)){
            return;
        }

        for(CharityDetailData data : CHARITY_MAP.values()){
            data.setIsCurrentRecipient(false);
        }

        CHARITY_MAP.get(charityId).setIsCurrentRecipient(true);

    }
}
