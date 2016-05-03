package com.example.psweeney.donationappandroid.feed;

import android.widget.ListView;

import com.example.psweeney.donationappandroid.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * Created by psweeney on 4/22/16.
 */
public class PostFactory {
    public static int defUserIconId = R.drawable.ic_account_box_black_48dp;
    public static int defCharityIconId = R.drawable.ic_local_florist_black_48dp;
    public static int defBodyImageIconId = R.drawable.ic_photo_library_black_48dp;
    public static String steveName = "Steve Fessler";
    public static String neilName = "Neil Alberg";
    public static String seanName = "Sean Kallungal";
    public static String jonName = "Dr. Jon Froehlich";
    public static Calendar lastUpdate;

    private static Map<Integer, PostData> _postMap;
    private static Comparator<PostData> postComparator = new Comparator<PostData>(){
        public int compare(PostData p1, PostData p2){
            if(p1 == null){
                return 1;
            } else if(p2 == null){
                return -1;
            }

            int calendarCompare = p2.getPostTime().compareTo(p1.getPostTime());
            if(calendarCompare != 0){
                return calendarCompare;
            }

            return p2.getPostIdentifier() - p1.getPostIdentifier();
        }
    };

    public static void init(){
        _postMap = new HashMap<>();
        populateUserPosts();
        populateFriendPosts();
        populateCharityPosts();
        lastUpdate = Calendar.getInstance();
    }

