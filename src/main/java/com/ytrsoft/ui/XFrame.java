package com.ytrsoft.ui;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.github.weisj.darklaf.theme.Theme;
import com.kitfox.svg.app.beans.SVGIcon;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class XFrame extends JFrame {

    public XFrame(int width, int height) {
        this("Java Sample", width, height);
    }

    public XFrame(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(iconImage("java"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel root = new JPanel();
        start(root);
        setContentPane(root);
    }

    protected abstract void start(JPanel root);

    private Image iconImage(String name) {
        SVGIcon svg = new SVGIcon();
        svg.setSvgURI(getResourcesURI(name));
        return svg.getImage();
    }

    private URI getResourcesURI(String name) {
        name = String.format("/svg/%s.svg", name) ;
        try {
            return XFrame.class.getResource(name).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void launch(Class<? extends XFrame> clz) {
        launch(clz, new DarculaTheme());
    }

    public static void launch(Class<? extends XFrame> clz, Theme theme) {
        SwingUtilities.invokeLater(() -> {
            LafManager.install(theme);
            try {
                XFrame frame = clz.newInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
