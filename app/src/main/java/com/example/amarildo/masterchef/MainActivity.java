package com.example.amarildo.masterchef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements ThirdFragment.MoveToNextPageListener {

    //ViewPagerFragment viewPagerFragment;

    private com.example.amarildo.masterchef.NonSwipeableViewPager viewPager;
    MyViewPagerAdapter adapterViewPager;
    Button next_Button;
    Button back_Button;
    ProgressBar progress_Bar;
    LinearLayout buttons_LinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_pager);

        buttons_LinearLayout = findViewById(R.id.buttonsLinearLayout);
        progress_Bar = findViewById(R.id.progressBar);

        viewPager = findViewById(R.id.pager);
        // We can declar viewPager ViewPager and cast it to com.example.amarildo.masterchef.NonSwipeableViewPager to use the overrided class methods
        //com.example.amarildo.masterchef.NonSwipeableViewPager prove = (com.example.amarildo.masterchef.NonSwipeableViewPager) viewPager;
        //viewPager.setCurrentItem(Integer.MAX_VALUE / 2);

        adapterViewPager = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), viewPager);
        //viewPager.setPagingEnabled(true);

        //com.example.amarildo.masterchef.NonSwipeableViewPager prove = new com.example.amarildo.masterchef.NonSwipeableViewPager(getContext());
        //    prove = (com.example.amarildo.masterchef.NonSwipeableViewPager) viewPager;





        back_Button = findViewById(R.id.backButton);
        next_Button = findViewById(R.id.nextButton);


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



        /*if (savedInstanceState == null) {

            viewPagerFragment = new ViewPagerFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_main,viewPagerFragment).commit();
        }*/
    }

    @Override
    public void moveNextFragment() {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        //viewPagerFragment.moveToNextPage();
    }
}