    private static void populateUserPosts(){
        PostData newPost = new DonationPostData(defUserIconId, null, "Charity A", 509);
        newPost.getPostTime().set(Calendar.YEAR, newPost.getPostTime().get(Calendar.YEAR) - 2);

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, null, "Charity B", 142);
        newPost.getPostTime().set(Calendar.MONTH, newPost.getPostTime().get(Calendar.MONTH) - 5);

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, null, "Charity D", 330);
        newPost.getPostTime().set(Calendar.DAY_OF_MONTH, newPost.getPostTime().get(Calendar.DAY_OF_MONTH) - 15);

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, null, "Charity X", 601);
        newPost.getPostTime().set(Calendar.DAY_OF_MONTH, newPost.getPostTime().get(Calendar.DAY_OF_MONTH) - 2);

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, null, "Charity A", 49);
        if(newPost.getPostTime().get(Calendar.AM_PM) == Calendar.PM){
            newPost.getPostTime().set(Calendar.AM_PM, Calendar.AM);
        } else {
            newPost.getPostTime().set(Calendar.DAY_OF_MONTH, newPost.getPostTime().get(Calendar.DAY_OF_MONTH) - 1);
            newPost.getPostTime().set(Calendar.AM_PM, Calendar.PM);
        }

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, null, "Charity Z", 11);
        newPost.getPostTime().set(Calendar.MINUTE, Math.max(0, newPost.getPostTime().get(Calendar.MINUTE) - 10));

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, null, "Charity B", 15);

        _postMap.put(newPost.getPostIdentifier(), newPost);
    }

    private static void populateFriendPosts(){
        ArrayList<CommentData> comments = new ArrayList<>();
        comments.add(new CommentData(steveName, "Nice"));
        comments.add(new CommentData(jonName, "Super cool"));
        comments.add(new CommentData(neilName, "hi"));
        comments.add(new CommentData(neilName, "hi"));
        comments.add(new CommentData(seanName, "that's a whole lot of money jon"));
        comments.add(new CommentData(jonName, "it sure is"));

        PostData newPost = new DonationPostData(defUserIconId, jonName, "The Dr. Jon Froehlich Foundation",
                Calendar.getInstance(), 99999, 2, false, comments);
        newPost.getPostTime().set(Calendar.YEAR, newPost.getPostTime().get(Calendar.YEAR) - 3);

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, neilName, "Charity A", 142);
        newPost.getPostTime().set(Calendar.MONTH, newPost.getPostTime().get(Calendar.MONTH) - 3);

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, neilName, "Charity B", 840);
        newPost.getPostTime().set(Calendar.DAY_OF_MONTH, newPost.getPostTime().get(Calendar.DAY_OF_MONTH) - 17);

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, seanName, "Charity T", 84);
        newPost.getPostTime().set(Calendar.DAY_OF_MONTH, newPost.getPostTime().get(Calendar.DAY_OF_MONTH) - 4);

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, steveName, "Charity O", 136);
        if(newPost.getPostTime().get(Calendar.AM_PM) == Calendar.PM){
            newPost.getPostTime().set(Calendar.AM_PM, Calendar.AM);
        } else {
            newPost.getPostTime().set(Calendar.DAY_OF_MONTH, newPost.getPostTime().get(Calendar.DAY_OF_MONTH) - 1);
            newPost.getPostTime().set(Calendar.AM_PM, Calendar.PM);
        }

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, seanName, "Charity E", 487);
        newPost.getPostTime().set(Calendar.MINUTE, Math.max(0, newPost.getPostTime().get(Calendar.MINUTE) - 20));

        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new DonationPostData(defUserIconId, steveName, "Charity F", 104);

        _postMap.put(newPost.getPostIdentifier(), newPost);
    }

    private static void populateCharityPosts(){
        PostData newPost = new CharityPostData(defCharityIconId, "Charity A",
                "Hello everyone,\nThank you for donating to Charity A this month. We're grateful for " +
                        "every donation we get.\n\nSincerely,\nThe Charity A Staff");
        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new CharityPostData(defCharityIconId, "Charity B", defBodyImageIconId,
                "Hello everyone,\nCheck out our new photos from the Charity B volunteer event last Sunday. We appreciate " +
                        "the help as well as your continued support through donations.\n\nSincerely,\nThe Charity B Staff");

        newPost.getPostTime().set(Calendar.DAY_OF_MONTH, Math.max(1, newPost.getPostTime().get(Calendar.DAY_OF_MONTH) - 1));
        _postMap.put(newPost.getPostIdentifier(), newPost);

        newPost = new CharityPostData(defCharityIconId, "Charity A", defBodyImageIconId,
                "Hello everyone,\nCheck out our new photos from the Charity A volunteer event last Saturday. We appreciate " +
                        "the help as well as your continued support through donations.\n\nSincerely,\nThe Charity A Staff");

        newPost.getPostTime().set(Calendar.DAY_OF_MONTH, Math.max(1, newPost.getPostTime().get(Calendar.DAY_OF_MONTH) - 1));
        _postMap.put(newPost.getPostIdentifier(), newPost);
    }

    public static PostData getPostById(int key){
        if(_postMap.containsKey(key)){
            return _postMap.get(key);
        }
        return null;
    }

    public static List<PostData> getAllPostsByAuthor(String authorDisplayName){
        Set<PostData> postsByAuthor = new TreeSet<>(postComparator);
        for(PostData p : _postMap.values()){
            if(p.getAuthorDisplayName().equals(authorDisplayName)){
                postsByAuthor.add(p);
            }
        }

        return new ArrayList<>(postsByAuthor);
    }

    public static int getAuthorDonationTotal(String authorDisplayName){
        List<PostData> authorPosts = getAllPostsByAuthor(authorDisplayName);
        int total = 0;
        for(PostData p : authorPosts){
            if(p instanceof DonationPostData)
                total += ((DonationPostData) p).getDonationAmountCents();
        }
        return total;
    }

    public static int getAuthorDonationTotalForRecipient(String authorDisplayName, String recipientDisplayName){
        List<DonationPostData> posts = getAllDonationsFromAuthorToRecipient(authorDisplayName, recipientDisplayName);
        int total = 0;
        for(DonationPostData p : posts){
            total += p.getDonationAmountCents();
        }
        return total;
    }

    public static int getDaysBetweenFirstAndLastPost(String authorDisplayName){
        List<PostData> posts = getAllPostsByAuthor(authorDisplayName);
        if(posts == null || posts.size() <= 0){
            return 0;
        }
        Calendar firstPostTime = posts.get(posts.size() - 1).getPostTime();
        Calendar lastPostTime = posts.get(0).getPostTime();

        Date firstDate = new Date(firstPostTime.get(Calendar.YEAR), firstPostTime.get(Calendar.MONTH), firstPostTime.get(Calendar.DAY_OF_MONTH));
        Date lastDate = new Date(lastPostTime.get(Calendar.YEAR), lastPostTime.get(Calendar.MONTH), lastPostTime.get(Calendar.DAY_OF_MONTH));
        long diff = lastDate.getTime() - firstDate.getTime();

        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static int getDaysBetweenFirstAndLastPost(String authorDisplayName, String recipientDisplayName){
        List<DonationPostData> posts = getAllDonationsFromAuthorToRecipient(authorDisplayName, recipientDisplayName);
        if(posts == null || posts.size() <= 0){
            return 0;
        }
        Calendar firstPostTime = posts.get(posts.size() - 1).getPostTime();
        Calendar lastPostTime = posts.get(0).getPostTime();

        Date firstDate = new Date(firstPostTime.get(Calendar.YEAR), firstPostTime.get(Calendar.MONTH), firstPostTime.get(Calendar.DAY_OF_MONTH));
        Date lastDate = new Date(lastPostTime.get(Calendar.YEAR), lastPostTime.get(Calendar.MONTH), lastPostTime.get(Calendar.DAY_OF_MONTH));
        long diff = lastDate.getTime() - firstDate.getTime();

        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static List<DonationPostData> getAllDonationsFromAuthorToRecipient(String authorDisplayName, String recipientDisplayName){
        Set<DonationPostData> posts = new TreeSet<>(postComparator);
        for(PostData p : _postMap.values()){
            if(p instanceof DonationPostData && p.getAuthorDisplayName().equals(authorDisplayName) &&
                    ((DonationPostData) p).getRecipientDisplayName().equals(recipientDisplayName)){
                posts.add((DonationPostData) p);
            }
        }
        return new ArrayList<DonationPostData>(posts);
    }

    public static float getUserDonationRatioForRecipient(String authorDisplayName, String recipientDisplayName){
        float donationTotal = getAuthorDonationTotal(authorDisplayName);
        float recipientTotal = getAuthorDonationTotalForRecipient(authorDisplayName, recipientDisplayName);

        if(donationTotal <= 0){
            return 0;
        }

        return recipientTotal/donationTotal;
    }

    public static void addPost(PostData postData){
        if(postData != null){
            _postMap.put(postData.getPostIdentifier(), postData);
        }
        lastUpdate = Calendar.getInstance();
    }

    public static List<PostData> getAllUserPosts(){
        Set<PostData> userPosts = new TreeSet<>(postComparator);

        for(PostData p : _postMap.values()){
            if(p.getAuthorDisplayName().equals(DonationPostData.USER_POST_NAME)){
                userPosts.add(p);
            }
        }

        return new ArrayList<>(userPosts);
    }

    public static List<PostData> getAllFriendPosts(){
        Set<PostData> friendPosts = new TreeSet<>(postComparator);

        for(PostData p : _postMap.values()){
            if(p.getPostType() == PostData.PostType.DONATION && !p.getAuthorDisplayName().equals(DonationPostData.USER_POST_NAME)){
                friendPosts.add(p);
            }
        }

        return new ArrayList<>(friendPosts);
    }

    public static List<PostData> getAllCharityPosts(){
        Set<PostData> charityPosts = new TreeSet<>(postComparator);

        for(PostData p : _postMap.values()){
            if(p.getPostType() == PostData.PostType.CHARITY){
                charityPosts.add(p);
            }
        }

        return new ArrayList<>(charityPosts);
    }
}
