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


public class KlamotteFragment extends Fragment {

    public KlamotteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_klamotte, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
