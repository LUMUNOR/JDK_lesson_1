package Server;

import Client.Client;
import Server.Repository.Keeper;
import ServerGUI.ServerWindow;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Server {

    private ServerWindow serverWindow;
    private Keeper keeper;

    private boolean isServerWorking;
    private ArrayList<Client> clientsList;
    private StringBuffer history;

    public Server(ServerWindow serverWindow, Keeper keeper){
        this.isServerWorking = false;
        this.clientsList = new ArrayList<>();
        this.history = new StringBuffer();
        this.serverWindow = serverWindow;
        this.serverWindow.setServer(this);
        this.keeper = keeper;
    }

    public void connectClient(Client client){
        log.append("Пльзователь " + client.getLogin() + " подключился" + "\n");
        clientsList.add(client);
        client.massageInLog(history.toString());
    }

    public void massageOnServer(String massage){
        log.append(massage);
        history.append(massage);
        for (ClientGUI c : clientsList){
            c.massageInLog(massage);
        }
    }

    public Boolean working(){
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
