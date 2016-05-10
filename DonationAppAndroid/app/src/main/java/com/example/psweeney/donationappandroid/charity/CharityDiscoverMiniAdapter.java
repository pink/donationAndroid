package com.example.psweeney.donationappandroid.charity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.psweeney.donationappandroid.R;
import com.example.psweeney.donationappandroid.SearchActivity;
import com.example.psweeney.donationappandroid.feed.PostContainer;
import com.example.psweeney.donationappandroid.feed.PostData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by psweeney on 5/10/16.
 */
public class CharityDiscoverMiniAdapter extends ArrayAdapter{
    public CharityDiscoverMiniAdapter(Context context, int resource, SearchActivity.SuggestionSortType sortType) {
        super(context, resource, CharityDetailFactory.getSortedListOfDiscoverableCharities(CharityDetailFactory.getComparatorForSuggestionSortType(sortType)));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        CharityDetailData data = (CharityDetailData) getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewGroup itemParent = (ViewGroup) inflater.inflate(R.layout.charity_mini_view, parent, false);
        CharityDetailContainer dataContainer = (CharityDetailContainer) itemParent.findViewById(R.id.recipientDataContainer);

        SearchActivity.setCharityMiniView(dataContainer, getContext().getResources(), data);
        return  itemParent;
    }
}
