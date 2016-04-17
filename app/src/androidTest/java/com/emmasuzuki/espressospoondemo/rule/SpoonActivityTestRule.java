package com.emmasuzuki.espressospoondemo.rule;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;


public class SpoonActivityTestRule<T extends Activity> extends ActivityTestRule<T> {


    private final SpoonTestWatcher mSpoonTestWatcher;

    public SpoonActivityTestRule(Class<T> activityClass, SpoonTestWatcher spoonTestWatcher) {
        this(activityClass, false, spoonTestWatcher);
        
    }

    public SpoonActivityTestRule(Class<T> activityClass, boolean initialTouchMode, SpoonTestWatcher spoonTestWatcher) {
        this(activityClass, initialTouchMode, true, spoonTestWatcher);
    }

    public SpoonActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity, SpoonTestWatcher spoonTestWatcher) {
        super(activityClass, initialTouchMode, launchActivity);
        mSpoonTestWatcher = spoonTestWatcher;
    }

    @Override
    public void afterActivityLaunched() {
        mSpoonTestWatcher.setActivity(this.getActivity());
    }
    
}
