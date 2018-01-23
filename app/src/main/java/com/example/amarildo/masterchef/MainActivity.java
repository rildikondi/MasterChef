package com.example.amarildo.masterchef;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.example.amarildo.masterchef.Steps.AddressDataStep;
import com.example.amarildo.masterchef.Steps.DropDownsStep;
import com.example.amarildo.masterchef.Steps.PersonalDataStep;
import com.example.amarildo.masterchef.Steps.SelezioneRicetteStep;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    private com.example.amarildo.masterchef.NonSwipeableViewPager viewPager;
    StepsPagerAdapter adapterViewPager;
    Button next_Button;
    Button back_Button;
    ProgressBar progress_Bar;
    LinearLayout buttons_LinearLayout;
    SharedPreferences sharedPreferences;
    List<BaseStepFragment> baseStepFragmentsList;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_pager);
        manager = getSupportFragmentManager();
        sharedPreferences = getSharedPreferences("com.example.amarildo.masterchef", Context.MODE_PRIVATE);

        buttons_LinearLayout = findViewById(R.id.buttonsLinearLayout);
        progress_Bar = findViewById(R.id.progressBar);

        viewPager = findViewById(R.id.pager);
        // We can declar viewPager ViewPager and cast it to com.example.amarildo.masterchef.NonSwipeableViewPager to use the overrided class methods
        //com.example.amarildo.masterchef.NonSwipeableViewPager prove = new com.example.amarildo.masterchef.NonSwipeableViewPager(getContext());
        //    prove = (com.example.amarildo.masterchef.NonSwipeableViewPager) viewPager;

        baseStepFragmentsList = getStepsFragment();

        /*BaseStepFragment mMyFragment;

           for(int i = 0; i < getStepsFragment().size(); i++){
               mMyFragment = (BaseStepFragment) manager.getFragment(savedInstanceState,""+i);
               baseStepFragmentsList.add(mMyFragment);
        }*/


        adapterViewPager = new StepsPagerAdapter(getSupportFragmentManager(), baseStepFragmentsList);
        viewPager.setAdapter(adapterViewPager);

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

        return list;
    }

    public void moveToNextFragment() {

        int currentPos = viewPager.getCurrentItem();
        int pos = viewPager.getCurrentItem() + 1;

        Log.i("CurrentFragment", "" + pos);

        BaseStepFragment currentFragment = adapterViewPager.getFragment(currentPos);
        if (currentFragment.validateStep()) {

            if(pos < baseStepFragmentsList.size()) {
                viewPager.setCurrentItem(pos);
                BaseStepFragment nextFragment = adapterViewPager.getFragment(pos);
                nextFragment.updateGui();
                //adapterViewPager.notifyDataSetChanged();
                progress_Bar.setProgress(pos);
            }
        }
    }


    public void moveToPreviuosFragment() {

        int currentPos = viewPager.getCurrentItem();
        int pos = viewPager.getCurrentItem() - 1;


      /*if (pos == 0){

          BaseStepFragment currentFragment = adapterViewPager.getFragment(pos);
          adapterViewPager.setPrimaryItem(viewPager, 0,currentFragment );


          adapterViewPager.notifyDataSetChanged();
          viewPager.setCurrentItem(pos);
            //viewPager.setCurrentItem(pos);
            progress_Bar.setProgress(pos);
        }else{*/

            Log.i("currentItem", ""+ pos);
            viewPager.setCurrentItem(pos);

            progress_Bar.setProgress(pos);
        //}
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
    }

    private void instantiateFragments(Bundle inState) {


    }

}
