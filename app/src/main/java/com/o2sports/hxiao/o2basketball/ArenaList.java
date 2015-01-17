package com.o2sports.hxiao.o2basketball;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.microsoft.windowsazure.mobileservices.*;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;


public class ArenaList extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{

    public MobileServiceClient mClient;

    private ListView arenaListView;

    private MobileServiceTable<Arena> mArenaTable;
    private List<Arena> arenaList;
    private ArenaListAdapter mArenaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena_list);

        arenaListView = (ListView) findViewById(R.id.arena_list_view);

        try {
            mClient = new MobileServiceClient(
                    "https://o2service.azure-mobile.net/",
                    "qJNqJihCYMDTfwYsbHbfURxaOfUNwh32",
                    this
            );
        }
        catch (Exception e)
        {

        }

        mArenaAdapter = new ArenaListAdapter(this, R.id.arena_list_view);
        arenaListView.setAdapter(mArenaAdapter);

        refreshArenaList();


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
                //
            }
        }
    }

    public void refreshArenaList()
    {
        mArenaTable = mClient.getTable(Arena.class);

        mArenaTable.execute(new TableQueryCallback<Arena>() {
            @Override
            public void onCompleted(List<Arena> arenas, int i, Exception e, ServiceFilterResponse serviceFilterResponse) {
                if (e == null) {
                    Log.i("Tag", "Total item count " + i);
                    mArenaAdapter.clear();
                    if (!arenas.isEmpty()) {
                        for (Arena arena : arenas) {
                            Log.i("Tag", "Read object with ID " + arena.id + arena.name);
                            mArenaAdapter.add(arena);
                        }
                    } else {
                        Log.i("Tag", "No Arena");
                    }
                } else {
                    Log.e("Tag", e.toString());
                    createAndShowDialog(e.toString(), "Error");
                }
            }
        });
    }

    public void onClick(View view) {
        Arena sArena = (Arena) view.getTag();
        Intent mIntent = new Intent(ArenaList.this, ArenaDetail.class);
        mIntent.putExtra("ArenaID", sArena.id);
        startActivity(mIntent);
    }

    private void createAndShowDialog(String message, String title)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }
}
