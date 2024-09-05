package Client;

import Server.ServerGUI;

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
    private char[] password;

    private ServerGUI serverGUI;
    private Boolean connectServer;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(1,1));
    private final JPanel panelTop1 = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField();
    private final JPasswordField tfPassword = new JPasswordField();
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBotton = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    public ClientGUI(ServerGUI serverGUI){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Чат клиента");
        setLocationRelativeTo(serverGUI);
        this.serverGUI = serverGUI;
        connectServer = false;

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
                connectToServer();
            }
        });

        panelTop1.add(tfIPAddress);
        panelTop1.add(tfPort);
        panelTop1.add(tfLogin);
        panelTop1.add(tfPassword);
        panelTop1.add(btnLogin);
        panelTop.add(panelTop1);
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
        if (connectServer && serverGUI.statusServer()) {
            if (tfMessage.getText() != null && !tfMessage.getText().trim().isEmpty()) {
                serverGUI.massageOnServer(login + ": " + tfMessage.getText() + "\n");
                tfMessage.setText("");
            } else tfMessage.setText("");
        } else {
            log.append("Client: Нет соединения с сервером!"+ "\n");
            connectServer = false;
            panelTop.add(panelTop1);
            panelTop.revalidate();
            panelTop.repaint();
            tfMessage.setText("");
            }
    }

    public String getLogin(){
        return this.login;
    }

    public void connectToServer(){
        if (!connectServer && serverGUI.statusServer()){
            this.ipAddress = tfIPAddress.getText();
            this.port = tfPort.getText();
            this.login = tfLogin.getText();
            this.password = tfPassword.getPassword();
            panelTop.removeAll();
            panelTop.revalidate();
            panelTop.repaint();
            this.connectServer = true;
            log.append("Client: Соединение с сервром установлено."+ "\n");
            serverGUI.connectClient(this);
        } else if (!serverGUI.statusServer()) log.append("Client: Cервер не доступен!"+ "\n");
    }

    public void massageOnClienl(String string){
        log.append(string);
    }

}



