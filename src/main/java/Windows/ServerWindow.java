package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private ArrayList <ClientGUI> clientsList = new ArrayList<>();
    private StringBuffer history = new StringBuffer();

    public ServerWindow(){
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    log.append("*Server stopped*" + "\n");
                    disconnectClients();
                    writeLog();
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isServerWorking) {
                    isServerWorking = true;
                    log.append("*Server start*" + "\n");
                    loadLog();
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

    public void connectClient(ClientGUI client){
       log.append("Пльзователь " + client.getLogin() + " подключился" + "\n");
       clientsList.add(client);
       client.massageOnClienl(history.toString());
    }

    public void massageOnServer(String massage){
        log.append(massage);
        history.append(massage);
        for (ClientGUI c : clientsList){
            c.massageOnClienl(massage);
        }
    }

    public Boolean statusServer(){
        return isServerWorking;
    }

    private void disconnectClients(){
        clientsList.clear();
    }

    private void loadLog (){
        try
        {
            String result = Files.readString( Paths.get ("log.txt"));
            history.append(result);
            log.append(result);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void writeLog(){
        try( FileWriter writer = new FileWriter("log.txt",true))
        {
            writer.append(history.toString());
            history.delete(0,history.length());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
