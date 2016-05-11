package com.example.psweeney.donationappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psweeney.donationappandroid.charity.CharityDetailData;
import com.example.psweeney.donationappandroid.charity.CharityDetailFactory;
import com.example.psweeney.donationappandroid.feed.FeedPostAdapter;
import com.example.psweeney.donationappandroid.feed.PostContainer;
import com.example.psweeney.donationappandroid.feed.PostFactory;

import java.util.Calendar;

/**
 * Created by psweeney
 *
 * This activity is where the user will view their post feeds, with separate feeds for posts by the user,
 * posts by the user's friends, and posts by charities the user has interacted with in the past.
 *
 * The user can like posts from the feed activity, but clicking the post body or the comment body will
 * take them to a new SinglePostActivity where further interaction can happen.
 *
 */

public class FeedActivity extends AppCompatActivity {
    private enum FeedType{
        USER, FRIEND, CHARITY
    }

    public static final String CURRENT_USER_DISPLAY_NAME = "Current user";
    public static final String COMMENT_FIELD_SELECTED_KEY = "commentFieldSelected";

    private FeedType _currentSelection = FeedType.USER;
    private PostContainer _lastInteraction = null;
    private Calendar lastPostRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        refreshFeeds();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                _lastInteraction.updateViews();
            }
        }
        if(lastPostRefresh != PostFactory.lastUpdate){
            refreshFeeds();
        }
    }

    private void refreshFeeds(){
        ListView userFeed = (ListView) findViewById(R.id.listViewUser);
        ListView friendFeed = (ListView) findViewById(R.id.listViewFriend);
        ListView charityFeed = (ListView) findViewById(R.id.listViewCharity);

        if(userFeed != null){
            FeedPostAdapter userAdapter = new FeedPostAdapter(this, R.layout.feed_post_donation, PostFactory.getAllUserPosts());
            userFeed.setAdapter(userAdapter);
            userAdapter.notifyDataSetChanged();
            userFeed.invalidate();
        }

        if(friendFeed != null){
            FeedPostAdapter friendAdapter = new FeedPostAdapter(this, R.layout.feed_post_donation, PostFactory.getAllFriendPosts());
            friendFeed.setAdapter(friendAdapter);
            friendAdapter.notifyDataSetChanged();
            friendFeed.invalidate();
        }

        if(charityFeed != null){
            FeedPostAdapter charityAdapter = new FeedPostAdapter(this, R.layout.feed_post_charity, PostFactory.getAllCharityPosts());
            charityFeed.setAdapter(charityAdapter);
            charityAdapter.notifyDataSetChanged();
            charityFeed.invalidate();
        }

        lastPostRefresh = PostFactory.lastUpdate;
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

    public void viewPost(View v, boolean commentRequested){
        if(v == null){
            return;
        }

        PostContainer parentPostContainer = FeedPostAdapter.getParentPostContainer(v);
        _lastInteraction = parentPostContainer;

        if(parentPostContainer == null){
            Toast.makeText(getApplicationContext(), "Something went wrong while trying to view this post (invalid container)", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent singlePostIntent = new Intent(getApplicationContext(), SinglePostActivity.class);
        Bundle bundle = parentPostContainer.getData().convertToBundle();

        if(singlePostIntent == null || bundle == null){
            Toast.makeText(getApplicationContext(), "Something went wrong while trying to view this post (null bundle)", Toast.LENGTH_SHORT).show();
            return;
        }

        bundle.putBoolean(COMMENT_FIELD_SELECTED_KEY, commentRequested);

        singlePostIntent.putExtras(bundle);
        startActivityForResult(singlePostIntent, 1);
    }

    public void onClickCharityIcon(View v){
        Animation.defaultButtonAnimation(v);

        PostContainer postContainer = FeedPostAdapter.getParentPostContainer(v);
        if(postContainer == null || postContainer.getData() == null){
            return;
        }

        CharityDetailData charityDetailData = CharityDetailFactory.searchByCharityName(postContainer.getData().getAuthorDisplayName());
        if(charityDetailData == null){
            return;
        }

        Intent intent = new Intent(getApplicationContext(), CharityDetailActivity.class);
        Bundle bundle = new Bundle();
        if(bundle == null){
            return;
        }

        bundle.putInt(CharityDetailData.CHARITY_IDENTIFIER_KEY, charityDetailData.getIdentifier());
        intent.putExtras(bundle);

        startActivityForResult(intent, 1);
    }

    public void onButtonClickUserFeed(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSelection != FeedType.USER){
            _currentSelection = FeedType.USER;
            updateFeedSelection();
        }
    }

    public void onButtonClickFriendFeed(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSelection != FeedType.FRIEND){
            _currentSelection = FeedType.FRIEND;
            updateFeedSelection();
        }
    }

    public void onButtonClickCharityFeed(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSelection != FeedType.CHARITY){
            _currentSelection = FeedType.CHARITY;
            updateFeedSelection();
        }
    }

    public void onButtonClickPostBody(View v){
        Animation.defaultButtonAnimation(v);
        viewPost(v, false);
    }

    public void onButtonClickLike(View v){
        if(v == null || v.getId() != R.id.frameLayoutLikeContainer){
            return;
        }

        View subContainer = v.findViewById(R.id.likeSubContainer);
        PostContainer parentContainer = FeedPostAdapter.getParentPostContainer(v);
        _lastInteraction = parentContainer;
        parentContainer.getData().setLikedByUser(!parentContainer.getData().likedByUser());
        if(parentContainer.getData().likedByUser()){
            parentContainer.getData().setNumLikes(parentContainer.getData().getNumLikes() + 1);
        } else {
            parentContainer.getData().setNumLikes(Math.max(0, parentContainer.getData().getNumLikes() - 1));
        }

        parentContainer.updateViews();
        Animation.defaultButtonAnimation(subContainer);
    }

    public void onButtonClickComment(View v){
        if(v.getId() != R.id.frameLayoutCommentContainer) {
            return;
        }

        View subContainer = v.findViewById(R.id.commentSubContainer);
        Animation.defaultButtonAnimation(subContainer);

        viewPost(v, true);
    }

    public void onButtonClickLoadMore(View v){
        Animation.defaultButtonAnimation(v);
        ListView listView;

        switch (_currentSelection){
            case USER:
                listView = (ListView) findViewById(R.id.listViewUser);
                break;
            case FRIEND:
                listView = (ListView) findViewById(R.id.listViewFriend);
                break;
            case CHARITY:
                listView = (ListView) findViewById(R.id.listViewCharity);
                break;
            default:
                return;
        }

        FeedPostAdapter adapter = (FeedPostAdapter) listView.getAdapter();
        adapter.incrementNumPosts();
        listView.invalidate();
    }
}
