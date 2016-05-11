package com.example.psweeney.donationappandroid.charity;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;
import com.example.psweeney.donationappandroid.feed.PostContainer;
import com.example.psweeney.donationappandroid.feed.PostData;

import java.util.List;

/**
 * Created by psweeney on 4/22/16.
 *
 * ArrayAdapter extension for the Profile section of a CharityDetailActivity
 */

public class CharityProfileAdapter extends ArrayAdapter {
    private CharityDetailData _data;

    private int _numPosts = 10;

    private static final int NUM_POSTS_DEFAULT_INCREMENT_AMOUNT = 10;

    public CharityProfileAdapter(Context context, int resource, CharityDetailData data) {
        super(context, resource, data.getPosts());
        _data = data;

    }

    @Override
    public int getCount() {
        int numShown = Math.min(_data.getPosts().size(), _numPosts);
        if(_data.getPosts().size() > _numPosts){
            numShown++;
        }

        if(numShown <= 0){
            return numShown + 4;
        }

        return numShown + 5;
    }

    public CharityDetailData getData() {
        return _data;
    }

    public static void updateContactViews(View v, CharityDetailData data, Resources resources, boolean showName){
        if(data == null || v == null || resources == null){
            return;
        }

        ImageView icon = (ImageView) v.findViewById(R.id.imageViewContactIcon);
        TextView addressLine1 = (TextView) v.findViewById(R.id.textViewCharityAddressLine1);
        TextView addressLine2 = (TextView) v.findViewById(R.id.textViewCharityAddressLine2);
        TextView phone = (TextView) v.findViewById(R.id.textViewCharityPhone);
        TextView email = (TextView) v.findViewById(R.id.textViewCharityEmail);

        if(icon != null){
            icon.setImageDrawable(resources.getDrawable(data.getIconId()));
        }

        if(addressLine1 != null){
            if(showName){
                addressLine1.setText(data.getDisplayName());
            } else {
                addressLine1.setText(data.getAddressLine1());
            }
        }

        if(addressLine2 != null){
            if(showName){
                addressLine2.setText(data.getAddressLine1());
            } else {
                addressLine2.setText(data.getAddressLine2());
            }
        }

        if(phone != null){
            phone.setText(data.getPhoneNumber());
        }

        if(email != null){
            email.setText(data.getEmailAddress());
        }
    }

    public static void updateAutoDonateViews(View v, CharityDetailData data, Resources resources){
        if(v == null || data == null){
            return;
        }

        View container = v.findViewById(R.id.charityButtonContainer);
        TextView buttonText = (TextView) v.findViewById(R.id.textViewButtonLabel);

        if(buttonText == null){
            return;
        }

        if(data.isCurrentRecipient()) {
            container.setBackgroundColor(resources.getColor(R.color.containerBackgroundLightGrey));
            buttonText.setBackgroundColor(resources.getColor(R.color.colorAccent));
            buttonText.setTextColor(resources.getColor(R.color.containerBackgroundLightGrey));
            buttonText.setText(resources.getString(R.string.charity_auto_donate_label_true));
        } else {
            container.setBackgroundColor(resources.getColor(R.color.containerBackgroundLightGrey));
            buttonText.setBackgroundColor(resources.getColor(R.color.containerBackgroundLightGrey));
            buttonText.setTextColor(resources.getColor(R.color.colorAccent));
            buttonText.setText(resources.getString(R.string.charity_auto_donate_label_false));
        }
    }

    public static void updateDonateNowViews(View v, CharityDetailData data){

    }

    public static void updateBioViews(View v, CharityDetailData data){
        if(v == null || data == null){
            return;
        }

        TextView bio = (TextView) v.findViewById(R.id.textViewBioBody);
        if(bio != null){
            bio.setText(data.getBioText());
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        List<PostData> posts = _data.getPosts();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View ret = null;

        switch (position){
            case 0:
                ret = inflater.inflate(R.layout.charity_contact_info, parent, false);
                updateContactViews(ret, _data, context.getResources(), false);
                break;
            case 1:
                ret = inflater.inflate(R.layout.charity_profile_auto_donate_button, parent, false);
                updateAutoDonateViews(ret, _data, context.getResources());
                break;
            case 2:
                ret = inflater.inflate(R.layout.charity_profile_donate_now_button, parent, false);
                break;
            case 3:
                ret = inflater.inflate(R.layout.charity_profile_bio, parent, false);
                updateBioViews(ret, _data);
                break;
            case 4:
                ret = inflater.inflate(R.layout.charity_posts_header, parent, false);
                break;
        }

        if(ret != null){
            return ret;
        }

        if(_data.getPosts().size() > _numPosts && position == getCount() - 1){
            return inflater.inflate(R.layout.load_more_button, parent, false);

        }

        PostData currPost = _data.getPosts().get(position - 5);
        PostContainer postContainer = (PostContainer) inflater.inflate(R.layout.feed_post_charity, parent, false);
        postContainer.setData(currPost);
        postContainer.updateViews();
        return postContainer;
    }

    public int getNumPosts() {
        return _numPosts;
    }

    public void incrementNumPosts(int incrementAmount) {
        _numPosts += incrementAmount;
        notifyDataSetChanged();
    }

    public void incrementNumPosts(){
        incrementNumPosts(NUM_POSTS_DEFAULT_INCREMENT_AMOUNT);
    }
}
