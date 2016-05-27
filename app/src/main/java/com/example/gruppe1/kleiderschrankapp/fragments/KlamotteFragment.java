package com.example.gruppe1.kleiderschrankapp.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.dao.DBHelper;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema;


public class KlamotteFragment extends Fragment {

    private CustomAdapter adapter;
    private ListView klamotteListView;
    private Cursor klamotteCursor;

    public KlamotteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_klamotte, container, false);
        initView(view);
        return view;
    }

    public void initView(View view){
        klamotteCursor = DBHelper.getInstance(getContext()).findAllKlamotteWithKleiderschrankKategorie();
        adapter = new CustomAdapter(getContext(), klamotteCursor);
        klamotteListView = (ListView) view.findViewById(R.id.klamotteListView);
        klamotteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * OnItemClickListener for item in ListView
             * @param parent where the click happened
             * @param view clicked View
             * @param position clicked position
             * @param id row id of the clicked item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"ToDo", Toast.LENGTH_SHORT).show();
            }
        });
        klamotteListView.setAdapter(adapter);
    }
}
