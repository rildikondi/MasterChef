package com.example.amarildo.masterchef;

import android.content.SharedPreferences;
import android.view.View;

/**
 * Created by amarildo on 18-01-17.
 */

public abstract class BaseStepFragment extends BaseFragment  {

    public abstract int getPageNr();

    public abstract boolean validateStep();

    public abstract void updateGui();
}
