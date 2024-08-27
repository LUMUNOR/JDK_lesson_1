package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private final JPanel panelBotton = new JPanel(new GridLayout(1,2));

    private boolean isServerWorking;
    private ArrayList <ClientGUI> clients = new ArrayList<>();

    public ServerWindow(){
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    log.append("*Server stopped*" + "\n");
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isServerWorking) {
                    isServerWorking = true;
                    log.append("*Server start*" + "\n");
                }
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

    public void ConnectClient(ClientGUI client){
       log.append()
    }
}
