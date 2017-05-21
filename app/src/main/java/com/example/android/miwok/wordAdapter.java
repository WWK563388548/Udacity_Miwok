package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wwk on 17/4/11.
 */

public class wordAdapter extends ArrayAdapter<Word> {

    // Resource ID for the background color for this list of words
    private int mColorResourceId;


    /**
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created
     * @param Words is the list of {@link Word} to be displayed
     * @param colorResourceId is the resource ID for the background color for this list of words
     */


    public wordAdapter(Activity context, ArrayList<Word> Words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, Words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located this position in the list
        Word currentWords = getItem(position);

        ImageView displayImage = (ImageView) listItemView.findViewById(R.id.image_view);

        // Check if an image is provided for this word or not
        if (currentWords.hasImage()) {

            // If an image is available, display the provided image based on the resource ID
            displayImage.setImageResource(currentWords.getmImageResourceId());
            // Make sure the view is visible
            displayImage.setVisibility(View.VISIBLE);

        }else {
            // Otherwise hide the ImageView (set visibility to GONE)
            displayImage.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);


        // Find the TextView in the list_item.xml layout with the ID japanese_text_view.
        TextView japaneseTextView = (TextView) listItemView.findViewById(R.id.japanese_text_view);
        // Get the Japanese translation from the currentWords object and set this text
        // on the TextView
        japaneseTextView.setText(currentWords.getmJapaneseTranslation());


        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWords.getmDefaultTranslation());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;

    }
}
