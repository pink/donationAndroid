package com.example.psweeney.donationappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.feed.CommentAdapter;
import com.example.psweeney.donationappandroid.feed.CommentData;
import com.example.psweeney.donationappandroid.feed.DonationPostData;
import com.example.psweeney.donationappandroid.feed.FeedPostAdapter;
import com.example.psweeney.donationappandroid.feed.PostContainer;
import com.example.psweeney.donationappandroid.feed.PostData;

import java.util.List;
import java.util.zip.Inflater;

public class SingleDonationPostActivity extends AppCompatActivity {
    DonationPostData _data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_donation_post);

        Bundle bundle = getIntent().getExtras();
        _data = (DonationPostData) FeedPostAdapter.extractPostDataFromBundle(bundle);
        PostContainer activityParent = (PostContainer) findViewById(R.id.parentContainer);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        PostContainer postParent = (PostContainer) inflater.inflate(R.layout.feed_post_donation, null);
        View directChild = postParent.findViewById(R.id.parentDirectChild);
        postParent.removeView(directChild);
        activityParent.addView(directChild);

        ImageView authorIcon = (ImageView) directChild.findViewById(R.id.imageViewAuthorIcon);
        TextView postTitle = (TextView) directChild.findViewById(R.id.textViewTitleLine);
        TextView postDate = (TextView) directChild.findViewById(R.id.textViewDateLine);

        authorIcon.setImageDrawable(_data.getAuthorIcon());
        postTitle.setText(_data.getTitleDisplayString());
        System.out.println(postTitle.getText().toString());
        postDate.setText(_data.getDateDisplayString());

        ImageView likeIconDisabled = (ImageView) directChild.findViewById(R.id.imageViewLikeIconDisabled);
        ImageView likeIconEnabled = (ImageView) directChild.findViewById(R.id.imageViewLikeIconEnabled);
        TextView likeNum = (TextView) directChild.findViewById(R.id.textViewLikeNum);

        if(_data.likedByUser()){
            likeIconDisabled.setVisibility(View.GONE);
            likeIconEnabled.setVisibility(View.VISIBLE);
        } else {
            likeIconDisabled.setVisibility(View.VISIBLE);
            likeIconEnabled.setVisibility(View.GONE);
        }

        likeNum.setText(Integer.toString(_data.getNumLikes()));

        TextView commentNum = (TextView) directChild.findViewById(R.id.textViewCommentNum);

        commentNum.setText(Integer.toString(_data.getComments().size()));

        final ListView listViewComments = (ListView) findViewById(R.id.listViewComments);
        List<CommentData> commentDataList = _data.getComments();
        final CommentAdapter commentAdapter = new CommentAdapter(this, R.layout.feed_comment, commentDataList);
        listViewComments.setAdapter(commentAdapter);
    }
}
