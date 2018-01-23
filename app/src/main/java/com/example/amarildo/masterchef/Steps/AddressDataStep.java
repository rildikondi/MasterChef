package com.example.amarildo.masterchef.Steps;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
;
import com.example.amarildo.masterchef.BaseStepFragment;
import com.example.amarildo.masterchef.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddressDataStep extends BaseStepFragment {

    private static final String ARG_PARAM1 = "param1";

    private int mParam1;
    View view;
    Switch switch_Button;

    TextView last_tv;
    LinearLayout fattComm_LinearLayout;
    ExpandableListView nuovoIn_expandable_ListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    ScrollView fourth_ScrollView;

    public HashMap<String, List<String>> getData() {

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> indirizzi = new ArrayList<String>();
        indirizzi.add("NUOVO INDIRIZZO");
        indirizzi.add("VIA MILANO");

        expandableListDetail.put("SELEZIONA UN INDIRIZZO", indirizzi);

        return expandableListDetail;
    }


    public AddressDataStep() {
        // Required empty public constructor
    }


    public static AddressDataStep newInstance(int param1) {

        AddressDataStep fragment = new AddressDataStep();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fourth, container, false);
        fattComm_LinearLayout = view.findViewById(R.id.fattCommLinearLayout);
        fourth_ScrollView = view.findViewById(R.id.fourthScrollView);

        handleViewTreeObserver(view);

        /*if(getArguments().get("nrPage") != null)
            Log.i("nrPage","nrPage" + getArguments().get("nrPage"));*/


        //final RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.fourthLinearLayout);
        //layout.addView(, index);

        LayoutInflater layoutInflater =
                (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(R.layout.row, null);

        last_tv = view.findViewById(R.id.lastTv);

        final View ResultText = addView;


        /*RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.BELOW, R.id.switchButton);
        ResultText.setLayoutParams(p);
        final View relativeLayout = view.findViewById(R.id.middleRL);
        ((RelativeLayout) relativeLayout).addView(ResultText);
        ((RelativeLayout) relativeLayout).removeView(ResultText);*/


        switch_Button = view.findViewById(R.id.switchButton);
        switch_Button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    expand(fattComm_LinearLayout);

                } else {
                    collapse(fattComm_LinearLayout);
                }
            }
        });


        nuovoIn_expandable_ListView = (ExpandableListView) view.findViewById(R.id.nuovoInExpandableListView);
        expandableListDetail = getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        //expandableListAdapter = new CustomExpandableListAdapter(this.getContext(), expandableListTitle, expandableListDetail, "address");
        //nuovoIn_expandable_ListView.setAdapter(expandableListAdapter);
        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), linearfour);

        return view;
    }

    @Override
    public int getPageNr() {
        return 3;
    }

    @Override
    public boolean validateStep() {
        return true;
    }


    @Override
    public void updateGui() {

    }

}