package com.example.amarildo.masterchef;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.amarildo.masterchef.Steps.AddressDataStep;
import com.example.amarildo.masterchef.Steps.DataDiArrivoStep;
import com.example.amarildo.masterchef.Steps.DropDownsStep;
import com.example.amarildo.masterchef.Steps.PersonalDataStep;
import com.example.amarildo.masterchef.Steps.SelezioneRicetteStep;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BaseFragment.ChangeGuiListener  {

    private com.example.amarildo.masterchef.NonSwipeableViewPager viewPager;
    StepsPagerAdapter adapterViewPager;
    Button next_Button;
    Button back_Button;
    ProgressBar progress_Bar;
    LinearLayout buttons_LinearLayout;
    SharedPreferences sharedPreferences;
    List<BaseStepFragment> baseStepFragmentsList;
    FragmentManager manager;
    TextView currentPage_TextView;
    int currentPos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("onCreate", "activiti on create");
        sharedPreferences = getSharedPreferences("com.example.amarildo.masterchef", Context.MODE_PRIVATE);

        buttons_LinearLayout = findViewById(R.id.buttonsLinearLayout);
        progress_Bar = findViewById(R.id.progressBar);
        currentPage_TextView = findViewById(R.id.currentPageTextView);
        viewPager = (com.example.amarildo.masterchef.NonSwipeableViewPager) findViewById(R.id.pager);
        // We can declar viewPager ViewPager and cast it to com.example.amarildo.masterchef.NonSwipeableViewPager to use the overrided class methods
        //com.example.amarildo.masterchef.NonSwipeableViewPager prove = new com.example.amarildo.masterchef.NonSwipeableViewPager(getContext());
        //    prove = (com.example.amarildo.masterchef.NonSwipeableViewPager) viewPager;

        baseStepFragmentsList = getStepsFragment();
        adapterViewPager = new StepsPagerAdapter(getSupportFragmentManager(), baseStepFragmentsList);
        viewPager.setAdapter(adapterViewPager);

        if(savedInstanceState != null){
            currentPos = savedInstanceState.getInt("nrOfCurrentPage");
            viewPager.setCurrentItem(currentPos);
            currentPage_TextView.setText(currentPos+1+"");
        }

        adapterViewPager.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("numberInPageListener", position+"");
                currentPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //KeyboardUtil keyboardUtil = new KeyboardUtil(getActivity(), viewPager);
        //viewPager.setPagingEnabled(true);

        back_Button = findViewById(R.id.backButton);
        next_Button = findViewById(R.id.nextButton);

        next_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToNextFragment();
            }
        });

        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToPreviuosFragment();
            }
        });
    }

    List<BaseStepFragment> getStepsFragment(){

        List<BaseStepFragment> list = new ArrayList<>();
        list.add(new DropDownsStep());
        list.add(new SelezioneRicetteStep());
        list.add(new PersonalDataStep());
        list.add(new AddressDataStep());
        list.add(new DataDiArrivoStep());

        return list;
    }

    public void moveToNextFragment() {

        int pos = currentPos + 1;
        Log.i("currentItem", "" + currentPos);

        BaseStepFragment currentFragment = adapterViewPager.getFragment(currentPos);
        if (currentFragment.validateStep()) {

            if(pos < adapterViewPager.getCount()) {

                BaseStepFragment nextFragment = adapterViewPager.getFragment(pos);
                nextFragment.updateGui();
                viewPager.setCurrentItem(pos);
                //adapterViewPager.notifyDataSetChanged();
                currentPage_TextView.setText(pos+1+"");
                progress_Bar.setProgress(pos);
            }
        }
    }
    public void moveToPreviuosFragment() {

        int pos = currentPos - 1;
        Log.i("currentItem", ""+ currentPos);
        if(pos >=0 && pos != 1) {

            BaseStepFragment nextFragment = adapterViewPager.getFragment(pos);
            nextFragment.updateGui();
            viewPager.setCurrentItem(pos);
            currentPage_TextView.setText(pos+1+"");
            progress_Bar.setProgress(pos);

        }
        if(pos == 1){
            viewPager.setCurrentItem(pos);
            currentPage_TextView.setText(pos+1+"");
            progress_Bar.setProgress(pos);
        }
    }


    public void saveData(int id, Bundle data) {
        // based on the id you'll know which fragment is trying to save data(see below)
        // the Bundle will hold the data
    }

    public Bundle getSavedData() {
        // here you'll save the data previously retrieved from the fragments and
        // return it in a Bundle
        return null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt("nrOfCurrentPage", currentPos);
    }

    private void instantiateFragments(Bundle inState) {

    }

    @Override
    public void hideButtons() {
        buttons_LinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void showButtons() {
        buttons_LinearLayout.setVisibility(View.VISIBLE);
    }

}
