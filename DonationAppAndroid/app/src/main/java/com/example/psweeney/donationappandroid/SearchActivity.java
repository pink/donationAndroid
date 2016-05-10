package com.example.psweeney.donationappandroid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.charity.CharityDetailContainer;
import com.example.psweeney.donationappandroid.charity.CharityDetailData;
import com.example.psweeney.donationappandroid.charity.CharityDetailFactory;
import com.example.psweeney.donationappandroid.charity.CharityDiscoverMiniAdapter;

public class SearchActivity extends AppCompatActivity {
    public enum SuggestionSortType{
        DISTANCE, RATING, NAME
    }

    private CharityDetailData _currentRecipient = null;
    private SuggestionSortType _currentSort = SuggestionSortType.RATING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        _currentRecipient = CharityDetailFactory.getUserAutoDonateCharity();
        setCurrentRecipientViews();
        setSuggestionListViews();
    }

    public static void setCharityMiniView(CharityDetailContainer dataContainer, Resources resources, CharityDetailData data){
        ImageView recipientIcon = (ImageView) dataContainer.findViewById(R.id.imageViewRecipientIcon);
        TextView recipientName = (TextView) dataContainer.findViewById(R.id.textViewRecipientName);
        LinearLayout ratingContainer = (LinearLayout) dataContainer.findViewById(R.id.recipientRatingContainer);
        TextView recipientDistance = (TextView) dataContainer.findViewById(R.id.textViewRecipientDistance);

        if(data != null){
            dataContainer.setData(data);
        }

        if(dataContainer.getData() == null){
            return;
        }

        CharityDetailData storedData = dataContainer.getData();

        recipientIcon.setImageDrawable(resources.getDrawable(storedData.getIconId()));
        recipientName.setText(storedData.getDisplayName());
        CharityDetailActivity.applyRatingToStarList(ratingContainer, resources, storedData.getRating());
        recipientDistance.setText(storedData.getDisplayDistance());
    }

    private void setCurrentRecipientViews(){
        ViewGroup currentRecipientParent = (ViewGroup) findViewById(R.id.currentRecipientContainer);
        if(_currentRecipient == null){
            currentRecipientParent.setVisibility(View.GONE);
            return;
        } else {
            currentRecipientParent.setVisibility(View.VISIBLE);
        }

        CharityDetailContainer currentRecipientContainer = (CharityDetailContainer) currentRecipientParent.findViewById(R.id.recipientDataContainer);

        if(currentRecipientContainer == null){
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewGroup inflatedContainer = (ViewGroup) inflater.inflate(R.layout.charity_mini_view, currentRecipientParent, true);
            currentRecipientContainer = (CharityDetailContainer) inflatedContainer.findViewById(R.id.recipientDataContainer);
        }

        if(currentRecipientContainer == null){
            return;
        }

        setCharityMiniView(currentRecipientContainer, getResources(), _currentRecipient);
    }

    private void setSuggestionListViews(){
        ListView suggestionList = (ListView) findViewById(R.id.listViewSuggestedRecipients);

        TextView ratingSortLabel = (TextView) findViewById(R.id.textViewRatingSortLabel);
        TextView distanceSortLabel = (TextView) findViewById(R.id.textViewDistanceSortLabel);
        TextView nameSortLabel = (TextView) findViewById(R.id.textViewNameSortLabel);

        switch (_currentSort){
            case RATING:
                ratingSortLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                ratingSortLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                distanceSortLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                distanceSortLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                nameSortLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                nameSortLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case DISTANCE:
                distanceSortLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                distanceSortLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                ratingSortLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                ratingSortLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                nameSortLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                nameSortLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case NAME:
                nameSortLabel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nameSortLabel.setTextColor(getResources().getColor(R.color.colorAccent));
                distanceSortLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                distanceSortLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                ratingSortLabel.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                ratingSortLabel.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }

        CharityDiscoverMiniAdapter adapter = new CharityDiscoverMiniAdapter(this, R.layout.charity_mini_view, _currentSort);
        suggestionList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        suggestionList.invalidate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        _currentRecipient = CharityDetailFactory.getUserAutoDonateCharity();
        setCurrentRecipientViews();
        setSuggestionListViews();
    }

    public void onButtonClickRatingSort(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSort != SuggestionSortType.RATING){
            _currentSort = SuggestionSortType.RATING;
            setSuggestionListViews();
        }
    }

    public void onButtonClickDistanceSort(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSort != SuggestionSortType.DISTANCE){
            _currentSort = SuggestionSortType.DISTANCE;
            setSuggestionListViews();
        }
    }

    public void onButtonClickNameSort(View v){
        Animation.defaultButtonAnimation(v);
        if(_currentSort != SuggestionSortType.NAME){
            _currentSort = SuggestionSortType.NAME;
            setSuggestionListViews();
        }
    }

    public void onClickCharityMiniView(View v){
        Animation.defaultButtonAnimation(v);

        CharityDetailContainer detailContainer = (CharityDetailContainer) v;
        if(detailContainer == null || detailContainer.getData() == null){
            return;
        }
        CharityDetailData data = detailContainer.getData();

        Intent intent = new Intent(getApplicationContext(), CharityDetailActivity.class);
        Bundle bundle = new Bundle();
        if(bundle == null){
            return;
        }

        bundle.putInt(CharityDetailData.charityIdentifierKey, data.getIdentifier());
        intent.putExtras(bundle);

        startActivityForResult(intent, 1);
    }
}
