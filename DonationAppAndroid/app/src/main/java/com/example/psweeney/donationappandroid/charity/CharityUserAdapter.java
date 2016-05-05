package com.example.psweeney.donationappandroid.charity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.PercentageView;
import com.example.psweeney.donationappandroid.R;
import com.example.psweeney.donationappandroid.feed.DonationPostData;
import com.example.psweeney.donationappandroid.feed.PostContainer;
import com.example.psweeney.donationappandroid.feed.PostData;
import com.example.psweeney.donationappandroid.feed.PostFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by psweeney on 5/3/16.
 */
public class CharityUserAdapter extends ArrayAdapter {
    private String _charityDisplayName = "";
    private List<PostData> _userDonations = new ArrayList<>();
    public CharityUserAdapter(Context context, int resource, String charityDisplayName) {
        super(context, resource, PostFactory.getAllDonationsFromAuthorToRecipient(DonationPostData.USER_POST_NAME, charityDisplayName));
        _charityDisplayName = charityDisplayName;
        _userDonations.addAll(PostFactory.getAllDonationsFromAuthorToRecipient(DonationPostData.USER_POST_NAME, _charityDisplayName));
    }

    @Override
    public int getCount() {
        return _userDonations.size() + 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (position){
            case 0:
                return buildPersonalHistoryView(parent);
            case 1:
                ViewGroup titleBarContainer = (ViewGroup) inflater.inflate(R.layout.charity_posts_header, parent, false);
                TextView titleText = (TextView) titleBarContainer.findViewById(R.id.textViewRecentPostsHeader);
                String titleString = "My Donations to " + _charityDisplayName;
                titleText.setText(titleString);
                return titleText;
        }

        PostData currData = _userDonations.get(position - 2);
        PostContainer postContainer = (PostContainer) inflater.inflate(R.layout.feed_post_donation, parent, false);
        postContainer.setData(currData);
        postContainer.updateViews();
        return postContainer;
    }

    public String getCharityDisplayName(){
        return _charityDisplayName;
    }

    public void setCharityDisplayName(String charityDisplayName){
        _charityDisplayName = charityDisplayName;
        _userDonations.clear();
        _userDonations.addAll(PostFactory.getAllDonationsFromAuthorToRecipient(DonationPostData.USER_POST_NAME, _charityDisplayName));
        notifyDataSetChanged();
    }

    public void refresh(){
        _userDonations.clear();
        _userDonations.addAll(PostFactory.getAllDonationsFromAuthorToRecipient(DonationPostData.USER_POST_NAME, _charityDisplayName));
        notifyDataSetChanged();
    }

    private LinearLayout buildPersonalHistoryView(ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout container = (LinearLayout) inflater.inflate(R.layout.charity_detail_user_history, parent, false);

        TextView line1 = (TextView) container.findViewById(R.id.textViewPersonalHistoryLine1);
        TextView line2 = (TextView) container.findViewById(R.id.textViewPersonalHistoryLine2);
        TextView line3 = (TextView) container.findViewById(R.id.textViewPersonalHistoryLine3);
        TextView line4 = (TextView) container.findViewById(R.id.textViewPersonalHistoryLine4);

        TextView value1 = (TextView) container.findViewById(R.id.textViewPersonalHistoryValue1);
        TextView value2 = (TextView) container.findViewById(R.id.textViewPersonalHistoryValue2);
        TextView value3 = (TextView) container.findViewById(R.id.textViewPersonalHistoryValue3);

        PercentageView percentageBar = (PercentageView) container.findViewById(R.id.percentageViewPersonalHistory);

        String textLine2 = getContext().getResources().getString(R.string.charity_personal_history_text_2a) + " " +
                _charityDisplayName + " " + getContext().getResources().getString(R.string.charity_personal_history_text_2b);
        line2.setText(textLine2);

        String textLine3 = _charityDisplayName + " " + getContext().getResources().getString(R.string.charity_personal_history_text_3);
        line3.setText(textLine3);

        value1.setText(DonationPostData.getDonationAmountDisplayString(
                PostFactory.getAuthorDonationTotalForRecipient(DonationPostData.USER_POST_NAME, _charityDisplayName)));

        String textValue2 = PostFactory.getDaysBetweenFirstAndLastPost(DonationPostData.USER_POST_NAME, _charityDisplayName) + " days.";
        value2.setText(textValue2);

        float ratio = PostFactory.getUserDonationRatioForRecipient(DonationPostData.USER_POST_NAME, _charityDisplayName);
        value3.setText(PercentageView.getPercentageStringForRatio(ratio));

        percentageBar.setProgressRatio(ratio);

        return container;
    }
}
