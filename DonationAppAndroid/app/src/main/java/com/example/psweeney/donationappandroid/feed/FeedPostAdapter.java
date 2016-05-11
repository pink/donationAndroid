package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.psweeney.donationappandroid.R;

import java.util.List;

/**
 * Created by psweeney on 4/18/16.
 *
 * ArrayAdapter extension for displaying PostData objects of either type in a FeedActivity
 */

public class FeedPostAdapter extends ArrayAdapter{
    private List<PostData> _postDataList;
    private int _numPosts = 10;

    private static final int NUM_POSTS_DEFAULT_INCREMENT_AMOUNT = 10;

    public FeedPostAdapter(Context context, int resource, List<PostData> postDataList) {
        super(context, resource, postDataList);
        _postDataList = postDataList;
    }

    @Override
    public int getCount() {
        int numShown = Math.min(_postDataList.size(), _numPosts);
        if(_postDataList.size() > _numPosts){
            numShown++;
        }

        return numShown;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(_postDataList.size() > _numPosts && position == getCount() - 1){
            return inflater.inflate(R.layout.load_more_button, parent, false);
        }

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
