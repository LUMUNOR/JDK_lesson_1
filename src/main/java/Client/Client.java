package Client;

import ClientGUI.ClientWindow;
import Server.Server;

public class Client {

    private String ipAddress;
    private String port;
    private String login;
    private char[] password;

    private Server server;
    private ClientWindow clientWindow;

    private Boolean connectServer;

    public Client(ClientWindow clientWindow, Server server){
        this.clientWindow = clientWindow;
        this.clientWindow.setClient(this);
        this.server = server;
        this.connectServer = false;
    }

    public void connection(){
        if (server.working()){
            clientWindow.connectToServer();
            server.connectClient(this);
            connectServer = true;
        } else clientWindow.noServer();
    }

    public void pushText (String message){
        if (connectServer && server.working()) {
            server.massageOnServer(this.login + ": " + message);
        } else disconnect();
    }

    public void messageInLog (String message){
        clientWindow.messageInLog(message);
    }

    public void disconnect(){
        connectServer = false;
        clientWindow.noConnectServer();
    }

    public void setLogin(String login){this.login = login;}
    public void setIpAddress(String ipAddress){this.ipAddress = ipAddress;}
    public void setPort(String port){this.port = port;}
    public void setPassword(char[] password){this.password = password;}

    public String getLogin(){
        return this.login;
    }
}
