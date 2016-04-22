package com.example.psweeney.donationappandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.feed.CharityPostData;
import com.example.psweeney.donationappandroid.feed.CommentAdapter;
import com.example.psweeney.donationappandroid.feed.CommentData;
import com.example.psweeney.donationappandroid.feed.DonationPostData;
import com.example.psweeney.donationappandroid.feed.FeedPostAdapter;
import com.example.psweeney.donationappandroid.feed.PostContainer;
import com.example.psweeney.donationappandroid.feed.PostData;

import java.util.List;

public class SinglePostActivity extends AppCompatActivity {
    PostData _data;
    Bundle _bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);

        _bundle = getIntent().getExtras();
        if(_bundle == null || !_bundle.containsKey(PostData.postTypeKey)){
            return;
        }

        if(_bundle.getString(PostData.postTypeKey).equals(PostData.PostType.DONATION.toString())){
            _data = new DonationPostData(_bundle);
        } else if(_bundle.get(PostData.postTypeKey).equals(PostData.PostType.CHARITY.toString())){
            _data = new CharityPostData(_bundle);
        }

        final ListView listViewComments = (ListView) findViewById(R.id.listViewComments);

        listViewComments.setDivider(null);
        listViewComments.setDividerHeight(0);
        List<CommentData> commentDataList = _data.getComments();
        final CommentAdapter commentAdapter = new CommentAdapter(this, R.layout.feed_comment, _data);
        listViewComments.setAdapter(commentAdapter);

        if(_bundle.getBoolean(FeedActivity.commentFieldSelectedKey)){
            listViewComments.setSelection(commentAdapter.getCount() - 1);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(_bundle.getBoolean(FeedActivity.commentFieldSelectedKey)){
            if(focusOnNewCommentField()){
                _bundle.putBoolean(FeedActivity.commentFieldSelectedKey, false);
            } else {
                System.out.println("comment field was null");
            }
        }
    }

    private boolean focusOnNewCommentField(){
        final EditText addCommentEditText = (EditText) findViewById(R.id.addCommentEditText);
        final ListView commentsList = (ListView) findViewById(R.id.listViewComments);

        if(addCommentEditText != null && commentsList != null){
            addCommentEditText.post(new Runnable() {
                @Override
                public void run() {
                    commentsList.setSelection(commentsList.getAdapter().getCount() - 1);
                    addCommentEditText.requestFocusFromTouch();
                    InputMethodManager lManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    lManager.showSoftInput(addCommentEditText, 0);
                }
            });
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();

        intent.putExtras(_bundle);
        setResult(RESULT_OK, intent);

        finish();
    }

    public void onButtonClickLike(View v) {
        if (v == null || v.getId() != R.id.frameLayoutLikeContainer) {
            return;
        }

        View subContainer = v.findViewById(R.id.likeSubContainer);
        PostContainer parentContainer = FeedPostAdapter.getParentPostContainer(v);
        parentContainer.getData().setLikedByUser(!parentContainer.getData().likedByUser());
        if (parentContainer.getData().likedByUser()) {
            parentContainer.getData().setNumLikes(parentContainer.getData().getNumLikes() + 1);
        } else {
            parentContainer.getData().setNumLikes(Math.max(0, parentContainer.getData().getNumLikes() - 1));
        }

        parentContainer.updateViews();

        subContainer.setAlpha(0);
        subContainer.animate().alpha(1);

        _bundle.putInt(PostData.numLikesKey, _data.getNumLikes());
        _bundle.putBoolean(PostData.likedByUserKey, _data.likedByUser());
    }

    public void onButtonClickPostBody(View v){
        v.setAlpha(0);
        v.animate().alpha(1);
        return;
    }

    public void onButtonClickComment(View v){
        View subContainer = v.findViewById(R.id.commentSubContainer);
        subContainer.setAlpha(0);
        subContainer.animate().alpha(1);
        focusOnNewCommentField();
    }

    public void onButtonClickPostComment(View v){
        v.setAlpha(0);
        v.animate().alpha(1);
        LinearLayout addCommentContainer = (LinearLayout) findViewById(R.id.addCommentContainer);
        EditText addCommentEditText = (EditText) addCommentContainer.findViewById(R.id.addCommentEditText);
        if(addCommentEditText.getText() == null || addCommentEditText.getText().length() <= 0){
            return;
        }
        ListView currentCommentListView = (ListView) findViewById(R.id.listViewComments);

        CommentAdapter adapter = (CommentAdapter) currentCommentListView.getAdapter();

        CommentData newComment = new CommentData(FeedActivity.currentUserDisplayName, addCommentEditText.getText().toString());
        adapter.getComments().add(newComment);
        adapter.notifyDataSetChanged();
        addCommentEditText.setText(null);

        _data.setComments(adapter.getComments());
        _bundle = _data.convertToBundle();
        PostContainer postParent = FeedPostAdapter.getParentPostContainer(v);
        postParent.updateViews();
    }
}
