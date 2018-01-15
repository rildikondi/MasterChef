package com.example.amarildo.masterchef;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class FirstFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    long idPositionType = -1;
    int temp;
    View view;

    LinearLayout firstItems_LinearLayout;
    LinearLayout secondItems_LinearLayout;
    LinearLayout thirdItems_LinearLayout;

    RelativeLayout header_RelativeLayout;
    RelativeLayout header2_RelativeLayout;
    RelativeLayout header3_RelativeLayout;

    ImageView arrow;
    ImageView arrow1;
    ImageView arrow2;

    View _lastColored = null;

    TextView expandedList2_Item1;
    TextView expandedList2_Item2;
    TextView expandedList2_Item3;
    TextView expandedList2_Item4;



    View.OnClickListener thirdGroupItemListener;


    public FirstFragment() {
        // Required empty public constructor
    }



    public static FirstFragment newInstance(int param1) {

        FirstFragment fragment = new FirstFragment();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_first, container, false);

        // arrow reference
        arrow = (ImageView) view.findViewById(R.id.imageGroupIndicator);
        arrow1 = (ImageView) view.findViewById(R.id.imageGroupIndicator1);
        arrow2 = (ImageView) view.findViewById(R.id.imageGroupIndicator2);


        header_RelativeLayout = view.findViewById(R.id.headerRelativeLayout);
        firstItems_LinearLayout = view.findViewById(R.id.firstItemsLinearLayout);


        handleHeaderOne();
        handleItems(firstItems_LinearLayout);


        header2_RelativeLayout = view.findViewById(R.id.header2RelativeLayout);
        secondItems_LinearLayout = view.findViewById(R.id.secondItemsLinearLayout);
        handleHeaderTwo();
        handleItems(secondItems_LinearLayout);

        header3_RelativeLayout = view.findViewById(R.id.header3RelativeLayout);
        thirdItems_LinearLayout = view.findViewById(R.id.thirdItemsLinearLayout);
        handleHeaderThree();
        handleItems(thirdItems_LinearLayout);
        //handleThirdItems();

        if(savedInstanceState != null && savedInstanceState.getInt("firstSelected") != 0){
            TextView t  = firstItems_LinearLayout.findViewById(savedInstanceState.getInt("firstSelected"));
            t.setTextColor(Color.GREEN);
            t.setTag("green");
            expand(firstItems_LinearLayout);
            arrow.animate().rotationBy(180);
        }else{

            expand(firstItems_LinearLayout);
            arrow.animate().rotationBy(180);
            TextView exampleSelected = (TextView) firstItems_LinearLayout.getChildAt(0);
            exampleSelected.setTextColor(Color.GREEN);
            exampleSelected.setTag("green");
        }

        if(savedInstanceState != null && savedInstanceState.getInt("secondSelected") != 0){

            TextView t1  = secondItems_LinearLayout.findViewById(savedInstanceState.getInt("secondSelected"));
            t1.setTextColor(Color.GREEN);
            t1.setTag("green");
        }

        if(savedInstanceState != null && savedInstanceState.getInt("thirdSelected") != 0){
            TextView t2  = thirdItems_LinearLayout.findViewById(savedInstanceState.getInt("thirdSelected"));
            t2.setTextColor(Color.GREEN);
            t2.setTag("green");
        }


        return view;
    }

    public static void expand(final View v) {

        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density)) * 2);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {

        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density)) * 2 );
        v.startAnimation(a);
    }

    public void handleHeaderOne(){

        header_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(arrow.getRotation() == 0 ) {

                    arrow.animate().rotationBy(180).setDuration(300);
                    expand(firstItems_LinearLayout);

                    if(arrow1.getRotation() == 180 ){

                        collapse(secondItems_LinearLayout);
                        arrow1.animate().rotationBy(-180).setDuration(300);
                    }
                    if(arrow2.getRotation() == 180 ){

                        collapse(thirdItems_LinearLayout);
                        arrow2.animate().rotationBy(-180).setDuration(300);
                    }

                }else if(arrow.getRotation() == 180){

                    arrow.animate().rotationBy(-180).setDuration(300);
                    collapse(firstItems_LinearLayout);
                }
            }
        });
    }

    public void handleHeaderTwo(){

        header2_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(arrow1.getRotation() == 0 ) {

                    arrow1.animate().rotationBy(180).setDuration(300);
                    expand(secondItems_LinearLayout);

                    if(arrow.getRotation() == 180 ){

                        collapse(firstItems_LinearLayout);
                        arrow.animate().rotationBy(-180).setDuration(300);
                    }
                    if(arrow2.getRotation() == 180 ){

                        collapse(thirdItems_LinearLayout);
                        arrow2.animate().rotationBy(-180).setDuration(300);
                    }

                }else if(arrow1.getRotation() == 180){

                    arrow1.animate().rotationBy(-180).setDuration(300);
                    collapse(secondItems_LinearLayout);
                }
            }
        });

    }

    public void handleHeaderThree(){

        header3_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(arrow2.getRotation() == 0 ) {

                    arrow2.animate().rotationBy(180).setDuration(300);
                    expand(thirdItems_LinearLayout);

                    if(arrow.getRotation() == 180 ){

                        collapse(firstItems_LinearLayout);
                        arrow.animate().rotationBy(-180).setDuration(300);
                    }
                    if(arrow1.getRotation() == 180 ){

                        collapse(secondItems_LinearLayout);
                        arrow1.animate().rotationBy(-180).setDuration(300);
                    }
                }
                else if(arrow2.getRotation() == 180){

                    arrow2.animate().rotationBy(-180).setDuration(300);
                    collapse(thirdItems_LinearLayout);
                }
            }
        });
    }

    public void handleThirdItems(){

        // items of third Group
        expandedList2_Item1 = view.findViewById(R.id.expandedList2Item1);
        expandedList2_Item2 = view.findViewById(R.id.expandedList2Item2);
        expandedList2_Item3 = view.findViewById(R.id.expandedList2Item3);
        expandedList2_Item4 = view.findViewById(R.id.expandedList2Item4);

        thirdGroupItemListener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(view.findViewWithTag("green") != null){

                    TextView tochange = (TextView) view.findViewWithTag("green");
                    tochange.setTag("");
                    tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                }

                TextView mytext = (TextView) v;
                mytext.setTag("green");
                mytext.setTextColor(Color.GREEN);
            }
        };

        expandedList2_Item1.setOnClickListener(thirdGroupItemListener);
        expandedList2_Item2.setOnClickListener(thirdGroupItemListener);
        expandedList2_Item3.setOnClickListener(thirdGroupItemListener);
        expandedList2_Item4.setOnClickListener(thirdGroupItemListener);


    }



    public void handleItems(final LinearLayout firstItemsLn){

        View.OnClickListener secondItemsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView controlTextView = (TextView) v;
                String contenti = (String) controlTextView.getText();
                if (contenti == "RESET") {
                    if (firstItemsLn.findViewWithTag("green") != null) {

                        TextView tochange = (TextView) firstItemsLn.findViewWithTag("green");
                        tochange.setTag("");
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                    }




                } else {


                    if (firstItemsLn.findViewWithTag("green") != null) {

                        TextView tochange = (TextView) firstItemsLn.findViewWithTag("green");
                        tochange.setTag("");
                        tochange.setTextColor(Color.parseColor("#FFAAAAAA"));
                    }

                    TextView mytext = (TextView) v;

                    mytext.setTag("green");
                    mytext.setTextColor(Color.GREEN);

                    // firstItemsLn.findViewWithTag("green").getId();
                }
            }
        };


        int nrItems = firstItemsLn.getChildCount();
        for (int i = 0; i < nrItems; i++){

            firstItemsLn.getChildAt(i).setOnClickListener(secondItemsListener);
        }
    }





    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(firstItems_LinearLayout.findViewWithTag("green")!= null){

            outState.putInt("firstSelected",firstItems_LinearLayout.findViewWithTag("green").getId());
        }
        if(secondItems_LinearLayout.findViewWithTag("green")!= null){

            outState.putInt("secondSelected",secondItems_LinearLayout.findViewWithTag("green").getId());
        }
        if(thirdItems_LinearLayout.findViewWithTag("green")!= null){

            outState.putInt("thirdSelected",thirdItems_LinearLayout.findViewWithTag("green").getId());
        }
    }
}
