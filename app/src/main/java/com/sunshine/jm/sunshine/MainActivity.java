package com.sunshine.jm.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        int id = menuItem.getItemId();

        if(R.id.action_settings == id){
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if ( R.id.action_map == id ){
            openPreferredMap();
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    private void openPreferredMap(){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPref.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_defualt));

        Uri mapLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(mapLocation);

        if( intent.resolveActivity(getPackageManager())!= null ){
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "There is no App for Localization");
        }


    }

}
