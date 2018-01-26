package com.example.amarildo.masterchef.Steps;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amarildo.masterchef.BaseStepFragment;
import com.example.amarildo.masterchef.R;


public class DataDiArrivoStep extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    int nrTotale = 0;

    public DataDiArrivoStep() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DataDiArrivoStep newInstance(int param1) {
        DataDiArrivoStep fragment = new DataDiArrivoStep();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fifth, container, false);
        handleViewTreeObserver(view);
        return view;
    }

    @Override
    public int getPageNr() {
        return 5;
    }

    @Override
    public boolean validateStep() {
        return false;
    }

    @Override
    public void updateGui() {

    }
}
