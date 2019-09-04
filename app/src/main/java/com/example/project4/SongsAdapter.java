package com.example.project4;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class SongsAdapter extends ArrayAdapter<Songs> {

    private int mColor;

    public SongsAdapter(Activity context, ArrayList<Songs> text, int color) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, text);
        mColor = color;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return custom layout view
     * Override the method to attach it to our layout and set the list to the TextView and ImageView
     * that we will add later.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }
        Songs currentSong = getItem(position);

        TextView songName = listItemView.findViewById(R.id.song_name);
        songName.setText(currentSong.getSongName());
        songName.setBackgroundColor(mColor);

        TextView artistName = listItemView.findViewById(R.id.artist_name);
        artistName.setText(currentSong.getArtistName());
        artistName.setBackgroundColor(mColor);

        TextView remixedBy = listItemView.findViewById(R.id.remixed_by);
        remixedBy.setText(currentSong.getRemixBy());
        remixedBy.setBackgroundColor(mColor);

        ImageView albumArt = listItemView.findViewById(R.id.album_art);
        albumArt.setImageResource(currentSong.getImageAsset());


        View textContainer = listItemView.findViewById(R.id.text_color_container);
        int color = ContextCompat.getColor(getContext(), mColor);
        textContainer.setBackgroundColor(color);

        return listItemView;

    }
}


