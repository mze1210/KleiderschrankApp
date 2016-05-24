package com.example.gruppe1.kleiderschrankapp.fragments;


import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.activities.KleiderschrankAnlegenActivity;
import com.example.gruppe1.kleiderschrankapp.dao.DatabaseSchema;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;

/**
 * A simple {@link Fragment} subclass.
 */
public class KleiderschrankListFragment extends ListFragment {

    private SimpleCursorAdapter adapter;
    private ListView kleiderschrankList;

    public KleiderschrankListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        String[] fromColumns = {DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_BEZEICHNUNG, DatabaseSchema.KleiderschrankEntry.COLUMN_NAME_ID};
//        int[] toViews = {android.R.id.text1, android.R.id.text2};
//        adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_2, null, fromColumns, toViews, 0);
//        kleiderschrankList = (ListView) this.getActivity().findViewById(R.id.kleiderschrankList);

        //TODO
//        kleiderschrankList.setAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Kleiderschrank k1 = new Kleiderschrank();
        Kleiderschrank k2 = new Kleiderschrank();
        Kleiderschrank k3 = new Kleiderschrank();
        Kleiderschrank k4 = new Kleiderschrank();
        Kleiderschrank k5 = new Kleiderschrank();
        Kleiderschrank k6 = new Kleiderschrank();

        k1.setBezeichnung("Kleiderschrank 1");
        k2.setBezeichnung("Kleiderschrank 2");
        k3.setBezeichnung("Kleiderschrank 3");
        k4.setBezeichnung("Kleiderschrank 4");
        k5.setBezeichnung("Kleiderschrank 5");
        k6.setBezeichnung("Kleiderschrank 6");

        Kleiderschrank[] values = new Kleiderschrank[] {k1, k2, k3, k4, k5, k6};


        ArrayAdapter<Kleiderschrank> adapter = new ArrayAdapter<Kleiderschrank>(getActivity(), android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
//    }

}
