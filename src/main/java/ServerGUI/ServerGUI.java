package ServerGUI;

import Server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerGUI extends JFrame implements ServerWindow {

    private Server server;

    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private final JPanel panelBotton = new JPanel(new GridLayout(1,2));

    public ServerGUI(){
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopWorking();
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.startWorking();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);//  Завершение приложения по закрытию окна
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        panelBotton.add(btnStart);
        panelBotton.add(btnStop);
        add(panelBotton, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void startServer(){
        log.append("*Server start*" + "\n");
    }

    public void stopServer(){
        log.setText("");
        log.append("*Server stopped*" + "\n");
    }

    public void messageInLog(String message) {
        log.append(message);
    }

}
