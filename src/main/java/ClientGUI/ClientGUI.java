package ClientGUI;

import Client.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ClientWindow {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private Client client;

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

    public ClientGUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Чат клиента");

        tfMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pushText();
            }
        });

        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pushText();
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connection(client);
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

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    private void pushText(){
        if ((tfMessage.getText() != null) && (!tfMessage.getText().trim().isEmpty())) {
            client.pushText(tfMessage.getText());
            tfMessage.setText("");
        } else tfMessage.setText("");
    }

    public void noConnectServer(){
        log.append("Client: Нет соединения с сервером!"+ "\n");
        panelTop.add(panelTop1);
        panelTop.revalidate();
        panelTop.repaint();
        tfMessage.setText("");
    }

    public void connectToServer(){
        panelTop.removeAll();
        panelTop.revalidate();
        panelTop.repaint();
        this.client.setIpAddress(tfIPAddress.getText());
        this.client.setPort(tfPort.getText());
        this.client.setLogin(tfLogin.getText());
        this.client.setPassword(tfPassword.getPassword());
        log.append("Client: Соединение с сервром установлено."+ "\n");
    }

    private void connection(Client client){
        client.connection();
    }

    public void noServer(){
        log.append("Client: Cервер не доступен!"+ "\n");
    }

    public void messageInLog(String string){
        log.append(string);
    }

}



