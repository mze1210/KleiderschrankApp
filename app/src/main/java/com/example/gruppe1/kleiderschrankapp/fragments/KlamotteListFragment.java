package com.example.gruppe1.kleiderschrankapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.model.Kategorie;
import com.example.gruppe1.kleiderschrankapp.model.Klamotte;
import com.example.gruppe1.kleiderschrankapp.model.Kleiderschrank;
import com.google.android.gms.plus.PlusOneButton;


public class KlamotteListFragment extends ListFragment{

    public KlamotteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Klamotte k1 = new Klamotte();
        Klamotte k2 = new Klamotte();
        Klamotte k3 = new Klamotte();
        Klamotte k4 = new Klamotte();
        Klamotte k5 = new Klamotte();
        Klamotte k6 = new Klamotte();

        k1.setBezeichnung("Klamotte 1");
        k2.setBezeichnung("Klamotte 2");
        k3.setBezeichnung("Klamotte 3");
        k4.setBezeichnung("Klamotte 4");
        k5.setBezeichnung("Klamotte 5");
        k6.setBezeichnung("Klamotte 6");

        Kategorie kat1 = new Kategorie();
        kat1.setBezeichnung("Hose");

        Kategorie kat2 = new Kategorie();
        kat2.setBezeichnung("Jacke");

        k1.setKategorie(kat1);
        k2.setKategorie(kat2);
        k3.setKategorie(kat1);
        k4.setKategorie(kat2);
        k5.setKategorie(kat1);
        k6.setKategorie(kat2);

        Klamotte[] values = new Klamotte[] {k1, k2, k3, k4, k5, k6};


        ArrayAdapter<Klamotte> adapter = new ArrayAdapter<Klamotte>(getActivity(), android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


    }

}
