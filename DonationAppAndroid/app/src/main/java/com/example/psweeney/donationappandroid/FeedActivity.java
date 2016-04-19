package com.example.psweeney.donationappandroid;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.feed.CharityPostData;
import com.example.psweeney.donationappandroid.feed.DonationPostData;
import com.example.psweeney.donationappandroid.feed.FeedPostAdapter;
import com.example.psweeney.donationappandroid.feed.PostData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    private enum FeedType{
        USER, FRIEND, CHARITY
    }

    private FeedType _currentSelection = FeedType.USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        final ListView listViewUser = (ListView) findViewById(R.id.listViewUser);

        List<PostData> dataListUser = new ArrayList<>();

        Drawable defUserIcon = getResources().getDrawable(R.drawable.ic_account_box_black_48dp);
        Drawable defCharityIcon = getResources().getDrawable(R.drawable.ic_local_florist_black_48dp);
        Drawable defPhotoLibraryIcon = getResources().getDrawable(R.drawable.ic_photo_library_black_48dp);

        DonationPostData newUserDonationPostData1 = new DonationPostData(defUserIcon, null, "Charity A", 509);
        newUserDonationPostData1.getPostTime().set(Calendar.YEAR, newUserDonationPostData1.getPostTime().get(Calendar.YEAR) - 2);

        DonationPostData newUserDonationPostData2 = new DonationPostData(defUserIcon, null, "Charity B", 142);
        newUserDonationPostData2.getPostTime().set(Calendar.MONTH, newUserDonationPostData2.getPostTime().get(Calendar.MONTH) - 5);

        DonationPostData newUserDonationPostData3 = new DonationPostData(defUserIcon, null, "Charity D", 330);
        newUserDonationPostData3.getPostTime().set(Calendar.DAY_OF_MONTH, newUserDonationPostData3.getPostTime().get(Calendar.DAY_OF_MONTH) - 15);

        DonationPostData newUserDonationPostData4 = new DonationPostData(defUserIcon, null, "Charity X", 601);
        newUserDonationPostData4.getPostTime().set(Calendar.DAY_OF_MONTH, newUserDonationPostData4.getPostTime().get(Calendar.DAY_OF_MONTH) - 2);

        DonationPostData newUserDonationPostData5 = new DonationPostData(defUserIcon, null, "Charity A", 49);
        if(newUserDonationPostData5.getPostTime().get(Calendar.AM_PM) == Calendar.PM){
            newUserDonationPostData5.getPostTime().set(Calendar.AM_PM, Calendar.AM);
        } else {
            newUserDonationPostData5.getPostTime().set(Calendar.DAY_OF_MONTH, newUserDonationPostData5.getPostTime().get(Calendar.DAY_OF_MONTH) - 1);
            newUserDonationPostData5.getPostTime().set(Calendar.AM_PM, Calendar.PM);
        }

        DonationPostData newUserDonationPostData6 = new DonationPostData(defUserIcon, null, "Charity Z", 11);
        newUserDonationPostData6.getPostTime().set(Calendar.MINUTE, Math.max(0, newUserDonationPostData6.getPostTime().get(Calendar.MINUTE) - 10));

        DonationPostData newUserDonationPostData7 = new DonationPostData(defUserIcon, null, "Charity B", 15);

        dataListUser.add(newUserDonationPostData7);
        dataListUser.add(newUserDonationPostData6);
        dataListUser.add(newUserDonationPostData5);
        dataListUser.add(newUserDonationPostData4);
        dataListUser.add(newUserDonationPostData3);
        dataListUser.add(newUserDonationPostData2);
        dataListUser.add(newUserDonationPostData1);

        final FeedPostAdapter adapterUser = new FeedPostAdapter(this, R.layout.feed_post_donation, dataListUser);

        listViewUser.setAdapter(adapterUser);

        final ListView listViewFriend = (ListView) findViewById(R.id.listViewFriend);
        String steveName = "Steve Fessler", neilName = "Neil Alberg", seanName = "Sean Kallungal",
                jonName = "Dr. Jon Froehlich";

        List<PostData> dataListFriend = new ArrayList<>();

        DonationPostData newFriendDonationPostData1 = new DonationPostData(defUserIcon, jonName, "The Dr. Jon Froehlich Foundation", 99999);
        newFriendDonationPostData1.getPostTime().set(Calendar.YEAR, newFriendDonationPostData1.getPostTime().get(Calendar.YEAR) - 3);

        DonationPostData newFriendDonationPostData2 = new DonationPostData(defUserIcon, neilName, "Charity A", 142);
        newFriendDonationPostData2.getPostTime().set(Calendar.MONTH, newFriendDonationPostData2.getPostTime().get(Calendar.MONTH) - 3);

        DonationPostData newFriendDonationPostData3 = new DonationPostData(defUserIcon, neilName, "Charity B", 840);
        newFriendDonationPostData3.getPostTime().set(Calendar.DAY_OF_MONTH, newFriendDonationPostData3.getPostTime().get(Calendar.DAY_OF_MONTH) - 17);

        DonationPostData newFriendDonationPostData4 = new DonationPostData(defUserIcon, seanName, "Charity T", 84);
        newFriendDonationPostData4.getPostTime().set(Calendar.DAY_OF_MONTH, newFriendDonationPostData4.getPostTime().get(Calendar.DAY_OF_MONTH) - 4);

        DonationPostData newFriendDonationPostData5 = new DonationPostData(defUserIcon, steveName, "Charity O", 136);
        if(newFriendDonationPostData5.getPostTime().get(Calendar.AM_PM) == Calendar.PM){
            newFriendDonationPostData5.getPostTime().set(Calendar.AM_PM, Calendar.AM);
        } else {
            newFriendDonationPostData5.getPostTime().set(Calendar.DAY_OF_MONTH, newFriendDonationPostData5.getPostTime().get(Calendar.DAY_OF_MONTH) - 1);
            newFriendDonationPostData5.getPostTime().set(Calendar.AM_PM, Calendar.PM);
        }

        DonationPostData newFriendDonationPostData6 = new DonationPostData(defUserIcon, seanName, "Charity E", 487);
        newFriendDonationPostData6.getPostTime().set(Calendar.MINUTE, Math.max(0, newFriendDonationPostData6.getPostTime().get(Calendar.MINUTE) - 20));

        DonationPostData newFriendDonationPostData7 = new DonationPostData(defUserIcon, steveName, "Charity F", 104);

        dataListFriend.add(newFriendDonationPostData7);
        dataListFriend.add(newFriendDonationPostData6);
        dataListFriend.add(newFriendDonationPostData5);
        dataListFriend.add(newFriendDonationPostData4);
        dataListFriend.add(newFriendDonationPostData3);
        dataListFriend.add(newFriendDonationPostData2);
        dataListFriend.add(newFriendDonationPostData1);

        final FeedPostAdapter adapterFriend = new FeedPostAdapter(this, R.layout.feed_post_donation, dataListFriend);

        listViewFriend.setAdapter(adapterFriend);

        final ListView listViewCharity = (ListView) findViewById(R.id.listViewCharity);

        List<PostData> dataListCharity = new ArrayList<>();

        dataListCharity.add(new CharityPostData(defCharityIcon, "Charity A",
                "Hello everyone,\nThank you for donating to Charity A this month. We're grateful for " +
                        "every donation we get.\n\nSincerely,\nThe Charity A Staff"));
        dataListCharity.add(new CharityPostData(defCharityIcon, "Charity B", defPhotoLibraryIcon,
                "Hello everyone,\nCheck out our new photos from the Charity B volunteer event last Sunday. We appreciate" +
                        "the help as well as your continued support through donations.\n\nSincerely,\nThe Charity B Staff"));

        final FeedPostAdapter adapterCharity = new FeedPostAdapter(this, R.layout.feed_post_charity, dataListCharity);

        listViewCharity.setAdapter(adapterCharity);
    }

    private void updateFeedSelection(){

        TextView userLabel = (TextView) findViewById(R.id.textViewUserFeedLabel);
        TextView friendLabel = (TextView) findViewById(R.id.textViewFriendFeedLabel);
        TextView charityLabel = (TextView) findViewById(R.id.textViewCharityFeedLabel);

        ListView userFeed = (ListView) findViewById(R.id.listViewUser);
        ListView friendFeed = (ListView) findViewById(R.id.listViewFriend);
        ListView charityFeed = (ListView) findViewById(R.id.listViewCharity);

        switch (_currentSelection){
            case USER:
                userLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                userLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                friendLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                friendLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                charityLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                charityLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                userFeed.setVisibility(View.VISIBLE);
                friendFeed.setVisibility(View.GONE);
                charityFeed.setVisibility(View.GONE);

                break;
            case FRIEND:
                friendLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                friendLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                userLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                userLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                charityLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                charityLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                userFeed.setVisibility(View.GONE);
                friendFeed.setVisibility(View.VISIBLE);
                charityFeed.setVisibility(View.GONE);

                break;
            case CHARITY:
                charityLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                charityLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                userLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                userLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                friendLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                friendLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                userFeed.setVisibility(View.GONE);
                friendFeed.setVisibility(View.GONE);
                charityFeed.setVisibility(View.VISIBLE);

                break;
        }
    }

    public void onButtonClickUserFeed(View v){
        if(_currentSelection != FeedType.USER){
            _currentSelection = FeedType.USER;
            updateFeedSelection();
        }
    }

    public void onButtonClickFriendFeed(View v){
        if(_currentSelection != FeedType.FRIEND){
            _currentSelection = FeedType.FRIEND;
            updateFeedSelection();
        }
    }

    public void onButtonClickCharityFeed(View v){
        if(_currentSelection != FeedType.CHARITY){
            _currentSelection = FeedType.CHARITY;
            updateFeedSelection();
        }
    }

    public void onButtonClickLike(View v){
        if(v == null || v.getId() != R.id.frameLayoutLikeContainer){
            return;
        }

        ImageView likeIconDisabled = (ImageView) v.findViewById(R.id.imageViewLikeIconDisabled);
        ImageView likeIconEnabled = (ImageView) v.findViewById(R.id.imageViewLikeIconEnabled);

        TextView likeNumText = (TextView) v.findViewById(R.id.textViewLikeNum);
        int likeNum;
        try{
            likeNum = Integer.parseInt(likeNumText.getText().toString());
        } catch (Exception e){
            likeNum = 0;
        }

        if(likeIconDisabled.getVisibility() == View.VISIBLE){
            likeIconDisabled.setVisibility(View.GONE);
            likeIconEnabled.setVisibility(View.VISIBLE);
            likeNum += 1;
        } else {
            likeIconDisabled.setVisibility(View.VISIBLE);
            likeIconEnabled.setVisibility(View.GONE);
            likeNum -= 1;
        }

        likeNumText.setText(Integer.toString(likeNum));
        if(likeNum > 0){
            likeNumText.setVisibility(View.VISIBLE);
        } else {
            likeNumText.setVisibility(View.GONE);
        }
    }
}
