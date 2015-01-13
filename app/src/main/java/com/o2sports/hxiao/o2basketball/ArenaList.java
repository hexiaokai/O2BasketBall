package com.o2sports.hxiao.o2basketball;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class ArenaList extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener{

    private ListView arenaListView;

    private static final String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arena_list);


        arenaListView = (ListView) findViewById(R.id.arenaListView);
        arenaListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, strs));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arena_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)
        {
            switch (buttonView.getId())
            {
                case R.id.radio_button0:
                    // switch page
                    break;
                case R.id.radio_button1:
                    break;
                case R.id.radio_button2:
                    break;
            }
        }
    }
}
