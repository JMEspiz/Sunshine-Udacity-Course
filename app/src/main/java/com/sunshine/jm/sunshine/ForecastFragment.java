package com.sunshine.jm.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForecastFragment extends Fragment {

    private ArrayAdapter<String> mAdapter;

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Static Fake Data
        String[] data = {
                "Mon 6/23 - Sunny - 31/17",
                "Tue 6/24 - Foggy - 21/8",
                "Wed 6/25 - Cloudy - 22/17",
                "Thurs 6/26 - Rainy - 18/11",
                "Fri 6/27 - Foggy - 21/10",
                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
                "Sun 6/29 - Sunny - 20/7"
        };


        List<String> fakeData = new ArrayList<String>(Arrays.asList(data));

        //Adapter
        mAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.item_list_forecast,
                        R.id.item_forecast,
                        fakeData);

        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);

        //set Adapter
        ListView listview = (ListView) rootView.findViewById(R.id.fc_listview);
        listview.setAdapter(mAdapter);

        return rootView;
    }
}
