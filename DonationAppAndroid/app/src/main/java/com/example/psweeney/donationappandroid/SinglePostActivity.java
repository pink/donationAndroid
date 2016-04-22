package com.example.psweeney.donationappandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.psweeney.donationappandroid.charity.CharityDetailData;
import com.example.psweeney.donationappandroid.charity.CharityDetailFactory;
import com.example.psweeney.donationappandroid.feed.CommentAdapter;
import com.example.psweeney.donationappandroid.feed.CommentData;
import com.example.psweeney.donationappandroid.feed.FeedPostAdapter;
import com.example.psweeney.donationappandroid.feed.PostContainer;
import com.example.psweeney.donationappandroid.feed.PostData;
import com.example.psweeney.donationappandroid.feed.PostFactory;

import java.util.List;

public class SinglePostActivity extends AppCompatActivity {
    PostData _data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            finish();
            return;
        }

        try{
            _data = PostFactory.getPostById(bundle.getInt(PostData.postIdentifierKey));
        } catch (NullPointerException e){
            finish();
            return;
        }

        if(_data == null){
            finish();
            return;
        }

        final ListView listViewComments = (ListView) findViewById(R.id.listViewComments);

        listViewComments.setDivider(null);
        listViewComments.setDividerHeight(0);
        List<CommentData> commentDataList = _data.getComments();
        final CommentAdapter commentAdapter = new CommentAdapter(this, R.layout.feed_comment, _data);
        listViewComments.setAdapter(commentAdapter);

        if(bundle.getBoolean(FeedActivity.commentFieldSelectedKey)){
            focusOnNewCommentField();
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

        setResult(RESULT_OK, intent);

        finish();
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

        bundle.putInt(CharityDetailData.charityIdentifierKey, charityDetailData.getIdentifier());
        intent.putExtras(bundle);

        startActivity(intent);
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

        Animation.defaultButtonAnimation(subContainer);
    }

    public void onButtonClickPostBody(View v){
        Animation.defaultButtonAnimation(v);
    }

    public void onButtonClickComment(View v){
        View subContainer = v.findViewById(R.id.commentSubContainer);
        Animation.defaultButtonAnimation(subContainer);
        focusOnNewCommentField();
    }

    public void onButtonClickPostComment(View v){
        Animation.defaultButtonAnimation(v);
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
        PostContainer postParent = FeedPostAdapter.getParentPostContainer(v);
        postParent.updateViews();
    }
}
