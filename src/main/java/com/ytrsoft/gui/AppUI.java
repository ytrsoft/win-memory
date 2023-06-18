package com.ytrsoft.gui;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.ytrsoft.util.img.svg.JavaSvgLoader;
import dorkbox.systemTray.SystemTray;

import javax.swing.*;
import java.awt.*;

public abstract class AppUI extends JFrame implements AddComponent {

    private final JPanel root;

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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    @Override
    public void addCenter(JComponent component) {
        root.add(component, BorderLayout.CENTER);
    }

    @Override
    public void addEast(JComponent component) {
        root.add(component, BorderLayout.EAST);
    }

    @Override
    public void addSouth(JComponent component) {
        root.add(component, BorderLayout.SOUTH);
    }

    @Override
    public void addNorth(JComponent component) {
        root.add(component, BorderLayout.NORTH);
    }

    @Override
    public void addWest(JComponent component) {
        root.add(component, BorderLayout.WEST);
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
