package com.example.amarildo.masterchef;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.List;

public class StepsPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseStepFragment> mFragmentList;
    private final SparseArray<WeakReference<BaseStepFragment>> instantiatedFragments = new SparseArray<>();

    public StepsPagerAdapter(FragmentManager fragmentManager, List<BaseStepFragment> fragmentList)
    {
        super(fragmentManager);
        this.mFragmentList = fragmentList;
    }

    @Override
    public BaseStepFragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final BaseStepFragment fragment = (BaseStepFragment) super.instantiateItem(container, position);
        instantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        instantiatedFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Nullable
    public BaseStepFragment getFragment(final int position) {
        final WeakReference<BaseStepFragment> wr = instantiatedFragments.get(position);
        if (wr != null) {
            return wr.get();
        } else {
            return null;
        }
    }
}
