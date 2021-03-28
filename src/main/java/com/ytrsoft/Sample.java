package com.ytrsoft;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.ytrsoft.core.ProcessMemory;
import com.ytrsoft.util.Bytes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sample extends JFrame implements ActionListener {

    private ProcessMemory memory;

    private static final int SCORE_ADDRESS = 0X0C4E1C30;

    public Sample() {
        setTitle("JNA示例");
        setSize(250, 65);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(SVGLoader.Images.JAVA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel root = new JPanel();
        JButton add = new JButton("上分");
        add.setActionCommand("1");
        add.addActionListener(this);
        JButton sub = new JButton("下分");
        sub.setActionCommand("-1");
        sub.addActionListener(this);
        root.add(add);
        root.add(sub);
        setContentPane(root);
        memory = new ProcessMemory("Purble Place");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LafManager.install(new DarculaTheme());
            Sample sample = new Sample();
            sample.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int command = Integer.parseInt(e.getActionCommand());
        if (!memory.isActive()) {
            JOptionPane.showMessageDialog(
                null,
                "找不到目标程序",
                "错误",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        byte[] bytes = memory.read(SCORE_ADDRESS, 4);
        int ret = Bytes.toUnsignedInt(bytes);
        ret += command;
        memory.write(SCORE_ADDRESS, new int[] { ret });
    }
}
