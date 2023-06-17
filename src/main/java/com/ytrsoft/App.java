package com.ytrsoft;

import com.ytrsoft.ui.AppGUI;

public class App extends AppGUI {

    private static final int HP_ADDRESS = 0X00428282;
    private static final String WINDOW_NAME = "Super Mario Xp";

    @Override
    protected void onInit() {}

    @Override
    protected void onUpdate(int val) {}

    @Override
    protected void scan() {}

    public static void main(String[] args) {
        launch(App.class);
    }

}
