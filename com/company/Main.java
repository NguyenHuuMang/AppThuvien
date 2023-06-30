package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Icon icon = new ImageIcon("yourFile.gif");
        JButton btnAdd = new JButton("image");

        frame.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int returnValue = jFileChooser.showOpenDialog(frame);
                if(returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    String pathFile = file.getAbsolutePath();

                    System.out.println(pathFile);
                }

            }
        });

        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
