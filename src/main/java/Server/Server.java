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

    public void startWorking(){
        if (!isServerWorking){
            isServerWorking = true;
            serverWindow.startServer();
            loadLog();
        }
    }

    public void stopWorking(){
        if (isServerWorking) {
            isServerWorking = false;
            serverWindow.stopServer();
            disconnectClients();
            writeLog();
        }
    }

    public void connectClient(Client client){
        serverWindow.messageInLog("Пльзователь " + client.getLogin() + " подключился" + "\n");
        clientsList.add(client);
        client.messageInLog(history.toString());
    }

    public void massageOnServer(String massage){
        serverWindow.messageInLog(massage);
        history.append(massage);
        for (Client c : clientsList){
            c.messageInLog(massage);
        }
    }

    public Boolean working(){
        return isServerWorking;
    }

    private void disconnectClients(){
        for (Client c : clientsList){
            c.disconnect();
        }
        clientsList.clear();
    }

    private void loadLog (){
        String log = keeper.load();
        history.append(log);
        serverWindow.messageInLog(log);
    }

    private void writeLog(){
        if (keeper.save(history.toString())) {
            history.delete(0,history.length());
        }
    }
}
