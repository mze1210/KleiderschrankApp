package com.example.gruppe1.kleiderschrankapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.activities.KleiderschrankAnlegenActivity;

import java.util.List;

public class KleiderschrankFragment extends ListFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    String[] countries = new String[] {
            "India",
            "Pakistan",
            "Sri Lanka",
            "China",
            "Bangladesh",
            "Nepal",
            "Afghanistan",
            "North Korea",
            "South Korea",
            "Japan"
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KleiderschrankFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static KleiderschrankFragment newInstance(int columnCount) {
        KleiderschrankFragment fragment = new KleiderschrankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Creating an array adapter to store the list of countries **/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, countries);

        /** Setting the list adapter for the ListFragment */
        setListAdapter(adapter);

        //Button hinzufügen
//        addButton.setOnClickListener(new View.OnClickListener() {
//            /**
//             * OnClickListener for the add button navigates to CreateContactAcitivy
//             * @param v clicked View
//             */
//            @Override
//            public void onClick(View v) {
//                Intent createIntent = new Intent(getActivity(), KleiderschrankAnlegenActivity.class);
//                startActivity(createIntent);
//            }
//        });

        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
