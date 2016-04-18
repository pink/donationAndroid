package com.example.psweeney.donationappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.psweeney.donationappandroid.feed.DonationPostData;
import com.example.psweeney.donationappandroid.feed.FeedPostAdapter;
import com.example.psweeney.donationappandroid.feed.PostData;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        final ListView listview = (ListView) findViewById(R.id.listViewUser);

        List<PostData> dataList = new ArrayList<>();

        dataList.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity A", 509));
        dataList.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity B", 142));
        dataList.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity D", 333));
        dataList.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity X", 601));
        dataList.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity A", 49));
        dataList.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity Z", 11));
        dataList.add(new DonationPostData(R.drawable.ic_photo_black_48dp, null, "Charity B", 15));

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        /*
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        */

        final FeedPostAdapter adapter = new FeedPostAdapter(this, R.layout.feed_post_donation, dataList);

        listview.setAdapter(adapter);
    }
}
