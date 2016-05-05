package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;

import java.util.List;

/**
 * Created by psweeney on 4/18/16.
 */
public class FeedPostAdapter extends ArrayAdapter{
    private List<PostData> _postDataList;
    private int _maxPosts = 10;
    public FeedPostAdapter(Context context, int resource, List<PostData> postDataList) {
        super(context, resource, postDataList);
        _postDataList = postDataList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        PostData curr = _postDataList.get(position);

        PostContainer parentContainer = null;
        if(curr.getPostType() == PostData.PostType.DONATION){
            parentContainer = (PostContainer) inflater.inflate(R.layout.feed_post_donation, parent, false);
        } else if(curr.getPostType() == PostData.PostType.CHARITY){
            parentContainer = (PostContainer) inflater.inflate(R.layout.feed_post_charity, parent, false);
        }

        if(parentContainer == null){
            return null;
        }
        parentContainer.setData(curr);
        parentContainer.updateViews();

        return parentContainer;
    }

    public static PostContainer getParentPostContainer(View v){
        if(v instanceof PostContainer){
            return (PostContainer) v;
        }

        if(v == null){
            return null;
        }

        return getParentPostContainer((View) v.getParent());
    }
}
