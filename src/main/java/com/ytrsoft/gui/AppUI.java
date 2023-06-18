package com.ytrsoft.gui;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.ytrsoft.util.img.svg.JavaSvgLoader;
import org.jdesktop.swingx.JXFrame;
import dorkbox.systemTray.SystemTray;

import javax.swing.*;
import java.awt.*;

import static com.github.weisj.darklaf.LafManager.getPreferredThemeStyle;

public abstract class AppUI extends JXFrame {

    private JPanel root;

    private final JavaSvgLoader logo = new JavaSvgLoader();

    public AppUI(int width, int height) {
        this("Java", width, height);
    }

    public AppUI(String title, int width, int height) {
        super(title);
        loadAppIcon();
        loadTrayIcon();
        setSize(width, height);
        root = new JPanel();
        root.setLayout(new BorderLayout());
        initUI();
        setContentPane(root);
        setLocationRelativeTo(null);
    }

    private Image getLogoImage() {
        return logo.toIcon().getImage();
    }

    private void loadAppIcon() {
        setIconImage(getLogoImage());
    }

    private void loadTrayIcon() {
        SystemTray tray = SystemTray.get();
        tray.setImage(getLogoImage());
        tray.setTooltip(getTitle());
    }

    protected abstract void initUI();

    protected void addCenter(JComponent component) {
        root.add(component, BorderLayout.CENTER);
    }

    public static void launch(Class<? extends AppUI> clz) {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    try {
                        LafManager.install(new DarculaTheme());
                        AppUI ui = clz.getDeclaredConstructor().newInstance();
                        ui.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
