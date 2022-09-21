package com.kood.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public  WordAdapter (Context context , ArrayList<Word> words , int backgroundColorResID){
        super(context,0,words);
        mColorResourceId = backgroundColorResID;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.words_list_item, parent, false);
        }

        // Get the object located at this position in the list
        final Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokWordTV = listItemView.findViewById(R.id.miwok_number_tv);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokWordTV.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView englishWordTV = listItemView.findViewById(R.id.en_number_tv);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        englishWordTV.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        // Check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            iconView.setImageResource(currentWord.getImgResId());
             //Make sure the view is visible
            iconView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            iconView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textViewsLayout = listItemView.findViewById(R.id.textViews_linearLayout);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textViewsLayout.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
