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
import com.example.psweeney.donationappandroid.feed.CommentData;
import com.example.psweeney.donationappandroid.feed.PostContainer;
import com.example.psweeney.donationappandroid.feed.PostData;

import java.util.List;

/**
 * Created by psweeney on 4/22/16.
 */
public class CharityProfileAdapter extends ArrayAdapter {
    private CharityDetailData _data;

    public CharityProfileAdapter(Context context, int resource, CharityDetailData data) {
        super(context, resource, data.getPosts());
        _data = data;

    }

    @Override
    public int getCount() {
        return _data.getPosts().size() + 5;
    }

    public CharityDetailData getData() {
        return _data;
    }

    public static void updateContactViews(View v, CharityDetailData data, Resources resources){
        if(data == null || v == null || resources == null){
            return;
        }

        ImageView icon = (ImageView) v.findViewById(R.id.imageViewContactIcon);
        TextView address = (TextView) v.findViewById(R.id.textViewCharityAddress);
        TextView phone = (TextView) v.findViewById(R.id.textViewCharityPhone);
        TextView email = (TextView) v.findViewById(R.id.textViewCharityEmail);

        if(icon != null){
            icon.setImageDrawable(resources.getDrawable(data.getIconId()));
        }

        if(address != null){
            address.setText(data.getAddressShort());
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
            container.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark));
            buttonText.setBackgroundColor(resources.getColor(R.color.colorAccent));
            buttonText.setTextColor(resources.getColor(R.color.colorPrimaryDark));
            buttonText.setText(resources.getString(R.string.charity_auto_donate_label_true));
        } else {
            container.setBackgroundColor(resources.getColor(R.color.colorPrimary));
            buttonText.setBackgroundColor(resources.getColor(R.color.colorPrimary));
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
                updateContactViews(ret, _data, context.getResources());
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
                ret = inflater.inflate(R.layout.charity_recent_posts_header, parent, false);
                break;
        }
        if(ret != null){
            return ret;
        }

        PostData currPost = _data.getPosts().get(position - 5);
        PostContainer postContainer = (PostContainer) inflater.inflate(R.layout.feed_post_charity, parent, false);
        postContainer.setData(currPost);
        postContainer.updateViews();
        return postContainer;
    }
}
