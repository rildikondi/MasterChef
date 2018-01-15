package com.example.amarildo.masterchef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by amarildo on 17-12-25.
 */

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    private static int NUM_ITEMS = 5;

    public MyViewPagerAdapter(FragmentManager fragmentManager)
    {

        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        Bundle args = new Bundle();
        switch (position){

            case 0:
                fragment = new FirstClass();

                // Our object is just an integer :-P
                args.putInt("nrPage", position);
                fragment.setArguments(args);
                return fragment;

            case 1 :
                fragment = new SecondFragment();

            // Our object is just an integer :-P
            args.putInt("nrPage", position);
            fragment.setArguments(args);
            return fragment;

            case 2 :
                fragment = new ThirdFragment();

                // Our object is just an integer :-P
                args.putInt("nrPage", position);
                fragment.setArguments(args);
                return fragment;

            case 3 :
                fragment = new FourthFragment();

                // Our object is just an integer :-P
                args.putInt("nrPage", position);
                fragment.setArguments(args);
                return fragment;

            case 4 :
                fragment = new FifthFragment();

            // Our object is just an integer :-P
            args.putInt("nrPage", position);
            fragment.setArguments(args);
            return fragment;

        }





     return  null;




        /*   switch (position){

            case 0 :
                return FirstFragment.newInstance(position);

            case 1 :
                return SecondFragment.newInstance(position);

            case 2 :
                return ThirdFragment.newInstance(position);

            case 3 :
                return FourthFragment.newInstance(position);

            case 4 :
                return FifthFragment.newInstance(position);

        }*/

        //return null;


    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
