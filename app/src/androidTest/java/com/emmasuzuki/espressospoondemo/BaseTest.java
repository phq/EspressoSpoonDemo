package com.emmasuzuki.espressospoondemo;

import com.emmasuzuki.espressospoondemo.rule.SpoonTestWatcher;

import org.junit.Rule;

public class BaseTest {

    @Rule
    public SpoonTestWatcher mSpoonTestWatcher = new SpoonTestWatcher();
}
