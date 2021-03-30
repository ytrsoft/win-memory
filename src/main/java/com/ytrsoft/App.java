package com.ytrsoft;

import com.ytrsoft.core.ProcessMemory;
import com.ytrsoft.core.ProcessMemoryBuilder;
import com.ytrsoft.ui.AppGUI;

public class App extends AppGUI {

    private final ProcessMemory pm;

    public static final int BASE_ADDRESS = 0X0CB51330;
    public static final String CLASS_NAME = "Purble Place";

    public App() {
        pm = buildProcessMemory();
    }

    @Override
    protected void onUpdate(int val) {
        int ret = pm.read();
        ret += val;
        pm.write(ret);
    }

    public ProcessMemory buildProcessMemory() {
        return new ProcessMemoryBuilder()
                .className(CLASS_NAME)
                .address(BASE_ADDRESS)
                .build();
    }

    public static void main(String[] args) {
        launch(App.class);
    }

}
