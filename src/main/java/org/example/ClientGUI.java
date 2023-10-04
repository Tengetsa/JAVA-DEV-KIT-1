package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private static final String FILE_NAME = "Log.txt";

    JButton btnLogin, btnSend;
    JTextField ipAddress, port, userName, message, textSendField;
    JPasswordField password;

    public ClientGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setTitle("Chat Client");
        setResizable(false);


        JTextArea log = new JTextArea();
        btnLogin = new JButton("Login");
        btnSend = new JButton("Send");
        ipAddress = new JTextField("Ip address: ");
        hideFields(ipAddress);
        hideFields(port = new JTextField("Port: "));
        hideFields(userName = new JTextField("Login: "));
        password = new JPasswordField();
        message = new JTextField();
        textSendField = new JTextField();
        JPanel n = new JPanel(null);
        JPanel panTop = new JPanel(new GridLayout(2, 3));
        JPanel panBottom = new JPanel(new BorderLayout());

        panTop.add(ipAddress);
        panTop.add(port);
        panTop.add(n);
        panTop.add(userName);
        panTop.add(password);
        panTop.add(btnLogin);
        add(panTop, BorderLayout.NORTH);

        panBottom.add(message, BorderLayout.CENTER);
        panBottom.add(btnSend, BorderLayout.EAST);
        add(panBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        add(scrollPane);

        message.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btnSend.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        btnSend.addActionListener(e -> {
            String result = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ": " +
                    userName.getText() + ": " + message.getText() + "\n";
            log.append(result);
            try {
                writeLogToFile(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            message.setText("");
        });

        System.out.println(password);
        setVisible(true);
    }

    private void writeLogToFile(String data) throws IOException {
        try (FileWriter writer = new FileWriter(ClientGUI.FILE_NAME, true); BufferedWriter bwr =
            new BufferedWriter(writer)) { bwr.write(data); }
    }

    /**
     * Меняем данные при клике.
     * @param d изначальные данные поля.
     * @return возвращаем нововведенные данные.
     */
    private JTextField hideFields(JTextField d) {
        d.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                d.setText("");
            }
        });
        return d;
    }
}
