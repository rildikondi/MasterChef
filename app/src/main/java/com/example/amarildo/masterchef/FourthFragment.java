package com.example.amarildo.masterchef;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FourthFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private int mParam1;
    View view;
    Switch switch_Button;

    TextView last_tv;

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


    public FourthFragment() {
        // Required empty public constructor
    }


    public static FourthFragment newInstance(int param1) {

        FourthFragment fragment = new FourthFragment();
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
        view = inflater.inflate(R.layout.fragment_fourth, container, false);

        fourth_ScrollView = view.findViewById(R.id.fourthScrollView);



        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                view.getWindowVisibleDisplayFrame(r);

                int heightDiff = view.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 500) { // if more than 100 pixels, its probably a keyboard...


                    view.setPadding(0, 0, 0, 360);

                } else {
                    view.setPadding(0, 0, 0, 0);
                }
            }
        });


        //final RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.fourthLinearLayout);
        //layout.addView(, index);

        LayoutInflater layoutInflater =
                (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(R.layout.row, null);

        last_tv = view.findViewById(R.id.lastTv);

        final View ResultText = addView;


        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.BELOW, R.id.switchButton);
        ResultText.setLayoutParams(p);

        final View relativeLayout = view.findViewById(R.id.middleRL);



        switch_Button = view.findViewById(R.id.switchButton);
        switch_Button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    ((RelativeLayout) relativeLayout).addView(ResultText);

                } else {

                    ((RelativeLayout) relativeLayout).removeView(ResultText);

                }

            }
        });


        nuovoIn_expandable_ListView = (ExpandableListView) view.findViewById(R.id.nuovoInExpandableListView);
        expandableListDetail = getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this.getContext(), expandableListTitle, expandableListDetail, "address");
        nuovoIn_expandable_ListView.setAdapter(expandableListAdapter);


        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), linearfour);

        return view;
    }

}