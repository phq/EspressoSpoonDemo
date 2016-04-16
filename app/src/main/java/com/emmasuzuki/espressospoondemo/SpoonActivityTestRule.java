package com.emmasuzuki.espressospoondemo;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;

import com.squareup.spoon.Spoon;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class SpoonActivityTestRule<T extends Activity> extends ActivityTestRule<T> {

    private boolean mLaunchActivity = false;

    private T mActivity;

    public SpoonActivityTestRule(Class<T> activityClass) {
        this(activityClass, false);
    }

    public SpoonActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
        this(activityClass, initialTouchMode, true);
    }

    public SpoonActivityTestRule(Class<T> activityClass, boolean initialTouchMode,
                            boolean launchActivity) {
        super(activityClass, initialTouchMode, launchActivity);
        this.mLaunchActivity = launchActivity;
    }

    @Override
    public Statement apply(final Statement base, Description description) {

        UiThreadTestRule uiThreadTestRule = new UiThreadTestRule();
        Statement statement = uiThreadTestRule.apply(base, description);


//        return uiThreadTestRule.apply(base, description);
        return new SpoonActivityStatement(statement, description);
    }

    void finishActivity() {
        if (mActivity != null) {
            mActivity.finish();
            mActivity = null;
        }
    }

    private class SpoonActivityStatement extends Statement {
        private final Statement mBase;
        private final Description mDescription;

        public SpoonActivityStatement(Statement base, Description description) {
            mBase = base;
            mDescription = description;
        }

        @Override
        public void evaluate() throws Throwable {
            try {
                if (mLaunchActivity) {
                    mActivity = launchActivity(getActivityIntent());
                }
                mBase.evaluate();
            } catch (Throwable e) {
                if (mActivity != null) {
                    Spoon.screenshot(mActivity, "Error", mDescription.getClassName(), mDescription.getMethodName());
                }
            } finally {
                finishActivity();
                afterActivityFinished();
            }
        }
    }
}
