package com.example.amarildo.masterchef;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


public class ViewPagerFragment extends Fragment {

    private com.example.amarildo.masterchef.NonSwipeableViewPager viewPager;
    MyViewPagerAdapter adapterViewPager;
    Button next_Button;
    Button back_Button;
    ProgressBar progress_Bar;
    LinearLayout buttons_LinearLayout;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public void hideButtons(){
        buttons_LinearLayout.setVisibility(View.INVISIBLE);
    }

    public ViewPagerFragment() {
        // Required empty public constructor
    }


    public static ViewPagerFragment newInstance(String param1, String param2) {

        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_pager, container, false);



        buttons_LinearLayout = v.findViewById(R.id.buttonsLinearLayout);
        progress_Bar = v.findViewById(R.id.progressBar);

        viewPager = v.findViewById(R.id.pager);
        // We can declar viewPager ViewPager and cast it to com.example.amarildo.masterchef.NonSwipeableViewPager to use the overrided class methods
        //com.example.amarildo.masterchef.NonSwipeableViewPager prove = (com.example.amarildo.masterchef.NonSwipeableViewPager) viewPager;
        //viewPager.setCurrentItem(Integer.MAX_VALUE / 2);

        adapterViewPager = new MyViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), viewPager);
        //viewPager.setPagingEnabled(true);

        //com.example.amarildo.masterchef.NonSwipeableViewPager prove = new com.example.amarildo.masterchef.NonSwipeableViewPager(getContext());
        //    prove = (com.example.amarildo.masterchef.NonSwipeableViewPager) viewPager;





        back_Button = v.findViewById(R.id.backButton);
        next_Button = v.findViewById(R.id.nextButton);


        next_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewPager.getCurrentItem() + 1;
                viewPager.setCurrentItem(pos);
                //tabLayout.addTab(tabLayout.newTab().setText("Yeah"));
                progress_Bar.setProgress(pos);
            }
        });

        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewPager.getCurrentItem() - 1;
                viewPager.setCurrentItem(pos);
                progress_Bar.setProgress(pos);

            }
        });


        return v;
    }

}
