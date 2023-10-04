package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    public static final String SERVER_START = "Server is started.";
    public static final String SERVER_ALREADY_RUNNING = "Server is already running.";
    public static final String SERVER_STOP = "Server stopped.";
    public static final String SERVER_ALREADY_STOPPED = "Server is already stopped.";

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static boolean isServerWorking;
    private final JTextArea log = new JTextArea("Server is stopped");

    JButton btnStart, btnStop;

    public ServerWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Chat Server");

        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(e -> {

            if (isServerWorking) {
                log.setText(SERVER_ALREADY_RUNNING);
                System.out.println(SERVER_ALREADY_RUNNING);
            } else {
                log.setText(SERVER_START);
                System.out.println(SERVER_START);
            }
            isServerWorking = true;
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isServerWorking) {
                    log.setText(SERVER_ALREADY_STOPPED);
                    System.out.println(SERVER_ALREADY_STOPPED);
                } else {
                    log.setText(SERVER_STOP);
                    System.out.println(SERVER_STOP);
                }
                isServerWorking = false;
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);

        add(panBottom, BorderLayout.SOUTH);
        add(log);
        setVisible(true);

    }

}
