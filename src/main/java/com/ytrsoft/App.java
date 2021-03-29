package com.ytrsoft;

import com.ytrsoft.core.ProcessMemory;
import com.ytrsoft.core.ProcessMemoryBuilder;
import com.ytrsoft.ui.AppGUI;

public class App extends AppGUI {

    private ProcessMemory memory;

    public App() {
        this.memory = buildProcessMemory();
    }

    @Override
    protected void update(int val) {
        int value = this.memory.readInt();
        int nVal = value + val;
        this.memory.writeInt(nVal);
    }

    private ProcessMemory buildProcessMemory() {
        return new ProcessMemoryBuilder()
                .appName("Purble Place")
                .appendAddress(0X0C4D1010)
                .build();
    }

    public static void main(String[] args) {
        launch(App.class);
    }

}
