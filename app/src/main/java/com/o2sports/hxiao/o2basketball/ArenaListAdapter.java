package com.o2sports.hxiao.o2basketball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

/**
 * Created by Xiaokai He on 1/14/2015.
 */
public class ArenaListAdapter extends ArrayAdapter<Arena> {

    private Context mContext;

    public ArenaListAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final Arena currentArena = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.arena_list_view, parent, false);
        }

        row.setTag(currentArena);

        final Button arenaButton = (Button) row.findViewById(R.id.arenaButton);
        arenaButton.setText(currentArena.name);
        arenaButton.setTag(currentArena);
        arenaButton.setEnabled(true);
        arenaButton.setOnClickListener((View.OnClickListener)mContext);


        return row;
    }
}
