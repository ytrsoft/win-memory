package com.ytrsoft;

import com.ytrsoft.core.ProcessMemory;
import com.ytrsoft.core.ProcessMemoryBuilder;
import com.ytrsoft.ui.AppGUI;
import com.ytrsoft.ui.XSlider;

public class App extends AppGUI {

    private ProcessMemory pm;

    private static final int HP_ADDRESS = 0X00428282;
    private static final String WINDOW_NAME = "Super Mario Xp";

    @Override
    protected void onInit(XSlider slider) {
        pm = new ProcessMemoryBuilder().windowName(WINDOW_NAME)
                .address(HP_ADDRESS).build();
        slider.setValue(pm.read());
    }

    @Override
    protected void onUpdate(int val) {
        pm.write(val);
    }

    public static void main(String[] args) {
        launch(App.class);
    }

}
