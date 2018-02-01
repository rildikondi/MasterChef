package com.example.amarildo.masterchef;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;


public class BaseFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;

    public BaseFragment() {
        // Required empty public constructor
    }

    protected ChangeGuiListener changeGuiListener;


    public interface ChangeGuiListener {

        void hideButtons();
        void showButtons();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ChangeGuiListener) {
            changeGuiListener = (ChangeGuiListener) context;
        }
        else{
            throw new RuntimeException(context.toString()
                    + " must implement OnNewNoteListener");
        }
    }

    public static BaseFragment newInstance(int param1) {

        BaseFragment fragment = new BaseFragment();
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
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public  void expand(final View v) {

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

    public  void collapse(final View v) {

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

    public void handleViewTreeObserver(final View v){

        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                v.getWindowVisibleDisplayFrame(r);

                int heightDiff = v.getRootView().getHeight() - (r.bottom);
                if (heightDiff > 500) { // if more than 100 pixels, its probably a keyboard...
                    changeGuiListener.hideButtons();
                    v.setPadding(0,0,0,0);
                }
                else{
                    changeGuiListener.showButtons();
                    v.setPadding(0,0,0,0);
                }
            }
        });
    }

    public void deleteFirstPageData(){

        SharedPreferences sharedPref = getActivity().getSharedPreferences("com.example.amarildo.masterchef", Context.MODE_PRIVATE);
        //sharedPref.edit().remove(a).apply();

    }
}
