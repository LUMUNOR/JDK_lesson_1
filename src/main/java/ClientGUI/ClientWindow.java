package ClientGUI;

import Client.Client;

public interface ClientWindow {
    void setClient(Client client);
    void noConnectServer();
    void connectToServer();
    void noServer();
    void messageInLog(String string);
}
