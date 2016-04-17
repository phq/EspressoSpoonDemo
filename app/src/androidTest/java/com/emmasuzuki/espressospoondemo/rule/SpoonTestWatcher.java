package com.emmasuzuki.espressospoondemo.rule;

import android.app.Activity;

import com.squareup.spoon.Spoon;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class SpoonTestWatcher extends TestWatcher {

    private Activity activity;

    @Override
    protected void failed(Throwable e, Description description) {
        Spoon.screenshot(activity, "Error", description.getClassName(), description.getMethodName());
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
