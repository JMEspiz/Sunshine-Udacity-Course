package com.sunshine.jm.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if ( id == R.id.action_share){
            doShareWeather();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doShareWeather(){

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {

        private static final String SUNSHIE_HASHTAG = "#SunshineApp";
        private String shareText;

        public DetailFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();

            //Check intent is not null and has Extra.
            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
                shareText = intent.getStringExtra(Intent.EXTRA_TEXT);
                TextView detail = (TextView) rootView.findViewById(R.id.detail_text);
                detail.setText(shareText);
            }
            return rootView;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_detail, menu);

            MenuItem menuItem = menu.findItem(R.id.action_share);

            ShareActionProvider shareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

            if ( shareAction != null) {
                shareAction.setShareIntent(intentWeatherShare());
            }
        }

        private Intent intentWeatherShare(){
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intentShare.setType("text/plain");
            intentShare.putExtra(Intent.EXTRA_TEXT,
                    shareText + " " + SUNSHIE_HASHTAG );
            return intentShare;
        }
    }
}
