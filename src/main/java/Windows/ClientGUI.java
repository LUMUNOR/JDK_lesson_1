package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private String ipAddress;
    private String port;
    private String login;
    private String password;

    private Boolean connect;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField();
    private final JPasswordField tfPassword = new JPasswordField();
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBotton = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    public ClientGUI(ServerWindow serverWindow){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Чат клиента");
        setLocationRelativeTo(serverWindow);
        connect = false;

        tfMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getTexInLog();
            }
        });

        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               getTexInLog();
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ipAddress = tfIPAddress.getText();
                port = tfPort.getText();
                login = tfLogin.getText();
                password = tfPassword.getText();
                connect = true;
            }
        });

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop,BorderLayout.NORTH);

        panelBotton.add(tfMessage, BorderLayout.CENTER);
        panelBotton.add(btnSend, BorderLayout.EAST);
        add(panelBotton, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);
    }

    private void getTexInLog(){
        if (tfMessage.getText() != null && !tfMessage.getText().trim().isEmpty()) {
            log.append(tfMessage.getText()+"\n");
            tfMessage.setText("");
        } else tfMessage.setText("");
    }
}
