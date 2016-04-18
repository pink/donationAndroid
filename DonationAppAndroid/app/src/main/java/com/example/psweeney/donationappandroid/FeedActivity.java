package com.example.psweeney.donationappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.feed.CharityTextPostData;
import com.example.psweeney.donationappandroid.feed.DonationPostData;
import com.example.psweeney.donationappandroid.feed.FeedPostAdapter;
import com.example.psweeney.donationappandroid.feed.PostData;

import java.util.ArrayList;
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

        dataListUser.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity A", 509));
        dataListUser.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity B", 142));
        dataListUser.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity D", 333));
        dataListUser.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity X", 601));
        dataListUser.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity A", 49));
        dataListUser.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity Z", 11));
        dataListUser.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity B", 15));

        final FeedPostAdapter adapterUser = new FeedPostAdapter(this, R.layout.feed_post_donation, dataListUser);

        listViewUser.setAdapter(adapterUser);

        final ListView listViewCharity = (ListView) findViewById(R.id.listViewCharity);

        List<PostData> dataListCharity = new ArrayList<>();

        dataListCharity.add(new CharityTextPostData(R.drawable.ic_photo_black_48dp, "Charity A",
                "Hello everyone,\nThank you for donating to Charity A this month. We're grateful for " +
                        "every donation we get.\nSincerely,\nThe Charity A Staff"));

        final FeedPostAdapter adapterCharity = new FeedPostAdapter(this, R.layout.feed_post_charity_text, dataListCharity);

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
}
