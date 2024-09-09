package ServerGUI;

import Server.Server;

public interface ServerWindow {
    void setServer(Server server);
    void startServer();
    void stopServer();
    void messageInLog(String message);
}
