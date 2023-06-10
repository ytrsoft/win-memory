package com.ytrsoft;

import com.ytrsoft.core.Found;
import com.ytrsoft.core.IntMemory;
import com.ytrsoft.core.WinUser32;
import com.ytrsoft.ui.AppGUI;

import java.util.List;

public class App extends AppGUI {

    private IntMemory memory;
    private static final int HP_ADDRESS = 0X00428282;
    private static final String WINDOW_NAME = "Super Mario Xp";

    @Override
    protected void onInit() {
        memory = new IntMemory(
            WinUser32.getWindowThreadProcessId(
               WinUser32.findWindow(WINDOW_NAME)
            )
        );
        setValue(memory.read(HP_ADDRESS));
    }

    @Override
    protected void onUpdate(int val) {
        memory.write(HP_ADDRESS, val);
    }

    @Override
    protected void scan() {
        List<Found<Integer>> scans = memory.scan();
        for (Found<Integer> found: scans) {
            System.out.println(found.getAddress() + '\t' + found.getValue());
        }
    }

    public static void main(String[] args) {
        launch(App.class);
    }

}
