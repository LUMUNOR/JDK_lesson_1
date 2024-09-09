package org.example;

import Client.Client;
import ClientGUI.ClientGUI;
import Server.Repository.Repository;
import Server.Server;
import ServerGUI.ServerGUI;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new ServerGUI(), new Repository());
        new Client(new ClientGUI(), server);
        new Client(new ClientGUI(), server);
        }
    }