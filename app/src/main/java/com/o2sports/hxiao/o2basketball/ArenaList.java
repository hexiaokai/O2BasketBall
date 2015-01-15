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

    private ListView arenaListView;
    private MobileServiceClient mClient;
    private MobileServiceTable<Player> mPlayer;
    private List<Player> playerList;
    private Button refreshButton;

    private PlayerListAdapter mAdapter;

    private static final String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arena_list);

        mAdapter = new PlayerListAdapter(this, R.id.arena_list_view);

        arenaListView = (ListView) findViewById(R.id.arena_list_view);
        arenaListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, strs));
        //arenaListView.setAdapter(mAdapter);

        refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(this);


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

    public void onClick(View view) {
        arenaListView.setAdapter(mAdapter);
        if (view == refreshButton) {
            try {
                mClient = new MobileServiceClient(
                        "https://o2service.azure-mobile.net/",
                        "WOOlrIveEONQVxwUzgDGjXDOtPVSxs92",
                        this
                );
                mPlayer = mClient.getTable(Player.class);
            }
            catch (Exception e)
            {

            }

            playerList = new ArrayList<Player>();
            final ArrayList<String> playerNames = new ArrayList<String>();

            /* Write Table Test Pass
            Player newPlayer = new Player();
            newPlayer.ID = "123";
            newPlayer.Name = "456";
            newPlayer.Position = 3;
            newPlayer.Capacity = 70;
            mClient.getTable(Player.class).insert(newPlayer, new TableOperationCallback<Player>() {
                public void onCompleted(Player entity, Exception exception, ServiceFilterResponse response) {
                    //
                }
            });
            */

            //Log.i("Tag", mClient.getTable(Player.class).where().field("Name").eq("1").getQueryText());

            mPlayer.execute(new TableQueryCallback<Player>() {
                @Override
                public void onCompleted(List<Player> players, int i, Exception e, ServiceFilterResponse serviceFilterResponse) {
                    if (e == null) {
                        Log.i("Tag", "Total item count " + i);
                        mAdapter.clear();
                        if (!players.isEmpty()) {
                            for (Player p : players) {
                                Log.i("Tag", "Read object with ID " + p.id + p.name + p.capability + p.position);
                                //playerList.add(p);
                                mAdapter.add(p);
                            }
                        } else {
                            Log.i("Tag", "No Player");
                        }
                    } else {
                        createAndShowDialog(e.toString(), "Error");
                    }
                }
            });


            //arenaListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, playerNames));
            //arenaListView.refreshDrawableState();
        }
    }

    private void createAndShowDialog(String message, String title)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }
}
